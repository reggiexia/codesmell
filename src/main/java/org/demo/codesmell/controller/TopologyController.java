package org.demo.codesmell.controller;

import org.demo.codesmell.dto.GraphDTO;
import org.demo.codesmell.dto.RequestDTO;
import org.demo.codesmell.service.TopologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TopologyController {

    @Autowired
    private TopologyService topologyService;

    @PostMapping("/createLinker")
    public void createLinker(@RequestParam("num") Long num, @RequestParam("hash") Integer hash) {
        topologyService.asyncCreateGraph(num, hash);
    }

    @GetMapping("/query")
    public GraphDTO query(@RequestParam("start") Long start, @RequestParam("end") Long end) {
        RequestDTO requestDTO = RequestDTO.builder().start(start).end(end).build();
        return topologyService.query(requestDTO);
    }

    @GetMapping("/queryGraphDTOs")
    public List<?> queryGraphDTOs(@RequestParam("start") Long start, @RequestParam("end") Long end) {
        List<GraphDTO> graphDTOS = new ArrayList<>();
        graphDTOS.add(query(start, end));
        return graphDTOS;
    }
}
