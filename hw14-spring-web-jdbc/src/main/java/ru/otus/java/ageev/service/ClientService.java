package ru.otus.java.ageev.service;

import java.util.List;
import java.util.Optional;
import ru.otus.java.ageev.domain.Client;


public interface ClientService {
    Client saveClient(Client client);

    Optional<Client> getClient(long id);

    List<Client> findAll();
}
