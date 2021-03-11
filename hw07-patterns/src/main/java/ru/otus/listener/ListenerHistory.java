package ru.otus.listener;

import ru.otus.model.Message;
import ru.otus.model.MessageHistory;
import ru.otus.service.HistoryService;

public class ListenerHistory implements Listener {

    private final HistoryService historyService;

    public ListenerHistory(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        historyService.addHistory(new MessageHistory(oldMsg, newMsg));
    }
}
