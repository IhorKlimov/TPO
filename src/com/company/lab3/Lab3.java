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

import com.sun.tools.javac.util.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Lab3 {
    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("common_files"));
        WordCounter wordCounter = new WordCounter();

        List<String> commonWords = wordCounter.getCommonWords(folder);
        System.out.println("Common words: " + String.join(", ", commonWords));


//        ArrayList<String> keywords = new ArrayList<>();
//        keywords.add("алгоритм");
//        keywords.add("аналоговий");
//        keywords.add("додаток");
//        keywords.add("масив");
//        keywords.add("резервне копіювання");
//        keywords.add("пропускна здатність");
//        keywords.add("широкосмуговий доступ");
//        keywords.add("браузер");
//        keywords.add("даних");
//        keywords.add("Доменне ім'я");
//        keywords.add("зашифрувати");
//        keywords.add("папка");
//        keywords.add("html");
//        keywords.add("Інтернет");
//        keywords.add("посилання");
//
//        for (int i = 0; i < repeatCount; i++) {
//            List<Document> documents = wordCounter.findDocumentsByKeywords(folder, keywords);
//            for (Document document : documents) {
//                System.out.println(document);
//            }
//        }
    }
}
