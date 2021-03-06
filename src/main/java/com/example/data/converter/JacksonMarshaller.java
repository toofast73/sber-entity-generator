package com.example.data.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class JacksonMarshaller implements JsonMarshaller {

    ObjectMapper mapper = new ObjectMapper();

    // Convert object to JSON string
    @Override
    public String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Convert JSON string to Object
    @Override
    public <T> T fromJson(String jsonInString, Class<T> valueType) {
        try {
            return mapper.readValue(jsonInString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Convert object to map
    <K, V> Map<K, V> toMap(Object object) {
        return mapper.convertValue(object, new MapTypeReference());
    }

    private static class MapTypeReference extends TypeReference<Map<String, Object>> {
    }

    // Convert map to object
    <T> T map2Object(Map<? extends Object, ? extends Object> map, Class<T> valueType) {
        return mapper.convertValue(map, valueType);
    }

    // Convert map to JSON string
    String fromMap(Map<? extends Object, ? extends Object> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    JsonNode readTree(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}