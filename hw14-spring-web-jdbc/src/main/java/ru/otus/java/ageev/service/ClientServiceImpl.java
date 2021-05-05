package ru.otus.java.ageev.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.repository.ClientRepository;
import ru.otus.java.ageev.sessionmanager.TransactionManager;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final TransactionManager transactionManager;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, TransactionManager transactionManager) {
        this.clientRepository = clientRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public Client saveClient(Client client) {
        return null;
    }

    @Override
    public Optional<Client> getClient(long id) {
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Optional<Client> findByLogin(String login) {
        return Optional.empty();
    }
}
