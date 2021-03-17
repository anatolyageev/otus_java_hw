package ru.otus.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ListnersHelper {
    public static <T> T copy(T anObject, Class<T> classInfo) {
        Gson gson = new GsonBuilder().create();
        String text = gson.toJson(anObject);
        T newObject = gson.fromJson(text, classInfo);
        return newObject;
    }
}
