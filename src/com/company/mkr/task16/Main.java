package com.company.mkr.task16;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(10);

        Thread threadA = new Thread(new TaskA(buffer));
        Thread threadB = new Thread(new TaskB(buffer));
        Thread threadC = new Thread(new TaskC(buffer));

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
