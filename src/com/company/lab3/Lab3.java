package com.company.lab3;

// Побудуйте алгоритм статистичного аналізу тексту та визначте характеристики випадкової величини «довжина слова
// в символах» з використанням ForkJoinFramework. 20 балів.

// Дослідіть побудований алгоритм аналізу текстових документів на ефективність експериментально. 10 балів.

// Реалізуйте один з алгоритмів комп’ютерного практикуму 2 або 3 з використанням ForkJoinFramework та визначте
// прискорення, яке отримане за рахунок використання ForkJoinFramework. 20 балів.

// Розробіть та реалізуйте алгоритм пошуку спільних слів в текстових документах з використанням ForkJoinFramework.
// 20 балів.

// Розробіть та реалізуйте алгоритм пошуку текстових документів, які відповідають заданим ключовим словам
// (належать до області «Інформаційні технології»), з використанням ForkJoinFramework. 30 балів.
// Див. презентацію 1.7 ForkJoin Framework

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Lab3 {
    private static long counts;

    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("files"));
        WordCounter wordCounter = new WordCounter();
        String searchedWord = "synchronized";
        final int repeatCount = 4;
        long cstartTime, stopTime, ounts = 0, averTime = 0;
        long startTime;


//        for (int i = 0; i < repeatCount; i++) {
//            startTime = System.currentTimeMillis();
//            counts = wordCounter.countOccurrencesInParallel(folder, searchedWord);
//            stopTime = System.currentTimeMillis();
//            averTime += stopTime - startTime;
//        }
//        System.out.println(counts + " found words. Fork / join search took " + averTime / repeatCount + "ms");
//        averTime = 0;
//
//        for (int i = 0; i < repeatCount; i++) {
//            startTime = System.currentTimeMillis();
//            counts = wordCounter.countOccurrencesOnSingleThread(folder, searchedWord);
//            stopTime = System.currentTimeMillis();
//            averTime += stopTime - startTime;
//            System.out.println(counts + " found words. Single thread search took " + averTime / repeatCount + "ms");
//        }

//        for (int i = 0; i < repeatCount; i++) {
//            Statistics statistics = wordCounter.getStatistics(folder);
//            System.out.println(statistics);
//        }

        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("алгоритм");
        keywords.add("аналоговий");
        keywords.add("додаток");
        keywords.add("масив");
        keywords.add("резервне копіювання");
        keywords.add("пропускна здатність");
        keywords.add("широкосмуговий доступ");
        keywords.add("браузер");
        keywords.add("даних");
        keywords.add("Доменне ім'я");
        keywords.add("зашифрувати");
        keywords.add("папка");
        keywords.add("html");
        keywords.add("Інтернет");
        keywords.add("посилання");

        for (int i = 0; i < repeatCount; i++) {
            List<Document> documents = wordCounter.findDocumentsByKeywords(folder, keywords);
            for (Document document : documents) {
                System.out.println(document);
            }
        }
    }
}
