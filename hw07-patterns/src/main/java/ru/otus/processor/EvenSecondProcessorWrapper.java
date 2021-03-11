package ru.otus.processor;

import ru.otus.exeption.EvenSecondException;
import ru.otus.model.DateTimeProvider;
import ru.otus.model.Message;

public class EvenSecondProcessorWrapper implements Processor {
    private final Processor processor;
    private final DateTimeProvider dateTimeProvider;

    public EvenSecondProcessorWrapper(Processor processor, DateTimeProvider dateTimeProvider) {
        this.processor = processor;
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {

        if (dateTimeProvider.getDate().getSecond() % 2 == 0) {
            throw new EvenSecondException("Is even second occurred");
        }
        return processor.process(message);
    }

}
