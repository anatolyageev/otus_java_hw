package com.java.otus.ageev;

import com.java.otus.ageev.annotation.Log;
import com.java.otus.ageev.domain.TestLogging;
import com.java.otus.ageev.proxy.ProxyTestLoggingFactory;
import com.java.otus.ageev.proxy.ProxyTestLoggingFactorySecond;

public class Demo {
    public static void main(String[] args) {
        TestLogging testLogging = ProxyTestLoggingFactory.newTestLogProxy(Log.class);
        testLogging.calculation(7);
        testLogging.calculation(7, 8);
        testLogging.calculation(7, 8, "Three");
        TestLogging testLogging2 = ProxyTestLoggingFactorySecond.newTestLogProxy(Log.class);
        testLogging2.calculation(2);
        testLogging2.calculation(2, 3);
        testLogging2.calculation(2, 3, "Second");

    }
}
