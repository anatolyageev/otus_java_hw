package ru.otus.processor;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.exeption.EvenSecondException;
import ru.otus.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChangeOrderProcessorTest {
    private Message message;
    private Message messageTemplate;
    private Processor processor;
    private EvenSecondProcessorWrapper evenSecondProcessorWrapper;

    @BeforeEach
    public void init() {
        message = new Message.Builder(1)
                .field11("Field11")
                .field12("Field12")
                .build();

        messageTemplate = new Message.Builder(1)
                .field11("Field12")
                .field12("Field11")
                .build();
    }


    @Test
    @DisplayName("Test for change feld processor")
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
        evenSecondProcessorWrapper = new EvenSecondProcessorWrapper(processor);

        //when
        evenSecondProcessorWrapper.setLocalDateTime(LocalDateTime.of(2021, 3, 8, 20, 30, 30));

        //then
        assertThrows(EvenSecondException.class, () -> evenSecondProcessorWrapper.process(message));
    }
}