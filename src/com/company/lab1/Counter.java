package com.company.lab1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private int count;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public void incrementAtomic() {
        atomicInteger.incrementAndGet();
    }

    public void decrementAtomic() {
        atomicInteger.decrementAndGet();
    }

    public synchronized void update(boolean isIncrement) {
        if (isIncrement) {
            count++;
        } else {
            count--;
        }
    }

    public int getCount() {
        return count;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }
}
