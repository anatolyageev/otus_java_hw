package ru.otus.java.ageev.hw8.dataprocessor;

public class FileProcessException extends RuntimeException {
    public FileProcessException(Exception ex) {
        super(ex);
    }

    public FileProcessException(String msg) {
        super(msg);
    }
}
