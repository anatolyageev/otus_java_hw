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
    private int testCount;
    private int successCount;
    private int failureCount;

    public void testAll(String className) throws ClassNotFoundException {
        Class<?> testClass = Class.forName(className);
        getAnnotatedMethods(testClass);

        for (Method testMethod : testMethods) {
            try {
                Object obj = testClass.getDeclaredConstructor().newInstance();
                invokeMethods(obj, beforeMethods);
                testMethod.invoke(obj);
                invokeMethods(obj, afterMethods);
                successCount++;
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                failureCount++;
            } finally {
                testCount++;
            }
        }
        printResult();
    }

    private void printResult() {
        System.out.println("Test count: " + testCount);
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
