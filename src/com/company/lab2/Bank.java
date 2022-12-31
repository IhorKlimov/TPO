package com.company.lab2;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
    public static final int NTEST = 10_000;
    private final ReentrantLock lock = new ReentrantLock();
    private final int[] accounts;
    private final int initialBalance;
    private long ntransacts;


    public Bank(int n, int initialBalance) {
        accounts = new int[n];
        this.initialBalance = initialBalance;
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, int amount) throws InterruptedException {
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0) {
            test();
        }
    }

    public synchronized void synchronizedTransfer(int from, int to, int amount) throws InterruptedException {
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0) {
            test();
        }
    }

    public void reentrantLockTransfer(int from, int to, int amount) throws InterruptedException {
        lock.lock();
        try {
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            if (ntransacts % NTEST == 0) {
                test();
            }
        } finally {
            lock.unlock();
        }
    }

    public void test() {
        int sum = 0;
        for (int account : accounts) {
            sum += account;
        }
        if (sum != initialBalance * size()) {
            System.out.println("Wrong result: Transactions:" + ntransacts + " Sum: " + sum);
        } else {
            System.out.println("Correct result: Transactions:" + ntransacts + " Sum: " + sum);
        }
    }

    public int size() {
        return accounts.length;
    }
}
