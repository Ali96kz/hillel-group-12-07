package com.hillel.group.concurrency.example;

public class Sender implements Runnable {
    private Data data;

    public Sender(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        String packets[] = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "Fifth packet",
                "End"
        };

        for (String packet : packets) {
            System.out.println("Sender started handling : " + packet);
            data.send(packet);
            System.out.println("Sender end handling : " + packet);
        }
    }
}
