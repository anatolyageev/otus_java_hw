package ru.otus.listener;

import ru.otus.model.Message;
import ru.otus.model.MessageHistory;
import ru.otus.service.HistoryService;
import ru.otus.utils.ListnersHelper;

public class ListenerHistory implements Listener {

    private final HistoryService historyService;

    public ListenerHistory(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        Message archiveMsg = ListnersHelper.copy(oldMsg, Message.class);
        historyService.addHistory(new MessageHistory(archiveMsg, newMsg));
    }

    public Message findMessageById(Long id) {
        return historyService.getHistoryById(id).getOldMessage();
    }
}
