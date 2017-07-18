package com.example.data.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class JacksonConverter {

    ObjectMapper mapper = new ObjectMapper();

    // Convert object to JSON string
    public String toJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // Convert JSON string to Object
    public <T> T fromJson(String jsonInString, Class<T> valueType) {
        try {
            return mapper.readValue(jsonInString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Convert object to map
    public <K, V> Map<K, V> toMap(Object object) {
        return mapper.convertValue(object, new TypeReference<Map<K, V>>() {
        });
    }

    // Convert map to object
    public Object map2Object(Map<Object, Object> map, Class<?> valueType) {
        try {
            return mapper.readValue(mapper.writeValueAsString(map), valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Convert map to JSON string
    public String fromMap(Map<Object, Object> map) {
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode readTree(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}