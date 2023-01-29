package com.company.lab3;

import java.util.concurrent.RecursiveTask;

class DocumentSearchTask extends RecursiveTask<Long> {
    private final Document document;
    private final String searchedWord;
    private final WordCounter wordCounter;

    DocumentSearchTask(Document document, String searchedWord, WordCounter wordCounter) {
        this.document = document;
        this.searchedWord = searchedWord;
        this.wordCounter = wordCounter;
    }


    @Override
    protected Long compute() {
        return wordCounter.occurrencesCount(document, searchedWord);
    }

}
