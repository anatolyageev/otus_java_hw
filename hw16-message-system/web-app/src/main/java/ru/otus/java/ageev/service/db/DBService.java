package ru.otus.java.ageev.service.db;

import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.dto.ClientMessageDto;

import java.util.List;
import java.util.Optional;

public interface DBService {
    Client saveClient(Client client);

    ClientMessageDto getClient(long id);

    List<Client> findAll();
}
