package com.company.lab1;

public class BallThread extends Thread {
    private final Ball ball;
    private final OnHitHoleListener onHitHoleListener;

    public BallThread(Ball ball, OnHitHoleListener onHitHoleListener) {
        this.ball = ball;
        this.onHitHoleListener = onHitHoleListener;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10000; i++) {
                boolean hasHitHole = ball.move();
                if (hasHitHole) {
                    onHitHoleListener.onHitHole(ball);
                    return;
                }
//                System.out.println("Thread name = " + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            // NOOP
        }
    }
}
