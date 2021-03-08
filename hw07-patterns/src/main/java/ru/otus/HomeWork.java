package ru.otus;

import java.util.List;
import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerHistory;
import ru.otus.listener.ListenerPrinter;
import ru.otus.model.Message;
import ru.otus.processor.ChangeOrderProcessor;
import ru.otus.processor.EvenSecondProcessor;
import ru.otus.processor.EvenSecondProcessorWrapper;
import ru.otus.repository.impl.HistoryRepositoryImpl;
import ru.otus.service.impl.HistoryServiceImpl;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
            Секунда должна определяьться во время выполнения.
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */

        var historyRepository = new HistoryRepositoryImpl();
        var historyService = new HistoryServiceImpl(historyRepository);

        var processor1 = new ChangeOrderProcessor();
        var processor2 = new EvenSecondProcessorWrapper(new EvenSecondProcessor());
        var processors = List.of(processor1, processor2);

        var listener1 = new ListenerPrinter();
        var listener2 = new ListenerHistory(historyService);

        var handler = new ComplexProcessor(processors, System.err::println);

        handler.addListener(listener1);
        handler.addListener(listener2);

        Message message = new Message.Builder(1)
                .field1("Test")
                .field11("Field11")
                .field12("Field12")
                .build();

        var result = handler.handle(message);

        System.out.println("result:" + result);

        System.out.println("From history: " + historyService.getHistoryById(1));

        handler.removeListener(listener1);
        handler.removeListener(listener2);
    }
}
