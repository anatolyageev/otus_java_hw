package ru.otus.java.ageev.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.java.ageev.cachehw.HwCache;
import ru.otus.java.ageev.domain.Client;
import ru.otus.java.ageev.repositiry.DataTemplate;
import ru.otus.java.ageev.sessionmanager.TransactionManager;

public class DbServiceClientCashedImpl implements DBServiceClient {
    private static final Logger log = LoggerFactory.getLogger(DbServiceClientImpl.class);

    private final DataTemplate<Client> clientDataTemplate;
    private final TransactionManager transactionManager;
    private final HwCache<String, Client> hwCache;

    public DbServiceClientCashedImpl(TransactionManager transactionManager, DataTemplate<Client> clientDataTemplate, HwCache<String, Client> hwCache) {
        this.transactionManager = transactionManager;
        this.clientDataTemplate = clientDataTemplate;
        this.hwCache = hwCache;
    }

    @Override
    public Client saveClient(Client client) {
        return transactionManager.doInTransaction(session -> {
            if (client.getId() == null) {
                clientDataTemplate.insert(session, client);
                hwCache.put(client.getId().toString(), client);
                log.info("created client: {}", client);
                return client;
            }
            clientDataTemplate.update(session, client);
            log.info("updated client: {}", client);
            return client;
        });
    }

    @Override
    public Optional<Client> getClient(long id) {
        return transactionManager.doInTransaction(session -> {
           Client client = hwCache.get(String.valueOf(id));
            if (Objects.nonNull(client)) {
                log.info("client from cache: {}", client);
                return Optional.of(client);
            } else {
                var clientOptional = clientDataTemplate.findById(session, id);
                log.info("client: {}", clientOptional);
                return clientOptional;
            }
        });
    }

    @Override
    public List<Client> findAll() {
        return transactionManager.doInTransaction(session -> {
            var clientList = clientDataTemplate.findAll(session);
            log.info("clientList:{}", clientList);
            return clientList;
        });
    }
}
