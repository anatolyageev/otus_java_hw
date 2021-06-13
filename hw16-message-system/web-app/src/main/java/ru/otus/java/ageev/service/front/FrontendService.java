package ru.otus.java.ageev.service.front;

import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.messagesystem.client.MessageCallback;

import java.util.List;
import java.util.Optional;

public interface FrontendService {
    void saveClient(ClientMessageDto client, MessageCallback<ClientMessageDto> dataConsumer);

    void getClient(long id, MessageCallback<ClientMessageDto> dataConsumer);

    void findAll();
}
