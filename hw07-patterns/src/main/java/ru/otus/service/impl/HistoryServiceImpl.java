package ru.otus.service.impl;

import java.util.Map;
import ru.otus.model.Message;
import ru.otus.model.MessageHistory;
import ru.otus.repository.HistoryRepository;

public class HistoryServiceImpl implements ru.otus.service.HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public MessageHistory addHistory(MessageHistory messageHistory) {
        return historyRepository.addHistory(messageHistory);
    }

    @Override
    public MessageHistory getHistoryById(long id) {
        return historyRepository.getHistoryById(id);
    }

    @Override
    public Map<Long, MessageHistory> getAllHistory() {
        return historyRepository.getAllHistory();
    }
}
