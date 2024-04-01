package org.demo.codesmell.mapper;

import org.demo.codesmell.dto.GraphDTO;
import org.demo.codesmell.dto.RequestDTO;
import org.demo.codesmell.dto.SpanDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TopologyMapper {

    public Set<String> searchIds(RequestDTO requestDTO) {
        return new HashSet<>();
    }

    public GraphDTO search(RequestDTO requestDTO) {
        return new GraphDTO();
    }

    public List<String> searchNames(RequestDTO requestDTO) {
        return new ArrayList<>();
    }

    public SpanDTO searchSpan(RequestDTO requestDTO) {
        return new SpanDTO();
    }

    public void create(GraphDTO graphDTO) {

    }
}
