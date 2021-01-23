package com.otus.ageev.hw;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;

import java.util.List;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar ./L01-gradle/build/libs/gradleHelloWorld-0.1.jar
 *
 * To unzip the jar:
 * unzip -l L01-gradle.jar
 * unzip -l gradleHelloWorld-0.1.jar
 *
 * To build:
 * ./gradlew build
 */

public class HelloOtus {
    public static void main(String[] args) {
        List<String> universityRowTable
                = Lists.newArrayList("Mumbai", "Harvard");
        List<String> courseColumnTables
                = Lists.newArrayList("Chemical", "IT", "Electrical");
        Table<String, String, Integer> universityCourseSeatTable
                = ArrayTable.create(universityRowTable, courseColumnTables);
        System.out.println(universityCourseSeatTable);
    }
}
