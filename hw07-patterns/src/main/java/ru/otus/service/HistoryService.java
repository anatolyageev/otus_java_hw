package ru.otus.service;

import java.util.Map;
import ru.otus.model.Message;

public interface HistoryService {
    Message addHistory(Message message);

    Message getHistoryById(long id);

    Map<Long, Message> getAllHistory();
}
