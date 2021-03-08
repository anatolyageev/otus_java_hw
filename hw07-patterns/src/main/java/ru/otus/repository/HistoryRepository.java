package ru.otus.repository;

import java.util.Map;
import ru.otus.model.Message;

public interface HistoryRepository {
    Message addHistory(Message message);

    Message getHistoryById(long id);

    Map<Long, Message> getAllHistory();
}
