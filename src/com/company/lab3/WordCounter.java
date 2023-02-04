package com.company.lab3;

import com.company.lab3.search.FolderSearchTask;
import com.company.lab3.statistics.FolderStatisticsTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class WordCounter {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    public Long occurrencesCount(Document document, String searchedWord) {
        long wordCount = 0;
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if (searchedWord.equals(word)) {
                    wordCount++;
                }
            }
        }
        return wordCount;
    }

    public Statistics getStatistics(Document document) {
        List<Integer> wordLengths = new ArrayList<>();
        Map<Integer, Integer> occurrences = new HashMap<>();

        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                int length = word.length();
                wordLengths.add(length);
                if (occurrences.containsKey(length)) {
                    int count = occurrences.get(length);
                    occurrences.put(length, count + 1);
                } else {
                    occurrences.put(length, 1);
                }
            }
        }

        // Математичне сподівання
        double mathematicalExpectation = 0;
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            double probability = entry.getValue() / ((double) wordLengths.size());
            mathematicalExpectation += entry.getKey() * probability;
        }

        // Вібіркове середнє
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        double xb = sum / ((double) wordLengths.size());

        // Дісперсія
        double disTotal = 0d;
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            disTotal += Math.pow((entry.getKey() - xb), 2) * entry.getValue();
        }
        double dispersion = disTotal / ((double) wordLengths.size());

        // Середнє квадратичне відхилення
        double meanSquareDeviation = Math.sqrt(dispersion);

        return new Statistics(mathematicalExpectation, dispersion, meanSquareDeviation);
    }

    Long countOccurrencesInParallel(Folder folder, String searchedWord) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord, this));
    }

    Statistics getStatistics(Folder folder) {
        return forkJoinPool.invoke(new FolderStatisticsTask(folder, this));
    }

    Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {
        long count = 0;
        for (Folder subFolder : folder.getSubFolders()) {
            count = count + countOccurrencesOnSingleThread(subFolder, searchedWord);
        }

        for (Document document : folder.getDocuments()) {
            count = count + occurrencesCount(document, searchedWord);
        }

        return count;
    }
}
