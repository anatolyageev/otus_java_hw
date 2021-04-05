package ru.otus.ageev.hw8.dataprocessor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import ru.otus.ageev.hw8.model.Measurement;

public class ProcessorAggregator implements Processor {

    /**
     * группирует выходящий список по name, при этом суммирует поля value
     *
     * @param data
     * @return
     */
    @Override
    public Map<String, Double> process(List<Measurement> data) {
        return data.stream()
                .collect(Collectors
                        .groupingBy(Measurement::getName, TreeMap::new,
                                Collectors.summingDouble(Measurement::getValue)));
    }
}