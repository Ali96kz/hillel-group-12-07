package com.hillel.group.concurrency.example;

import java.util.concurrent.atomic.AtomicBoolean;

public class Data {
    private volatile String packet;

    // True if receiver should wait
    // False if sender should wait
    private final AtomicBoolean transfer = new AtomicBoolean(true);
    private final Object lock = new Object();

    public void send(String packet) {
        while (!transfer.get()) {
            try {
                System.out.println("Sender start wait");
                wait();
                System.out.println("Sender end wait");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        transfer.set(false);

        this.packet = packet;
        notifyAll();
    }

    public synchronized String receive() {
        while (transfer.get()) {
            try {
                System.out.println("Receiver start wait");
                wait();
                System.out.println("Receiver finish wait");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        transfer.set(true);

        notifyAll();
        return packet;
    }
}
