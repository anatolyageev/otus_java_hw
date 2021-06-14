package ru.otus.java.ageev.service.front.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.java.ageev.dto.ClientList;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.java.ageev.service.front.FrontendService;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageType;

import java.util.ArrayList;

@Service
public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);
    private final MsClient msClient;
    private final String databaseService;

    public FrontendServiceImpl(@Qualifier("frontClient") MsClient msClient, @Value("${app.dbService}") String databaseService) {
        this.msClient = msClient;
        this.databaseService = databaseService;
    }

    @Override
    public void saveClient(ClientMessageDto client, MessageCallback<ClientMessageDto> dataConsumer) {
        logger.info("saveClient() clientDto: {}", client.toString());
        Message outMsg = msClient.produceMessage(databaseService, client, MessageType.USER_SAVE, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void getClient(long id, MessageCallback<ClientMessageDto> dataConsumer) {
        ClientMessageDto clientDto = new ClientMessageDto();
        logger.info("getClient() clientDto: {}", clientDto.toString());
        clientDto.setId(id);
        logger.info("getClient() clientDto: {}", clientDto.toString());
        Message outMsg = msClient.produceMessage(databaseService, clientDto, MessageType.USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void findAll(MessageCallback<ClientList> clientListMessageCallback) {
        logger.info("findAll() clientListMessageCallback: {}", clientListMessageCallback);
        Message outMsg = msClient.produceMessage(databaseService, new ClientList(new ArrayList<>()), MessageType.USER_ALL, clientListMessageCallback);
        msClient.sendMessage(outMsg);
    }
}
