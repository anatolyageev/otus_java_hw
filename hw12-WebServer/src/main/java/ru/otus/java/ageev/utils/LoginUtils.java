package ru.otus.java.ageev.utils;

import jakarta.servlet.http.HttpServletRequest;
import ru.otus.java.ageev.dto.ClientDto;

public class LoginUtils {
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";
    public static final String LOGIN_USER = "login";

    public static ClientDto getUserFromForm(HttpServletRequest request) {
        return new ClientDto(
                request.getParameter(USER_NAME),
                request.getParameter(LOGIN_USER),
                request.getParameter(USER_PASSWORD));
    }

}
