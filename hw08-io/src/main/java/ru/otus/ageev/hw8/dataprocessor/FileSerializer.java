package ru.otus.ageev.hw8.dataprocessor;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    /**
     * формирует результирующий json и сохраняет его в файл
     *
     * @param data
     */
    @Override
    public void serialize(Map<String, Double> data) {
        Gson gson = new Gson();
        ClassLoader classLoader = getClass().getClassLoader();
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            gson.toJson(data, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
