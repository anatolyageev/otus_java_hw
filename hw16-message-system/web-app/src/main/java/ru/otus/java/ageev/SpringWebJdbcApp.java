package ru.otus.java.ageev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebJdbcApp {
    public static void main(String[] args) {
        var context =   SpringApplication.run(SpringWebJdbcApp.class, args);
    }
}
