package com.company.lab2.task3;

import java.util.List;
import java.util.Random;

public class Teacher {
    private final String name;
    private static final Random random = new Random();

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addGrades(Group group, String className, int from, int to) {
        new Thread(() -> {
            for (Student student : group.getStudents().subList(from, to)) {
                group.addGrade(className, student, random.nextInt(101));
            }
        }).start();
    }
}
