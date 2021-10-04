package com.hillel.group.concurrency.example2;

// https://stackoverflow.com/questions/44205710/why-is-java-synchronized-not-working-as-expected
/**
 * There is an example of intrinsic lock
 */
public class Main {
    public static void main(String[] args) {
        A target = new A();
        Thread t1 = new Thread(target);
        Thread t2 = new Thread(target);
        t1.setName("T1");
        t2.setName("T2");
        t1.start();
        t2.start();
    }

    public static class B {
        /**
         * try to delete synchronized parameter from method
         * and see what will happens
         */
        public synchronized void addNew(int i) {
            Thread t = Thread.currentThread();
            for (int j = 0; j < 5; j++) {
                System.out.println(t.getName() + "-" + (j + i));
            }
        }
    }

    public static class A extends Thread {
        private B b1 = new B();

        @Override
        public void run() {
            b1.addNew(100);
        }
    }
}
