package com.company.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    private final String groupName;
    private final List<String> students;
    private final Map<String, List<Integer>> grades = new HashMap<>();

    public Group(String groupName, List<String> students) {
        this.groupName = groupName;
        this.students = students;
    }

    public synchronized void addGrade(String studentName, int grade) {
        if (!grades.containsKey(studentName)) {
            grades.put(studentName, new ArrayList<>());
        }
        System.out.println("Додаю оцінку "+ studentName + " "+ grade + " "+ groupName);
        grades.get(studentName).add(grade);
    }

    public List<String> getStudents() {
        return students;
    }
}
