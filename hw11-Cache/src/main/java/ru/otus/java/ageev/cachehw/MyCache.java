package ru.otus.java.ageev.cachehw;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    private final Map<K, V> weakHashMap = new WeakHashMap<>();
    private final List<SoftReference<HwListener<K, V>>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        weakHashMap.put(key, value);
        notifyListeners(key, value, "add");
    }

    @Override
    public void remove(K key) {
        V value = weakHashMap.remove(key);
        notifyListeners(key, value, "delete");
    }

    @Override
    public V get(K key) {
        V value = weakHashMap.get(key);
        notifyListeners(key, value, "get");
        return value;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(new SoftReference<>(listener));
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(K key, V value, String action) {
        listeners.forEach(listener -> Objects.requireNonNull(listener.get()).notify(key, value, action));
    }
}
