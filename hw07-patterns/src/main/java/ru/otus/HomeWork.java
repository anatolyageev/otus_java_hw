package ru.otus;

import java.time.LocalDateTime;
import java.util.List;
import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerHistory;
import ru.otus.listener.ListenerPrinter;
import ru.otus.model.Message;
import ru.otus.processor.ChangeOrderProcessor;
import ru.otus.processor.EvenSecondProcessor;
import ru.otus.repository.impl.HistoryRepositoryImpl;
import ru.otus.service.impl.HistoryServiceImpl;

public class HomeWork {

    public static void main(String[] args) {
        var historyRepository = new HistoryRepositoryImpl();
        var historyService = new HistoryServiceImpl(historyRepository);

        var processor1 = new ChangeOrderProcessor();
        var processor2 = new EvenSecondProcessor(LocalDateTime::now);
        var processors = List.of(processor1, processor2);
        var listener1 = new ListenerPrinter();
        var listener2 = new ListenerHistory(historyService);

        var handler = new ComplexProcessor(processors, System.err::println);

        handler.addListener(listener1);
        handler.addListener(listener2);

        Message message = Message.builder()
                .id(1)
                .field1("Test")
                .field11("Field11")
                .field12("Field12")
                .build();

        var result = handler.handle(message);

        System.out.println("result:" + result);

        System.out.println("From history: " + historyService.getHistoryById(1).getOldMessage());

        handler.removeListener(listener1);
        handler.removeListener(listener2);
    }
}
