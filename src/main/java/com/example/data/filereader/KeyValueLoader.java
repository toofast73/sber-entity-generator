package com.example.data.filereader;

import com.example.data.converter.PojoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class KeyValueLoader implements FileLoader<Map<String, String>> {

    private final JsonLoader jsonLoader;
    private final PojoConverter converter;

    public KeyValueLoader(JsonLoader jsonLoader, PojoConverter converter) {
        this.jsonLoader = jsonLoader;
        this.converter = converter;
    }

    @Override
    public List<Map<String, String>> loadAll() {
        return jsonLoader.loadAll().stream()
                .map(converter::convertJsonToKeyValue)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, String>> load(int fieldsCount) {
        return jsonLoader.load(fieldsCount).stream()
                .map(converter::convertJsonToKeyValue)
                .collect(Collectors.toList());
    }
}
