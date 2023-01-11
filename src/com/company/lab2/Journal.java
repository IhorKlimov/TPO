package com.company.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Journal {
    private static List<Group> groups = new ArrayList<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        ArrayList<String> studentsOne = new ArrayList<>();
        studentsOne.add("Yamanu Eszti");
        studentsOne.add("Hiltrud Apurva");
        studentsOne.add("Coinneach Sebastiana");
        studentsOne.add("Annemiek Tadesse");

        ArrayList<String> studentsTwo = new ArrayList<>();
        studentsTwo.add("Korinna Bogomil");
        studentsTwo.add("Liese Morgana");
        studentsTwo.add("Arrats Aldert");

        ArrayList<String> studentsThree = new ArrayList<>();
        studentsThree.add("Leonas Jenny");
        studentsThree.add("Mathea Kuzman");
        studentsThree.add("Kris Etenesh");

        groups.add(new Group("ІТз-01", studentsOne));
        groups.add(new Group("ІПз-01", studentsTwo));
        groups.add(new Group("ІАз-01", studentsThree));

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                for (Group group : groups) {
                    for (String student : group.getStudents()) {
                        group.addGrade(student, random.nextInt(100) + 1);
                    }
                }

                try {
                    Thread.sleep(7 * 24 * 60 * 60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
