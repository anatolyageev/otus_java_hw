package com.java.otus.ageev.proxy;

import com.java.otus.ageev.domain.TestLogging;
import com.java.otus.ageev.domain.TestLoggingImpl;
import com.java.otus.ageev.invocationhandler.TestLoggingInvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTestLoggingFactory{

    public static TestLogging newTestLogProxy() {
        TestLoggingInvocationHandler handler = new TestLoggingInvocationHandler(new TestLoggingImpl());
        return (TestLogging)Proxy.newProxyInstance(TestLoggingImpl.class.getClassLoader(),
                TestLoggingImpl.class.getInterfaces(),handler);
    }
}
