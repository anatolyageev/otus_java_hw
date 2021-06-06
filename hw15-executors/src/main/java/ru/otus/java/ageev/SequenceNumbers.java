package ru.otus.java.ageev;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequenceNumbers {
    private static final Logger LOG = LoggerFactory.getLogger(SequenceNumbers.class);
    private static final int MAX_NUMBER = 10;
    private static final int START_NUMBER = 1;
    private final String firstThread;
    private final Object lock = new Object();
    private AtomicInteger number;
    private int delta;
    private boolean forward;

    public SequenceNumbers(String firstThread) {
        this.firstThread = firstThread;
        number = new AtomicInteger(0);
        forward = true;
        delta = 1;
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (!Thread.currentThread().getName().equals(firstThread)) {
                    lock.wait();
                }
                if (forward) {
                    if (number.get() == MAX_NUMBER) {
                        forward = false;
                        delta = -1;
                    }
                } else {
                    if (number.get() == START_NUMBER) {
                        forward = true;
                        delta = 1;
                    }
                }
                LOG.info("{} --> {}", Thread.currentThread().getName(), number.addAndGet(delta));
                lock.notify();
            }
            Thread.sleep(2000);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (Thread.currentThread().getName().equals(firstThread)) {
                    lock.wait();
                }
                LOG.info("{} --> {}", Thread.currentThread().getName(), number.get());
                lock.notify();
            }
            Thread.sleep(2000);
        }
    }

}
