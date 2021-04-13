package ru.otus.java.ageev.hw8.dataprocessor;

import java.util.List;
import ru.otus.java.ageev.hw8.model.Measurement;

public interface Loader {

    List<Measurement> load();
}
