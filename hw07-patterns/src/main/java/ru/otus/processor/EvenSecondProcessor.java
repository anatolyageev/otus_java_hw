package ru.otus.processor;

import ru.otus.model.Message;

public class EvenSecondProcessor implements Processor {

    @Override
    public Message process(Message message) {
        return message;
    }
}
