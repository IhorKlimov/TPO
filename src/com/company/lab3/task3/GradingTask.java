package com.company.lab3.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class GradingTask extends RecursiveTask<Map<Student, Integer>> {
    private Group group;
    private String className;

    public GradingTask(Group group, String className) {
        this.group = group;
        this.className = className;
    }

    @Override
    protected Map<Student, Integer> compute() {
        int numOfStudents = group.getStudents().size();
        int oneTask = numOfStudents / 4;
        int remainder = numOfStudents % 4;

        ArrayList<RecursiveTask<Map<Student, Integer>>> tasks = new ArrayList<>();
        tasks.add(new Teacher("Jeannine Leilani", group, className, 0, oneTask));
        tasks.add(new Assistant("Marinda Daniil", group, className, oneTask, oneTask * 2));
        tasks.add(new Assistant("Terrie Patton", group, className, oneTask * 2, oneTask * 3));
        tasks.add(new Assistant("Gonzalo Wilson", group, className, oneTask * 3, oneTask * 4 + remainder));

        for (RecursiveTask<Map<Student, Integer>> task : tasks) {
            task.fork();
        }

        HashMap<Student, Integer> result = new HashMap<>();

        for (RecursiveTask<Map<Student, Integer>> task : tasks) {
            Map<Student, Integer> grades = task.join();
            result.putAll(grades);
        }

        return result;
    }
}
