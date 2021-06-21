package ru.otus.java.ageev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import ru.otus.java.ageev.dto.ClientList;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.java.ageev.service.ClientService;
import ru.otus.java.ageev.service.front.FrontendService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        frontendService.findAll(this::replyGetAll);
    }


    @MessageMapping("/request/{id}")
    public void getClientById(@DestinationVariable long id) {
        frontendService.getClient(id, this::replyGet);
    }

    @MessageMapping("/request/save")
    public void createClient(ClientMessageDto clientDto) {
        logger.info("clientDto: {}", clientDto);
        frontendService.saveClient(clientDto, this::replySave);
        getAll();
    }

    private void replyGet(ClientMessageDto client) {
        template.convertAndSend("/topic/userId", nonNull(client) ? client : "Not found");
    }

    private void replySave(ClientMessageDto client) {
        template.convertAndSend("/topic/saveUser", client);
    }

    private void replyGetAll(ClientList clientList) {
        template.convertAndSend("/topic/allUsers", clientList);
    }
}
