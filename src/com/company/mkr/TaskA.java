package com.company.mkr;

public class TaskA extends Thread {
    private volatile String customState = "s";

    public synchronized void setState(String newState) {
        customState = newState;
    }

    public synchronized String getCustomState() {
        return customState;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Thread A in state S");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setState("z");
            synchronized (this) {
                System.out.println("notifying");
                notify();
            }
            System.out.println("Thread A in state Z");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setState("s");
            synchronized (this) {
                System.out.println("notifying");
                notify();
            }
        }
    }
}

