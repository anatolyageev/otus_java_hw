package ru.otus.java.ageev.service.db.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.dto.ClientMessageDto;
import ru.otus.java.ageev.repository.ClientRepository;
import ru.otus.java.ageev.service.db.DBService;
import ru.otus.java.ageev.sessionmanager.TransactionManager;

import java.util.List;

@Service
public class DBServiceImpl implements DBService {
    private static final Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    public final ClientRepository clientRepository;
    private final TransactionManager transactionManager;

    @Autowired
    public DBServiceImpl(ClientRepository clientRepository, TransactionManager transactionManager) {
        this.clientRepository = clientRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionManager.doInTransaction(() -> {
            logger.info("saved client: {}", client);
            var savedClient = clientRepository.save(client);
            logger.info("saved client: {}", savedClient);
            return savedClient;
        });
    }

    @Override
    public ClientMessageDto getClient(long id) {
        logger.info("getClient by id: {}", id);
        ClientMessageDto clientMessageDto = new ClientMessageDto(clientRepository.findById(id).get());
        logger.info("clientMessageDto: {}", clientMessageDto);
        return clientMessageDto;
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }
}
