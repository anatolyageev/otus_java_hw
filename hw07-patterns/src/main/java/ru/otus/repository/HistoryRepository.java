package ru.otus.repository;

import java.util.Map;
import ru.otus.model.Message;
import ru.otus.model.MessageHistory;

public interface HistoryRepository {
    MessageHistory addHistory(MessageHistory messageHistory);

    MessageHistory getHistoryById(long id);

    Map<Long, MessageHistory> getAllHistory();
}
