package org.demo.codesmell.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpanDTO {

    private List<Span> spans;

    @Data
    public static class Span {
        private String id;
        private String name;
        private String parentId;
        private String kind;
        private String center;
        private String area;
        private String cluster;
    }
}
