package com.otus.ageev;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;

class Benchmark implements BenchmarkMBean {
    private static final Logger LOG = Logger.getLogger(Benchmark.class);


    private int loopCounter;
    private volatile int size = 0;
    private final float ERASE_FACTOR = 0.2f;
    private final float GROW_FACTOR = 0.5f;
    List<String> stringList = new LinkedList<>();

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        while (true) {
            for (int idx = 0; idx < loopCounter; idx++) {
                String string = "Digit:" + new Random().nextInt(Integer.MAX_VALUE);
                stringList.add(string);
            }
            loopCounter = loopCounter + (int)(loopCounter * GROW_FACTOR);
            LOG.debug("List size: " + stringList.size());
            eraseLast();
            LOG.debug("List size after erasing: " + stringList.size());
            Thread.sleep(1000);
        }
    }

    /**
     * Method to free list by ERASE_FACTOR size.
     */
    private void eraseLast() {
        int eraseList = (int) (stringList.size() * ERASE_FACTOR);
        for (int i = 0; i < eraseList; i++) {
            stringList.remove(0);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        LOG.debug("new size:" + size);
        this.size = size;
    }
}

