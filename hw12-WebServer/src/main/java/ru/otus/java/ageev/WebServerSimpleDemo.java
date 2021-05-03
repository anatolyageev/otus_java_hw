package ru.otus.java.ageev;

import ru.otus.java.ageev.web.server.UsersWebServer;

public class WebServerSimpleDemo {
    public static void main(String[] args) throws Exception {
        Context context = new Context();

        UsersWebServer usersWebServer = context.getWebServer();

        usersWebServer.start();
        usersWebServer.join();
    }
}
