package com.company.lab3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class Assistant extends RecursiveTask<Map<Student, Integer>> {
    private static final Random random = new Random();
    private String name;
    private final String className;
    private final List<Student> students;

    public Assistant(String name, Group group, String className, int from, int to) {
        this.name = name;
        this.className = className;
        this.students = group.getStudents().subList(from, to);
    }

    @Override
    protected Map<Student, Integer> compute() {
        HashMap<Student, Integer> grades = new HashMap<>();
        for (Student student : students) {
            grades.put(student, random.nextInt(101));
        }
        return grades;
    }
}
