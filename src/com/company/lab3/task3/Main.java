package com.company.lab3.task3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Group> groups = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<String> classes = new ArrayList<>();
        classes.add("Технології паралельних обчислень");

        ArrayList<Student> studentsOne = new ArrayList<>();
        studentsOne.add(new Student("Yamanu Eszti", "ІТз-01"));
        studentsOne.add(new Student("Hiltrud Apurva", "ІТз-01"));
        studentsOne.add(new Student("Coinneach Sebastiana", "ІТз-01"));
        studentsOne.add(new Student("Annemiek Tadesse", "ІТз-01"));
        studentsOne.add(new Student("Steve Johnson", "ІТз-01"));
        studentsOne.add(new Student("Andrew Keller", "ІТз-01"));
        studentsOne.add(new Student("Jules Lotte", "ІТз-01"));
        studentsOne.add(new Student("Katja Bengta", "ІТз-01"));
        studentsOne.add(new Student("Andra Portia", "ІТз-01"));

        ArrayList<Student> studentsTwo = new ArrayList<>();
        studentsTwo.add(new Student("Korinna Bogomil", "ІПз-01"));
        studentsTwo.add(new Student("Liese Morgana", "ІПз-01"));
        studentsTwo.add(new Student("Arrats Aldert", "ІПз-01"));
        studentsTwo.add(new Student("Samantha Smith", "ІПз-01"));
        studentsTwo.add(new Student("Carol Vikinsson", "ІПз-01"));
        studentsTwo.add(new Student("Lugus Vera", "ІПз-01"));
        studentsTwo.add(new Student("Femme Diederik", "ІПз-01"));
        studentsTwo.add(new Student("Tariel Sofiya", "ІПз-01"));
        studentsTwo.add(new Student("Honora Larunda", "ІПз-01"));
        studentsTwo.add(new Student("Bogdana Win", "ІПз-01"));

        ArrayList<Student> studentsThree = new ArrayList<>();
        studentsThree.add(new Student("Leonas Jenny", "ІАз-01"));
        studentsThree.add(new Student("Mathea Kuzman", "ІАз-01"));
        studentsThree.add(new Student("Kris Etenesh", "ІАз-01"));
        studentsThree.add(new Student("Christopher Atkinsson", "ІАз-01"));
        studentsThree.add(new Student("Kelly Smith", "ІАз-01"));
        studentsThree.add(new Student("Ivar Alajos", "ІАз-01"));
        studentsThree.add(new Student("Prisca Merita", "ІАз-01"));
        studentsThree.add(new Student("Aþalafuns Herleifr", "ІАз-01"));

        groups.add(new Group("ІТз-01", studentsOne, classes));
        groups.add(new Group("ІПз-01", studentsTwo, classes));
        groups.add(new Group("ІАз-01", studentsThree, classes));

        Teacher teacher = new Teacher("Jeannine Leilani");
        Assistant assistantOne = new Assistant("Marinda Daniil");
        Assistant assistantTwo = new Assistant("Terrie Patton");
        Assistant assistantThree = new Assistant("Gonzalo Wilson");


        while (true) {
            for (Group group : groups) {
                int numOfStudents = group.getStudents().size();
                int oneTask = numOfStudents / 4;
                int remainder = numOfStudents % 4;

                teacher.addGrades(group, "Технології паралельних обчислень", 0, oneTask);
                assistantOne.addGrades(group, "Технології паралельних обчислень", oneTask, oneTask * 2);
                assistantTwo.addGrades(group, "Технології паралельних обчислень", oneTask * 2, oneTask * 3);
                assistantThree.addGrades(group, "Технології паралельних обчислень", oneTask * 3, oneTask * 3 + remainder);
            }

            try {
                Thread.sleep(7 * 24 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
