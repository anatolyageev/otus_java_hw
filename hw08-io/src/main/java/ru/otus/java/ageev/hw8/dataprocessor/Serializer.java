package ru.otus.java.ageev.hw8.dataprocessor;

import java.util.Map;

public interface Serializer {

     /**
     * Serialize to json
     * @param data
     */
    void serialize(Map<String, Double> data);
}
