package com.company.lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    private final JTextArea score;
    private int ballsInHoles;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonTask3 = new JButton("Task 3");
        JButton buttonStop = new JButton("Stop");
        score = new JTextArea("Balls in holes: " + ballsInHoles);
        buttonStart.addActionListener(e -> {
            Ball b = new Ball(canvas, Color.black, -1, -1);
            canvas.add(b);
            BallThread thread = new BallThread(b, ball -> {
                canvas.remove(ball);
                ballsInHoles++;
                revalidate();
                repaint();
            });
            thread.start();
            System.out.println("Thread name = " + thread.getName());
        });
        buttonTask3.addActionListener(e -> {
            int startX = new Random().nextInt(this.canvas.getWidth());
            int startY = 0;

            ArrayList<BallThread> balls = new ArrayList<>();

            int count = 1000;
            for (int i = 0; i < count; i++) {
                boolean isRed = i == count - 1;
                Ball b = new Ball(canvas, isRed ? Color.red : Color.blue, startX, startY);
                canvas.add(b);
                BallThread thread = new BallThread(b, ball -> {
                    canvas.remove(ball);
                    ballsInHoles++;
                    revalidate();
                    repaint();
                });
                thread.setPriority(isRed ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);
                balls.add(thread);
            }

            balls.forEach(ballThread -> {
                ballThread.start();
                System.out.println("Thread name = " + ballThread.getName());
            });
        });
        buttonStop.addActionListener(e -> System.exit(0));
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(buttonTask3);
        buttonPanel.add(score);
        System.out.println("paint");
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        score.setText("Balls in holes: " + ballsInHoles);
    }
}
