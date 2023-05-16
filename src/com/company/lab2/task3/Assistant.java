package com.company.lab2.task3;

import java.util.Random;

public class Assistant {
    private final String name;
    private static final Random random = new Random();
    private Thread thread;


    public Assistant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addGrades(Group group, String className, int from, int to) {
        thread = new Thread(() -> {
            for (Student student : group.getStudents().subList(from, to)) {
                group.addGrade(className, student, random.nextInt(101));
            }
        });
        thread.start();
    }

    public void join() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
