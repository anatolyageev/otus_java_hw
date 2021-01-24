package com.otus.ageev.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper {

    public static List<Method> getAnnotationMethods(Class<? extends Annotation> annotationType, Class<?> clazz){
        List<Method> annotationMethods = new ArrayList<>();
        for(Method method : clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(annotationType)){
                annotationMethods.add(method);
            }
        }
        return annotationMethods;
    }
}
