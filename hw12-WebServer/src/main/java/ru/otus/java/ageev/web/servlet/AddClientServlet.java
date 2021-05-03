package ru.otus.java.ageev.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import ru.otus.java.ageev.dto.ClientDto;
import ru.otus.java.ageev.service.DBServiceClient;
import ru.otus.java.ageev.service.TemplateProcessor;
import ru.otus.java.ageev.utils.LoginUtils;

public class AddClientServlet extends HttpServlet {
    private static final String ADD_CLIENT_URL = "addClient.ftl";

    private final TemplateProcessor templateProcessor;
    private final DBServiceClient dbServiceClient;

    public AddClientServlet(TemplateProcessor templateProcessor, DBServiceClient dbServiceClient) {
        this.templateProcessor = templateProcessor;
        this.dbServiceClient = dbServiceClient;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println(templateProcessor.getPage(ADD_CLIENT_URL, Collections.emptyMap()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientDto clientData = LoginUtils.getUserFromForm(req);

        dbServiceClient.saveClient(clientData.getClient());
        resp.sendRedirect("/users");
    }
}
