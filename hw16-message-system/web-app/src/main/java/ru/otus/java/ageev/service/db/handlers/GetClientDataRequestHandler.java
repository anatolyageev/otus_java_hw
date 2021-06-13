package ru.otus.java.ageev.service.db.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.java.ageev.service.db.DBService;
import ru.otus.messagesystem.RequestHandler;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.message.MessageHelper;
import ru.otus.messagesystem.message.MessageType;

import java.util.Optional;

@Service("clientDataRequestHandler")
public class GetClientDataRequestHandler implements RequestHandler<ClientMessageDto> {
    private static final Logger logger = LoggerFactory.getLogger(GetClientDataRequestHandler.class);
    private final DBService dbService;

    public GetClientDataRequestHandler(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("msg: {}", msg);
        ClientMessageDto client = MessageHelper.getPayload(msg);
        logger.info("client: {}", client);

        if (MessageType.USER_DATA.getName().equals(msg.getType())) {
            return getClient(msg, client);
        }

        return Optional.empty();
    }

    private Optional<Message> getClient(Message msg, ClientMessageDto client) {
        ClientMessageDto clientData = dbService.getClient(client.getId());
        logger.info("clientData {}", clientData);
        return Optional.of(MessageBuilder.buildReplyMessage(msg, clientData));
    }

}
