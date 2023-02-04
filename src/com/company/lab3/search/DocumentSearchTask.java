package com.company.lab3.search;

import com.company.lab3.Document;
import com.company.lab3.WordCounter;

import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<Long> {
    private final Document document;
    private final String searchedWord;
    private final WordCounter wordCounter;

    public DocumentSearchTask(Document document, String searchedWord, WordCounter wordCounter) {
        this.document = document;
        this.searchedWord = searchedWord;
        this.wordCounter = wordCounter;
    }


    @Override
    protected Long compute() {
        return wordCounter.occurrencesCount(document, searchedWord);
    }

}
