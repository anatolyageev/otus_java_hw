package ru.otus.java.ageev.web.servlet;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ru.otus.java.ageev.service.DBServiceClient;
import ru.otus.java.ageev.service.TemplateProcessor;


public class UsersServlet extends HttpServlet {
    private static final String USERS_PAGE_TEMPLATE = "users.ftl";
    private static final String ALL_USERS = "allUsers";


    private final DBServiceClient userDao;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, DBServiceClient userDao) {
        this.templateProcessor = templateProcessor;
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(ALL_USERS, userDao.findAll());
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

}
