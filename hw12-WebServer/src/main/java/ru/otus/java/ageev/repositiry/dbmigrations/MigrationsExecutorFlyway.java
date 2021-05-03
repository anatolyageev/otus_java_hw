package ru.otus.java.ageev.repositiry.dbmigrations;

import org.flywaydb.core.Flyway;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MigrationsExecutorFlyway {
    private static final Logger logger = LoggerFactory.getLogger(MigrationsExecutorFlyway.class);

    private final Flyway flyway;

    public MigrationsExecutorFlyway(Configuration configuration) {
        String dbUrl = configuration.getProperty("hibernate.connection.url");
        String dbUserName = configuration.getProperty("hibernate.connection.username");
        String dbPassword = configuration.getProperty("hibernate.connection.password");

        flyway = Flyway.configure()
                .dataSource(dbUrl, dbUserName, dbPassword)
                .baselineOnMigrate(true)
                .locations("classpath:/db/migration")
                .load();
    }

    public void executeMigrations() {
        logger.info("db migration started...");
        flyway.migrate();
        logger.info("db migration finished.");
    }
}
