package com.otus.ageev;

import com.otus.ageev.annotation.After;
import com.otus.ageev.annotation.Before;
import com.otus.ageev.annotation.Test;

public class TestClass {

    @Before
    public void beforeMethod() {
        System.out.println("Before method run");
    }

    @Test
    public void testMethod1() {
        System.out.println("Test method1 run");
    }

    @Test
    public void testMethod2() {
        System.out.println("Test method2 run");
    }

    @Test
    public void testMethod3() {
        System.out.println("Test method3 run");
    }

    @After
    public void AfterMethod() {
        System.out.println("After method run");
    }
}
