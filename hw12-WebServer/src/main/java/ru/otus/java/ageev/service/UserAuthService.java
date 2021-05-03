package ru.otus.java.ageev.service;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
