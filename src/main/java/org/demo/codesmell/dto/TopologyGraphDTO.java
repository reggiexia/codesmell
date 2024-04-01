package org.demo.codesmell.dto;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class TopologyGraphDTO {

    private List<Node> nodes;
    private List<Edge> edges;

    @Data
    public static class Node {
        private String name;
        private String center;
        private String area;
        private String cluster;

        @Override
        public Object clone() {
            Node node = new Node();
            node.setName(this.getName());
            node.setCenter(this.getCenter());
            node.setArea(this.getArea());
            node.setCluster(this.getCluster());
            return node;
        }
    }

    @Data
    public static class Edge {
        private String parent;
        private String pCenter;
        private String pArea;
        private String pCluster;
        private String child;
        private String cCenter;
        private String cArea;
        private String cCluster;
    }

    @Data
    public static class Service {
        private String name;
        private String type;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Service service = (Service) o;
            return name.equals(service.name) && type.equals(service.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type);
        }
    }
}
