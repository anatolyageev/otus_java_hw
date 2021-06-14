package ru.otus.java.ageev.service.db.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.java.ageev.dto.ClientList;
import ru.otus.java.ageev.service.db.DBService;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;

import java.util.Optional;


@Service("allClientDataRequestHandler")
public class GetClientListHandler implements RequestHandler<ClientList> {
    private static final Logger logger = LoggerFactory.getLogger(GetClientListHandler.class);
    private final DBService dbService;

    public GetClientListHandler(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        ClientList clientList = new ClientList(dbService.findAll());
        logger.info("clientList {}", clientList);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, clientList));
    }
}
