package ru.otus.service;

import java.util.Map;
import ru.otus.model.Message;
import ru.otus.model.MessageHistory;

public interface HistoryService {
    MessageHistory addHistory(MessageHistory messageHistory);

    MessageHistory getHistoryById(long id);

    Map<Long, MessageHistory> getAllHistory();
}
