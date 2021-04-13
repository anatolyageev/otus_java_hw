package ru.otus.java.ageev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.otus.java.ageev.domain.AddressDataSet;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.domain.PhoneDataSet;
import ru.otus.java.ageev.repositiry.DataTemplateHibernate;
import ru.otus.java.ageev.repositiry.HibernateUtils;
import ru.otus.java.ageev.repositiry.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.java.ageev.service.DBServiceClient;
import ru.otus.java.ageev.service.DbServiceClientImpl;
import ru.otus.java.ageev.service.TemplateProcessor;
import ru.otus.java.ageev.service.TemplateProcessorImpl;
import ru.otus.java.ageev.service.UserAuthService;
import ru.otus.java.ageev.service.UserAuthServiceImpl;
import ru.otus.java.ageev.sessionmanager.TransactionManagerHibernate;
import ru.otus.java.ageev.web.server.UsersWebServer;
import ru.otus.java.ageev.web.server.UsersWebServerWithFilterBasedSecurity;

public class Context {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    private final Configuration configuration;
    private final SessionFactory sessionFactory;
    private final TransactionManagerHibernate transactionManager;
    private final DataTemplateHibernate<Client> clientTemplate;
    private final DBServiceClient serviceClient;
    private final Gson gson;
    private final TemplateProcessor templateProcessor;
    private final UserAuthService authService;

    public Context() throws IOException {
        configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
        new MigrationsExecutorFlyway(configuration).executeMigrations();
        sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, AddressDataSet.class, PhoneDataSet.class);
        transactionManager = new TransactionManagerHibernate(sessionFactory);
        clientTemplate = new DataTemplateHibernate<>(Client.class);
        serviceClient = new DbServiceClientImpl(transactionManager, clientTemplate);
        gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        authService = new UserAuthServiceImpl(serviceClient);
    }

    public UsersWebServer getWebServer() {
        return new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT, authService, serviceClient,
                gson, templateProcessor);
    }
}
