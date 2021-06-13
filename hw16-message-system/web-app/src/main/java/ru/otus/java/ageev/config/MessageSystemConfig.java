package ru.otus.java.ageev.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.messagesystem.*;
import ru.otus.messagesystem.client.CallbackRegistry;
import ru.otus.messagesystem.client.CallbackRegistryImpl;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.client.MsClientImpl;
import ru.otus.messagesystem.message.MessageType;

@Configuration
public class MessageSystemConfig {
    @Value("${app.dbService}")
    private String dbService;
    @Value("${app.frontendService}")
    private String frontendService;

    @Bean
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public CallbackRegistry callbackRegistry() {
        return new CallbackRegistryImpl();
    }

    @Bean("frontHandlerStore")
    public HandlersStore frontHandlerStore(@Qualifier("clientDataResponseHandler") RequestHandler<ClientMessageDto> handler) {
        var store = new HandlersStoreImpl();
        store.addHandler(MessageType.USER_DATA, handler);
//        store.addHandler(MessageType.USER_SAVE, handler);
        return store;
    }

    @Bean("dbHandlerStore")
    public HandlersStore dbHandlerStore(@Qualifier("clientDataRequestHandler") RequestHandler<ClientMessageDto> handler) {
        var store = new HandlersStoreImpl();
        store.addHandler(MessageType.USER_DATA, handler);
//        store.addHandler(MessageType.USER_SAVE, handler);
        return store;
    }

    @Bean("backClient")
    public MsClient backClient(MessageSystem messageSystem,
                               @Qualifier("dbHandlerStore") HandlersStore store,
                               CallbackRegistry registry) {
        var client = new MsClientImpl(dbService, messageSystem, store, registry);
        messageSystem.addClient(client);
        return client;
    }

    @Bean("frontClient")
    public MsClient frontClient(MessageSystem messageSystem,
                                @Qualifier("frontHandlerStore") HandlersStore store,
                                CallbackRegistry registry) {
        var client = new MsClientImpl(frontendService, messageSystem, store, registry);
        messageSystem.addClient(client);
        return client;
    }
}
