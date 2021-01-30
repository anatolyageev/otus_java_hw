package com.otus.ageev.mytestframework;

import com.otus.ageev.annotation.After;
import com.otus.ageev.annotation.Before;
import com.otus.ageev.annotation.Test;
import com.otus.ageev.reflection.ReflectionHelper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MyTestFramework {
    private List<Method> beforeMethods;
    private List<Method> testMethods;
    private List<Method> afterMethods;

    public void testAll(String className) throws ClassNotFoundException {
        int successCount = 0;
        int failureCount = 0;
        Class<?> testClass = Class.forName(className);
        getAnnotatedMethods(testClass);
        Object obj = null;
        for (Method testMethod : testMethods) {
            try {
                obj = testClass.getDeclaredConstructor().newInstance();
                invokeMethods(obj, beforeMethods);
                testMethod.invoke(obj);
                successCount++;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                failureCount++;
            } finally {
                try {
                    invokeMethods(obj, afterMethods);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Test count: " + testMethods.size());
        System.out.println("Success test count: " + successCount);
        System.out.println("Failure test count: " + failureCount);
    }

    private void invokeMethods(Object obj, List<Method> methods) throws IllegalAccessException, InvocationTargetException {
        for (Method method : methods) {
            method.invoke(obj);
        }
    }

    private void getAnnotatedMethods(Class<?> testClass) {
        beforeMethods = ReflectionHelper.getAnnotationMethods(Before.class, testClass);
        testMethods = ReflectionHelper.getAnnotationMethods(Test.class, testClass);
        afterMethods = ReflectionHelper.getAnnotationMethods(After.class, testClass);
    }
}
