package com.company.lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JButton buttonStop = new JButton("Stop");
        score = new JTextArea("Balls in holes: " + ballsInHoles);
        buttonStart.addActionListener(e -> {
            Ball b = new Ball(canvas);
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
        buttonStop.addActionListener(e -> System.exit(0));
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(score);
        System.out.println("paint");
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("new paint");
        score.setText("Balls in holes: " + ballsInHoles);
    }
}
