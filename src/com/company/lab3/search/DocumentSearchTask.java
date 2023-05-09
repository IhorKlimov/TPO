package com.company.lab3.search;

import com.company.lab3.Document;
import com.company.lab3.WordCounter;

import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<Boolean> {
    private final Document document;
    private final String searchedWord;
    private final WordCounter wordCounter;

    public DocumentSearchTask(Document document, String searchedWord, WordCounter wordCounter) {
        this.document = document;
        this.searchedWord = searchedWord;
        this.wordCounter = wordCounter;
    }

    public String getSearchedWord() {
        return searchedWord;
    }

    @Override
    protected Boolean compute() {
        return wordCounter.containsWord(document, searchedWord);
    }

}
