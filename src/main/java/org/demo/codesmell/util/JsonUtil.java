package org.demo.codesmell.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;

@Log4j2
public class JsonUtil {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String arrayToString(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        String result = null;
        try {
            result = MAPPER.writeValueAsString(collection);
        } catch (JsonProcessingException e) {
            log.error("arrayToString error: {}", e.getMessage());
        }
        return result;
    }
}
