package com.company.lab1;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

class Ball {
    private final Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private final Color color;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private boolean isRemoved;

    public Ball(Component c, Color color, int startX, int startY) {
        this.canvas = c;
        this.color = color;

        if (startX != -1 && startY != -1) {
            this.x = startX;
            this.y = startY;
        } else {
            if (Math.random() < 0.5) {
                x = new Random().nextInt(this.canvas.getWidth());
                y = 0;
            } else {
                x = 0;
                y = new Random().nextInt(this.canvas.getHeight());
            }
        }
    }

    public static void f() {
        int a = 0;
    }

    public void draw(Graphics2D g2) {
        if (!isRemoved) {
            g2.setColor(color);
            g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
        }
    }

    public boolean move() {
        x += dx;
        y += dy;

        if (x <= XSIZE / 2 && y <= YSIZE / 2) { // Left-right corner
            isRemoved = true;
            this.canvas.repaint();
            return true;
        } else if (x <= XSIZE / 2 && y + YSIZE / 2 >= this.canvas.getHeight()) { // Left-bottom corner
            isRemoved = true;
            this.canvas.repaint();
            return true;
        } else if (x + XSIZE / 2 >= this.canvas.getWidth() && y <= YSIZE / 2) { // Right-top corner
            isRemoved = true;
            this.canvas.repaint();
            return true;
        } else if (x + XSIZE / 2 >= this.canvas.getWidth() && y + YSIZE / 2 >= this.canvas.getHeight()) { // Right-bottom corner
            isRemoved = true;
            this.canvas.repaint();
            return true;
        }

        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
        return false;
    }
}