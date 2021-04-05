package ru.otus.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionHelper {

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
        throw new NoDefultConstractorExeption("Class does not contain default constructor ");
    }
}
