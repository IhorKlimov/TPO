package com.company.mkr;

import java.util.concurrent.Callable;

public class Task implements Callable<Double> {

    private double[] array;
    private int from;
    private int to;

    public Task(double[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    public Double call() {
        double sum = 0;
        for (int i = from; i < to; i++) {
            sum += array[i];
        }
        return sum;
    }
}
