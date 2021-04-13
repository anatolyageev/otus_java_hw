package ru.otus.java.ageev.hw8.dataprocessor;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ru.otus.java.ageev.hw8.model.Measurement;

public class FileLoader implements Loader {
    private final String fileName;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * читает файл, парсит и возвращает результат
     *
     * @return
     */
    @Override
    public List<Measurement> load() {
        List<Measurement> measurementList = Collections.emptyList();
        ClassLoader classLoader = getClass().getClassLoader();
        try (FileReader fileReader = new FileReader(classLoader.getResource(fileName).getFile());
             JsonReader reader = new JsonReader(fileReader)) {
            measurementList = Arrays.asList(new Gson().fromJson(reader, Measurement[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return measurementList;
    }
}
