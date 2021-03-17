package ru.otus.processor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.exeption.EvenSecondException;
import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.Listener;
import ru.otus.listener.ListenerHistory;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.repository.HistoryRepository;
import ru.otus.repository.impl.HistoryRepositoryImpl;
import ru.otus.service.HistoryService;
import ru.otus.service.impl.HistoryServiceImpl;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangeOrderProcessorTest {
    private Message message;
    private Message messageTemplate;
    private Processor processor;
    private EvenSecondProcessor evenSecondProcessorWrapper;
    private HistoryService historyService;
    private HistoryRepository historyRepository;

    @BeforeEach
    public void init() {
        historyRepository = new HistoryRepositoryImpl();
        historyService = new HistoryServiceImpl(historyRepository);
        message = Message.builder()
                .id(1)
                .field11("Field11")
                .field12("Field12")
                .build();

        messageTemplate = Message.builder()
                .id(1)
                .field11("Field12")
                .field12("Field11")
                .build();
    }


    @Test
    @DisplayName("Test for change field processor")
    public void changeOrderProcessor_ShouldReturnTrue() {
        //given
        processor = new ChangeOrderProcessor();

        //when
        message = processor.process(message);

        //then
        assertEquals(message, messageTemplate);
    }


    @Test
    @DisplayName("Test for check even second")
    public void evenSecondProcessor_ShouldThrowException() {
        //given
        processor = new ChangeOrderProcessor();
        evenSecondProcessorWrapper = new EvenSecondProcessor(()
                -> LocalDateTime.of(2021, 3, 8, 20, 30, 30));

        //when


        //then
        assertThrows(EvenSecondException.class, () -> evenSecondProcessorWrapper.process(message));
    }

    @Test
    @DisplayName("Test for check if old message stays without changes")
    public void changeOrderProcessor_InHistoryShouldRemainOriginal_CheckShouldReturnTrue() {
        //given
        Listener listener = new ListenerHistory(historyService);
        processor = new ChangeOrderProcessor();
        Message originalMessage = message;
        ComplexProcessor complexProcessor = new ComplexProcessor(List.of(processor), (ex) -> {
        });
        complexProcessor.addListener(listener);
        //when
        complexProcessor.handle(message);
        Message oldMessage = historyService.getHistoryById(message.getId()).getOldMessage();
        //then
        assertEquals(originalMessage, oldMessage);
    }

    @Test
    @DisplayName("Test for check if old message stays without changes")
    public void changeOrderProcessor_InHistoryShouldRemainOriginal_CheckShouldReturnTrue2() {
        //given
        var list1 = new ArrayList<String>();
        list1.add("44");
        var obj = new ObjectForMessage();
        obj.setData(list1);

        Message oldMessage = Message.builder()
                .id(1)
                .field13(obj)
                .build();

        Listener listener = new ListenerHistory(historyService);

        //when
        listener.onUpdated(oldMessage, oldMessage);
        obj.setData(new ArrayList<>());

        Message msg = historyService.getHistoryById(oldMessage.getId()).getOldMessage();

        //then
        assertNotEquals(oldMessage, msg);
    }

    @Test
    void ListenerTest() {
        //given
        var historyListener = new ListenerHistory(historyService);

        var id = 2L;
        var data = "33";
        var field13 = new ObjectForMessage();
        field13.setData(List.of(data));
        System.out.println("Field13 object: " + field13.getData());
        var message2 = Message
                .builder()
                .id(id)
                .field10("field10")
                .field13(field13)
                .build();
        System.out.println("message: " + message2);
        //when
        historyListener.onUpdated(message2, message2);
        message2.getField13().setData(new ArrayList<>()); //меняем исходное сообщение

        //then
        var messageFromHistory = historyListener.findMessageById(id);
        System.out.println(messageFromHistory.getField13().getData());
        System.out.println(data);
        assertThat(messageFromHistory).isNotNull();
        assertThat(messageFromHistory.getField13().getData()).containsExactly(data);
    }
}