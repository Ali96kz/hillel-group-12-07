package com.hillel.group.concurrency.example;

// https://www.baeldung.com/java-wait-notify
public class Demo {
    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data));
        sender.setName("sender");
        Thread receiver = new Thread(new Receiver(data));
        receiver.setName("receiver");

        sender.start();
        receiver.start();
    }
}
