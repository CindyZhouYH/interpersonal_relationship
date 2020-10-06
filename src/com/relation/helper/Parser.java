package com.relation.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String objectToJson(Object data) {
        try {
            String str = MAPPER.writeValueAsString(data);
            return str;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
