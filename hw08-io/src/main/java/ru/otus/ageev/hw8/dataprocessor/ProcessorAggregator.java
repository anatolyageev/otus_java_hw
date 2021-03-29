package ru.otus.ageev.hw8.dataprocessor;

import java.util.List;
import java.util.Map;
import ru.otus.ageev.hw8.model.Measurement;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value
        return null;
    }
}
