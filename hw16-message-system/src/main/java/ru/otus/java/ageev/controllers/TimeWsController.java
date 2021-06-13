package ru.otus.java.ageev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.service.ClientService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.otus.java.ageev.config.WebSocketConfig.DATE_TIME_FORMAT;

@Controller
public class TimeWsController {
    private final SimpMessagingTemplate template;
    private final ClientService clientService;

    @Autowired
    public TimeWsController(SimpMessagingTemplate template, ClientService clientService) {
        this.template = template;
        this.clientService = clientService;
    }

    @Scheduled(fixedDelay = 1000)
    public void broadcastCurrentTime() {
        template.convertAndSend("/topic/currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
    }

    @Scheduled(fixedDelay = 1000)
    public void getAll() {
       List<Client> clientList = clientService.findAll();
        template.convertAndSend("/topic/allUsers", clientList.toString());
    }

    @MessageMapping("/request/{id}")
    public void getClientById(@DestinationVariable long id) {
       Client client = clientService.getClient(id).get();
        template.convertAndSend("/topic/userId", client.toString());
    }


}
