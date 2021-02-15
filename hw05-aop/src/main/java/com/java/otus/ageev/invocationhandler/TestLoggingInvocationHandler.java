package com.java.otus.ageev.invocationhandler;

import com.java.otus.ageev.annotation.Log;
import com.java.otus.ageev.domain.TestLogging;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestLoggingInvocationHandler implements InvocationHandler {
    private final TestLogging testLogging;

    public TestLoggingInvocationHandler(TestLogging testLogging) {
        this.testLogging = testLogging;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Log.class)) {
            System.out.println("executed method: " + method.getName() + ", param: " +
                    Arrays.stream(args).map(Object::toString).collect(Collectors.joining(", ")));
        }
        return method.invoke(testLogging, args);
    }
}
