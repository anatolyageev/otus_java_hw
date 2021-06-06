package ru.otus.java.ageev.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.dto.ClientDto;
import ru.otus.java.ageev.service.ClientService;

@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/users")
    public String clientList(Model model) {
        List<Client> clientList = clientService.findAll();
        model.addAttribute("allUsers", clientList);
        return "users";
    }

    @GetMapping("/addClient")
    public String addClient() {
        return "addClient";
    }

    @PostMapping("/addClient")
    public String createClient(@ModelAttribute ClientDto clientDto) {
        Client client = clientDto.getClient();
        clientService.saveClient(client);
        return "redirect:/users";
    }
}
