package ru.otus.listener;

import ru.otus.model.Message;
import ru.otus.service.HistoryService;

public class ListenerHistory implements Listener {

    private HistoryService historyService;

    public ListenerHistory(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        historyService.addHistory(oldMsg);
    }
}
