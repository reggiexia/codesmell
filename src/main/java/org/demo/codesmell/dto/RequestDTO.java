package org.demo.codesmell.dto;

import lombok.Data;

@Data
public class RequestDTO {

    private Long start;
    private Long end;
    private String name;
    private String id;
    private String type;
    private Integer size;

    public static class RequestDTOBuilder {
        private RequestDTO dto;

        public RequestDTOBuilder() {
            dto = new RequestDTO();
        }

        public RequestDTOBuilder start(Long start) {
            dto.start = start;
            return this;
        }

        public RequestDTOBuilder end(Long end) {
            dto.end = end;
            return this;
        }

        public RequestDTOBuilder size(Integer size) {
            dto.size = size;
            return this;
        }

        public RequestDTOBuilder name(String name) {
            dto.name = name;
            return this;
        }

        public RequestDTOBuilder id(String traceId) {
            dto.id = traceId;
            return this;
        }

        public RequestDTOBuilder type(String type) {
            dto.type = type;
            return this;
        }

        public RequestDTO build() {
            return dto;
        }
    }

    public static RequestDTOBuilder builder() {
        return new RequestDTOBuilder();
    }

}
