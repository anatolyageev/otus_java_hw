package ru.otus.java.ageev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.java.ageev.service.ClientService;
import ru.otus.java.ageev.service.db.handlers.GetClientDataRequestHandler;
import ru.otus.java.ageev.service.front.FrontendService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Objects.nonNull;
import static ru.otus.java.ageev.config.WebSocketConfig.DATE_TIME_FORMAT;

@Controller
public class TimeWsController {
    private static final Logger logger = LoggerFactory.getLogger(TimeWsController.class);
    private final SimpMessagingTemplate template;
    private final FrontendService frontendService;
    private final ClientService clientService;

    @Autowired
    public TimeWsController(SimpMessagingTemplate template, FrontendService frontendService, ClientService clientService) {
        this.template = template;
        this.frontendService = frontendService;
        this.clientService = clientService;
    }

    @Scheduled(fixedDelay = 1000)
    public void broadcastCurrentTime() {
        template.convertAndSend("/topic/currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
    }

    @MessageMapping("/request/all")
    public void getAll() {
       List<Client> clientList = clientService.findAll();
        template.convertAndSend("/topic/allUsers", clientList.toString());
    }

    @MessageMapping("/request/{id}")
    public void getClientById(@DestinationVariable long id) {


        frontendService.getClient(id, this::reply);
    //   Client client = clientService.getClient(id).get();
     //   template.convertAndSend("/topic/userId", client.toString());
    }

    private void reply(ClientMessageDto client) {
        template.convertAndSend("/topic/userId", nonNull(client) ? client.toString() : "Not found");
    }
}
