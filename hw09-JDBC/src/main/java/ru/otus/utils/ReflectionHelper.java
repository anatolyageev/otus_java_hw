package ru.otus.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionHelper {

    public static List<Method> getAnnotationMethods(Class<? extends Annotation> annotationType, Class<?> clazz) {
        List<Method> annotationMethods = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationType)) {
                annotationMethods.add(method);
            }
        }
        return annotationMethods;
    }

    public static Field getFieldWithAnnotation(Class<? extends Annotation> annotationType, Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationType))
                return field;
        }
        return null;
    }

    public static Constructor<?> getDefaultConstruct(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 0) return constructor;
        }
        return null;
    }

    public static List<Field> getFields(Class<?> clazz) {
        return Arrays.asList(clazz.getDeclaredFields());
    }
}
