package com.company.lab3.task3;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String groupName;
    private final List<Student> students;
    private List<Journal> journals;

    public Group(String groupName, ArrayList<Student> students, List<String> classes) {
        this.groupName = groupName;
        this.students = students;
        journals = new ArrayList<>();
        for (String c : classes) {
            journals.add(new Journal(c));
        }
    }

    public synchronized void addGrade(String className, Student student, int grade) {
        Journal journal = null;

        for (Journal j : journals) {
            if (j.getClassName().equals(className)) {
                journal = j;
                break;
            }
        }

        if (journal != null) {
            journal.addGrade(student, grade);
        }
    }

    public List<Student> getStudents() {
        return students;
    }
}
