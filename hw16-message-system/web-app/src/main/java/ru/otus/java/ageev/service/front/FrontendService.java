package ru.otus.java.ageev.service.front;

import ru.otus.java.ageev.dto.ClientList;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.messagesystem.client.MessageCallback;

public interface FrontendService {
    void saveClient(ClientMessageDto client, MessageCallback<ClientMessageDto> dataConsumer);

    void getClient(long id, MessageCallback<ClientMessageDto> dataConsumer);

    void findAll(MessageCallback<ClientList> clientListMessageCallback);
}
