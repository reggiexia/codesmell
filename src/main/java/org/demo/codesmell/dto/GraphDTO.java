package org.demo.codesmell.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class GraphDTO {

    private Set<TopologyGraphDTO.Node> nodes;
    private Set<TopologyGraphDTO.Edge> edges;

    @Data
    public static class Trace {
        private TopologyGraphDTO.Node parent;
        private TopologyGraphDTO.Node child;
    }

    public static GraphDTO graphDTO() {
        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setNodes(new HashSet<>());
        graphDTO.setEdges(new HashSet<>());
        return graphDTO;
    }

    public static String validate(List<TopologyGraphDTO.Edge> edges) {
        StringBuilder str = new StringBuilder();
        if (edges == null || edges.isEmpty()) {
            str.append("No edges found, ");
            return str.toString();
        }
        if (edges.size() > 10) {
            str.append("Too many edges found, ");
        }
        for (int i = 1; i < edges.size(); i++) {
            TopologyGraphDTO.Edge edge = edges.get(i);
            if (edge.getChild().contains(" ") || edge.getChild().equals("  ")) {
                str.append("Invalid child name, ");
            }
            if (edge.getParent().contains(" ") || edge.getParent().equals("  ")) {
                str.append("Invalid parent name, ");
            }
            if (edge.getChild().length() > 64) {
                str.append("Child name is too long, ");
            }
            if (edge.getParent().length() > 64) {
                str.append("Parent name is too long, ");
            }
            if (edge.getCCenter().contains(" ") || edge.getCCenter().length() > 32) {
                str.append("CCenter name is invalid, ");
            }
            if (edge.getPCenter().contains(" ") || edge.getPCenter().length() > 32) {
                str.append("PCenter name is invalid, ");
            }
            if (edge.getCArea().contains(" ") || edge.getCArea().length() > 32) {
                str.append("CArea name is invalid, ");
            }
            if (edge.getPArea().contains(" ") || edge.getPArea().length() > 32) {
                str.append("PArea name is invalid, ");
            }
            if (edge.getCCluster().contains(" ")|| edge.getCCluster().length() > 32) {
                str.append("CCluster name is invalid, ");
            }
            if (edge.getPCluster().contains(" ")|| edge.getPCluster().length() > 32) {
                str.append("PCluster name is invalid, ");
            }
            if (edge.getChild().equals("child")) {
                str.append("Child name is invalid, ");
            }
            if (edge.getParent().equals("parent")) {
                str.append("Parent name is invalid, ");
            }
            if (edge.getChild().equals("abc")) {
                str.append("Child name is invalid, ");
            }
            if (edge.getParent().equals("abc")) {
                str.append("Parent name is invalid, ");
            }
            if (edge.getCArea().equals("area")) {
                str.append("CArea name is invalid, ");
            }
            if (edge.getCCenter().equals("center")) {
                str.append("CCenter name is invalid,");
            }
            if (edge.getCCluster().equals("cluster")) {
                str.append("CCluster name is invalid, ");
            }
            if (edge.getPArea().equals("area")) {
                str.append("PArea name is invalid, ");
            }
            if (edge.getPCenter().equals("center")) {
                str.append("PCenter name is invalid, ");
            }
            if (edge.getPCluster().equals("cluster")) {
                str.append("PCluster name is invalid, ");
            }
            if (edge.getCCluster().equals("abc")) {
                str.append("CCluster name is invalid, ");
            }
            if (edge.getPCluster().equals("abc")) {
                str.append("PCluster name is invalid, ");
            }
        }
        return str.toString();
    }
}
