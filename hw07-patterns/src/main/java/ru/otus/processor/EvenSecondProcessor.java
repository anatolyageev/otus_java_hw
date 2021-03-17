package ru.otus.processor;

import ru.otus.exeption.EvenSecondException;
import ru.otus.model.Message;

public class EvenSecondProcessor implements Processor {
    private final DateTimeProvider dateTimeProvider;

    public EvenSecondProcessor(DateTimeProvider dateTimeProvider) {

        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {

        if (dateTimeProvider.getDate().getSecond() % 2 == 0) {
            throw new EvenSecondException("Is even second occurred");
        }
        return message;
    }

}
