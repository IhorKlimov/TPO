package com.company.lab2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TransferThread extends Thread {
    private final Bank bank;
    private final int fromAccount;
    private final int maxAmount;
    private static final int REPS = 1_000;

    public TransferThread(Bank b, int from, int max) {
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    public void run() {
        try {
            while (!interrupted()) {
                for (int i = 0; i < REPS; i++) {
                    int toAccount = (int) (bank.size() * Math.random());
                    int amount = (int) (maxAmount * Math.random() / REPS);

                    // Task 1.1
//                     bank.synchronizedTransfer(fromAccount, toAccount, amount);

                    // Task 1.2
//                    synchronized (bank) {
//                        bank.transfer(fromAccount, toAccount, amount);
//                    }

                    // Task 1.3
                    bank.reentrantLockTransfer(fromAccount, toAccount, amount);

                    Thread.sleep(1);
                }
            }
        } catch (InterruptedException e) {
            // NOOP
        }
    }
}
