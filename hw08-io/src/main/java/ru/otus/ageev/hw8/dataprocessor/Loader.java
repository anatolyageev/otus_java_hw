package ru.otus.ageev.hw8.dataprocessor;

import java.util.List;
import ru.otus.ageev.hw8.model.Measurement;

public interface Loader {

    List<Measurement> load();
}
