package org.demo.codesmell.service;

import lombok.extern.log4j.Log4j2;
import org.demo.codesmell.dto.GraphDTO;
import org.demo.codesmell.dto.RequestDTO;
import org.demo.codesmell.dto.SpanDTO;
import org.demo.codesmell.dto.TopologyGraphDTO;
import org.demo.codesmell.mapper.TopologyMapper;
import org.demo.codesmell.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Service
public class TopologyService {

    @Autowired
    private TopologyMapper topologyMapper;

    @Resource(name = "taskExecutor")
    private TaskExecutor executor;

    public void asyncCreateGraph(Long num, Integer hash) {
        executor.execute(() -> createGraph(num, hash));
    }

    public GraphDTO query(RequestDTO requestDTO) {
        return topologyMapper.search(requestDTO);
    }

    private void createGraph(Long num, Integer hash) {
        long end = System.currentTimeMillis() - 30000;
        long start = end - num;
        List<String> names = queryNames(start, end);
        names.parallelStream().forEach(name -> {
            int hc = Math.abs(name.hashCode());
            if (hc % 10 != hash) {
                return;
            }
            Set<String> ids = queryIds(start, end, name);
            if (ids.isEmpty()) {
                return;
            }
            GraphDTO graphDTO = query(ids, start, end);
            if (graphDTO.getEdges().isEmpty()) {
                return;
            }
            createGraph(graphDTO);
        });
    }

    private List<String> queryNames(long start, long end) {
        RequestDTO dto = RequestDTO.builder().start(start).end(end).size(1000).build();
        return topologyMapper.searchNames(dto);
    }

    private Set<String> queryIds(long start, long end, String name) {
        RequestDTO dto = RequestDTO.builder().start(start).end(end).size(1000).name(name).build();
        return topologyMapper.searchIds(dto);
    }

    private GraphDTO query(Set<String> ids, long start, long end) {
        GraphDTO graphDTO = GraphDTO.graphDTO();
        int size = ids.size();
        int length = 50;
        int loopCnt = (size % length== 0) ? (size / length) : (size / length + 1);
        Stream.iterate(0, n -> n + 1).limit(loopCnt).forEach(i -> {
            String idStr = JsonUtil.arrayToString(ids.stream().skip(i * length).limit(length).collect(Collectors.toList()));
            RequestDTO dto = RequestDTO.builder().start(start).end(end).id(idStr).build();
            SpanDTO spanDTO = topologyMapper.searchSpan(dto);
            transSpanToGraph(spanDTO, graphDTO);
        });
        return graphDTO;
    }

    private void transSpanToGraph(SpanDTO spanDTO, GraphDTO graphDTO) {
        Map<String, GraphDTO.Trace> traceMap = new ConcurrentHashMap<>();
        spanDTO.getSpans().parallelStream().forEach(span -> {
            TopologyGraphDTO.Node node = new TopologyGraphDTO.Node();
            node.setName(span.getName());
            node.setArea(span.getArea());
            node.setCenter(span.getCenter());
            node.setCluster(span.getCluster());
            GraphDTO.Trace trace = traceMap.get(span.getId());
            if (trace == null) {
                trace = new GraphDTO.Trace();
                traceMap.put(span.getId(), trace);
            }
            String kind = span.getKind();
            switch (kind) {
                case "SERVER":
                case "CONSUMER":
                    trace.setChild(node);
                    break;
                case "CLIENT":
                case "PRODUCER":
                    trace.setParent(node);
                    break;
            }
        });
        traceMap.values().forEach(trace -> {
            TopologyGraphDTO.Edge edge = new TopologyGraphDTO.Edge();
            TopologyGraphDTO.Node parent = trace.getParent();
            TopologyGraphDTO.Node child = trace.getChild();
            if (parent == null && child == null) {
                return;
            }
            edge.setParent(parent.getName());
            edge.setPArea(parent.getArea());
            edge.setPCenter(parent.getCenter());
            edge.setPCluster(parent.getCluster());
            graphDTO.getNodes().add(child);

            edge.setChild(child.getName());
            edge.setCArea(child.getArea());
            edge.setCCenter(child.getCenter());
            edge.setCCluster(child.getCluster());
            graphDTO.getNodes().add(parent);

            graphDTO.getEdges().add(edge);
        });
    }

    private void createGraph(GraphDTO graphDTO) {
        topologyMapper.create(graphDTO);
    }
}
