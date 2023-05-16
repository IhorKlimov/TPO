package com.company.mkr;

public class Task8 {
    public static void main(String[] args) {
        TaskA taskA = new TaskA();
        TaskB taskB = new TaskB(taskA);

        taskA.start();
        taskB.start();
    }
}
