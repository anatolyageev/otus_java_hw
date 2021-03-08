package ru.otus.processor;

import java.time.LocalDateTime;
import java.util.Objects;
import ru.otus.exeption.EvenSecondException;
import ru.otus.model.Message;

public class EvenSecondProcessorWrapper implements Processor {
    private final Processor processor;
    private LocalDateTime localDateTime;

    public EvenSecondProcessorWrapper(Processor processor) {
        this.processor = processor;
    }

    @Override
    public Message process(Message message) {
        if (Objects.isNull(localDateTime)) {
            localDateTime = LocalDateTime.now();
        }
        if (localDateTime.getSecond() % 2 == 0) {
            throw new EvenSecondException("Is even second occurred");
        }
        return processor.process(message);
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
