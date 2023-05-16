package com.company.lab3.task3;

import java.util.Random;

public class Assistant {
    private final String name;
    private static final Random random = new Random();


    public Assistant(String name) {
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
