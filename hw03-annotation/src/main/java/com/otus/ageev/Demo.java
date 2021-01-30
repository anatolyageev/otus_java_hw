package com.otus.ageev;

import com.otus.ageev.mytestframework.MyTestFramework;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        MyTestFramework myTestFramework = new MyTestFramework();
        myTestFramework.testAll(TestClass.class.getName());
    }
}
