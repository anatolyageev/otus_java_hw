package ru.otus.ageev.hw8.dataprocessor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.requirementsascode.moonwlker.MoonwlkerModule;
import ru.otus.ageev.hw8.model.Measurement;
import com.google.gson.Gson;

public class FileLoader implements Loader {
    private final String  fileName;



    public FileLoader(String fileName) {
//        ClassLoader classLoader = getClass().getClassLoader();
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader fileReader =  new FileReader(classLoader.getResource(fileName).getFile());
            JsonReader reader = new JsonReader(fileReader);
            Measurement[] measurements = new Gson().fromJson(reader, Measurement[].class);
            List<Measurement> measurementList = Arrays.asList(measurements);
            System.out.println(measurementList);
            return measurementList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        //читает файл, парсит и возвращает результат
        return null;
    }
}
