package com.java.otus.ageev;

import com.java.otus.ageev.domain.TestLogging;
import com.java.otus.ageev.proxy.ProxyTestLoggingFactory;

public class Demo {
    public static void main(String[] args) {
        TestLogging testLogging = ProxyTestLoggingFactory.newTestLogProxy();
        testLogging.calculation(7);
        testLogging.calculation(7,8);
        testLogging.calculation(7,8,"Three");
    }
}
