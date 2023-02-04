package com.company.lab3;

import java.util.concurrent.ForkJoinPool;

public class WordCounter {
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    Long occurrencesCount(Document document, String searchedWord) {
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

    Long countOccurrencesInParallel(Folder folder, String searchedWord) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord, this));
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
