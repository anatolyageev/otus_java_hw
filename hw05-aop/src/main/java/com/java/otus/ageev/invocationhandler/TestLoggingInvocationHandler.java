package com.java.otus.ageev.invocationhandler;

import com.java.otus.ageev.domain.TestLogging;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TestLoggingInvocationHandler implements InvocationHandler {
    private final TestLogging testLogging;
    private final Set<String> annotatedMethods;

    public TestLoggingInvocationHandler(Class<? extends Annotation> annotationClass, TestLogging testLogging) {
        this.testLogging = testLogging;
        annotatedMethods = getAnnotatedMethods(annotationClass, testLogging.getClass());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName() + Arrays.toString(method.getParameters());
        if (annotatedMethods.contains(methodName)) {
            System.out.println("executed method: " + method.getName() + ", param: " +
                    Arrays.stream(args).map(Object::toString).collect(Collectors.joining(", ")));
        }
        return method.invoke(testLogging, args);
    }

    public static Set<String> getAnnotatedMethods(Class<? extends Annotation> annotationClass, Class<?> myClass) {
        Set<String> annotatedMethods = new HashSet<>();
        Method[] methods = myClass.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                Parameter[] parameters = method.getParameters();
                annotatedMethods.add(method.getName() + Arrays.toString(parameters));
            }
        }

        if (annotatedMethods.isEmpty()) {
            System.out.println("No annotated methods found !");
            return Collections.emptySet();
        }

        return annotatedMethods;
    }

}
