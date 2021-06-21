package ru.otus.java.ageev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.service.ClientService;

@RestController
public class ClientControllerRest {
    private final ClientService clientService;

    @Autowired
    public ClientControllerRest(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/client/{id}")
    public Client getClient (@PathVariable("id") Long id){
        return clientService.getClient(id).get();
    }
}
