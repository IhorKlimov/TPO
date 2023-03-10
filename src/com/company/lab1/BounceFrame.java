package com.company.lab1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    private final JTextArea score;
    private int ballsInHoles;
    private Thread thread, thread2 = null;
    private Counter count;

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
        JButton buttonTask4 = new JButton("Task 4");
        JButton buttonTask5 = new JButton("Task 5");
        JButton buttonTask6 = new JButton("Task 6");
        JButton buttonTask7 = new JButton("Task 7");
        JButton buttonTask8 = new JButton("Task 8");
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
        buttonTask4.addActionListener(e -> {
            int startX = new Random().nextInt(this.canvas.getWidth());
            int startY = 0;

            int count = 100;
            new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    boolean isRed = i == 0;
                    Ball b = new Ball(canvas, isRed ? Color.red : Color.blue, startX, startY);
                    canvas.add(b);
                    BallThread thread = new BallThread(b, ball -> {
                        canvas.remove(ball);
                        ballsInHoles++;
                        revalidate();
                        repaint();
                    });
                    thread.setPriority(isRed ? Thread.MAX_PRIORITY : Thread.MIN_PRIORITY);
                    thread.start();
                    if (isRed) {
                        try {
                            thread.join();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }

                    System.out.println("Thread name = " + thread.getName());
                }
            }).start();
        });
        buttonTask5.addActionListener(e -> {
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("-");
                }
            }).start();
            new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    System.out.println("|");
                }
            }).start();
        });
        buttonTask6.addActionListener(e -> {
            thread = new Thread(() -> {
                for (int i = 0; i < 50; i++) {
                    System.out.println("-");
                    thread2.interrupt();
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException ex) {
                        // NOOP
                    }
                }
            });

            thread2 = new Thread(() -> {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException ex) {
                    // NOOP
                }
                for (int i = 0; i < 50; i++) {
                    System.out.println("|");
                    thread.interrupt();
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException ex) {
                        // NOOP
                    }
                }
            });

            thread.start();
            thread2.start();
        });
        buttonTask7.addActionListener(e -> {
            count = new Counter();

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 100000; i++) {
                    count.increment();
                }
                System.out.println("Done");
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 100000; i++) {
                    count.decrement();
                }
                System.out.println("Done");
            });

            thread1.start();
            thread2.start();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Count is " + count.getCount());
        });
        buttonTask8.addActionListener(e -> {
            task8First();
            task8Second();
            task8Third();
        });
        buttonStop.addActionListener(e -> System.exit(0));
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(buttonTask3);
        buttonPanel.add(buttonTask4);
        buttonPanel.add(buttonTask5);
        buttonPanel.add(buttonTask6);
        buttonPanel.add(buttonTask7);
        buttonPanel.add(buttonTask8);
        buttonPanel.add(score);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void task8First() {
        count = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (count) {
                    count.increment();
                }
            }
            System.out.println("Done");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (count) {
                    count.decrement();
                }
            }
            System.out.println("Done");
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Count is " + count.getCount());
    }

    private void task8Second() {
        count = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.update(true);
            }
            System.out.println("Done");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.update(false);
            }
            System.out.println("Done");
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Count is " + count.getCount());
    }

    private void task8Third() {
        count = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.incrementAtomic();
            }
            System.out.println("Done");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.decrementAtomic();
            }
            System.out.println("Done");
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Count is " + count.getAtomicInteger().get());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        score.setText("Balls in holes: " + ballsInHoles);
    }
}
