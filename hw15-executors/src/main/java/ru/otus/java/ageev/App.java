package ru.otus.java.ageev;

public class App {
    public static void main(String[] args) throws InterruptedException {
        SequenceNumbers sn = new SequenceNumbers("First");

            Thread thread1 = new Thread(() -> {
                try {
                    sn.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "First");

        Thread thread2 = new Thread(() -> {
            try {
                sn.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Second");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
