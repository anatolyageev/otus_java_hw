package ru.otus.repository.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import ru.otus.model.Message;
import ru.otus.repository.HistoryRepository;

public class HistoryRepositoryImpl implements HistoryRepository {
    private Map<Long, Message> history;

    public HistoryRepositoryImpl() {
        this.history = new HashMap<>();
    }

    @Override
    public Message addHistory(Message message) {
        return history.put(message.getId(), message);
    }

    @Override
    public Message getHistoryById(long id) {
        return history.get(id);
    }

    @Override
    public Map<Long, Message> getAllHistory() {
        return Collections.unmodifiableMap(history);
    }
}
