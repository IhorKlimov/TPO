package com.company.lab2.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Journal {
    private final String className;
    private final Map<Student, List<Integer>> grades = new HashMap<>();

    public Journal(String name) {
        this.className = name;
    }

    public String getClassName() {
        return className;
    }

    public Map<Student, List<Integer>> getGrades() {
        return grades;
    }

    public synchronized void addGrade(Student student, int grade) {
        if (!grades.containsKey(student)) {
            grades.put(student, new ArrayList<>());
        }
        System.out.println("Додаю оцінку " + student.getName() + " " + grade + " " + student.getGroupName());
        grades.get(student).add(grade);
    }
}
