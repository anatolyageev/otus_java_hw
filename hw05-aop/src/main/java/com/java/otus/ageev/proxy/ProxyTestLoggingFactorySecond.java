package com.java.otus.ageev.proxy;

import com.java.otus.ageev.domain.TestLogging;
import com.java.otus.ageev.domain.TestLoggingImplSecond;
import com.java.otus.ageev.invocationhandler.TestLoggingInvocationHandler;
import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

public class ProxyTestLoggingFactorySecond {
    public static TestLogging newTestLogProxy(Class<? extends Annotation> annotationClass) {
        TestLoggingInvocationHandler handler = new TestLoggingInvocationHandler(annotationClass, new TestLoggingImplSecond());
        return (TestLogging) Proxy.newProxyInstance(TestLoggingImplSecond.class.getClassLoader(),
                TestLoggingImplSecond.class.getInterfaces(), handler);
    }
}
