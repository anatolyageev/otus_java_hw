package com.java.otus.ageev.domain;

import com.java.otus.ageev.annotation.Log;

public class TestLoggingImpl implements TestLogging {
    @Log
    public void calculation(int param1) {

    }

    @Log
    public void calculation(int param1, int param2) {

    }

    @Log
    public void calculation(int param1, int param2, String param3) {

    }
}
