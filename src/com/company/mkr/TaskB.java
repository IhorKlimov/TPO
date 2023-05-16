package com.company.mkr;

public class TaskB extends Thread {
    private final TaskA taskA;

    public TaskB(TaskA taskA) {
        this.taskA = taskA;
    }

    @Override
    public void run() {
        while (true) {
            if (taskA.getCustomState().equals("s")) {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("Thread B countdown: " + i + " milliseconds");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                synchronized (taskA) {
                    try {
                        taskA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

