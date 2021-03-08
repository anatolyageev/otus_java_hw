package ru.otus.service.impl;

import java.util.Map;
import ru.otus.model.Message;
import ru.otus.repository.HistoryRepository;

public class HistoryServiceImpl implements ru.otus.service.HistoryService {
    private HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public Message addHistory(Message message) {
        return historyRepository.addHistory(message);
    }

    @Override
    public Message getHistoryById(long id) {
        return historyRepository.getHistoryById(id);
    }

    @Override
    public Map<Long, Message> getAllHistory() {
        return historyRepository.getAllHistory();
    }
}
