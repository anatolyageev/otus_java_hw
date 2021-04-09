package ru.otus.java.ageev.cachehw;

public interface HwListener<K, V> {
    void notify(K key, V value, String action);
}
