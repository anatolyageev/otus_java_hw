package ru.otus.processor;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.exeption.EvenSecondException;
import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.Listener;
import ru.otus.listener.ListenerHistory;
import ru.otus.model.DateTimeProvider;
import ru.otus.model.Message;
import ru.otus.repository.HistoryRepository;
import ru.otus.repository.impl.HistoryRepositoryImpl;
import ru.otus.service.HistoryService;
import ru.otus.service.impl.HistoryServiceImpl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangeOrderProcessorTest {
    private Message message;
    private Message messageTemplate;
    private Processor processor;
    private EvenSecondProcessorWrapper evenSecondProcessorWrapper;
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
        evenSecondProcessorWrapper = new EvenSecondProcessorWrapper(processor, ()
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
}