package ru.otus.ageev.hw8.dataprocessor;

import java.util.List;
import java.util.Map;
import ru.otus.ageev.hw8.model.Measurement;

public interface Processor {

    Map<String, Double> process(List<Measurement> data);
}
