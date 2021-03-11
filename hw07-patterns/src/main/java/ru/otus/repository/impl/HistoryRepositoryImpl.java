package ru.otus.repository.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import ru.otus.model.MessageHistory;
import ru.otus.repository.HistoryRepository;

public class HistoryRepositoryImpl implements HistoryRepository {
    private final Map<Long, MessageHistory> history;

    public HistoryRepositoryImpl() {
        this.history = new HashMap<>();
    }

    @Override
    public MessageHistory addHistory(MessageHistory messageHistory) {
        return history.put(messageHistory.getOldMessage().getId(), messageHistory);
    }

    @Override
    public MessageHistory getHistoryById(long id) {
        return history.get(id);
    }

    @Override
    public Map<Long, MessageHistory> getAllHistory() {
        return Collections.unmodifiableMap(history);
    }
}
