package com.company.lab3.statistics;

import com.company.lab3.Document;
import com.company.lab3.Statistics;
import com.company.lab3.WordCounter;

import java.util.concurrent.RecursiveTask;

public class DocumentStatisticsSynchronizedTask extends RecursiveTask<Statistics> {
    private final Document document;
    private final WordCounter wordCounter;

    public DocumentStatisticsSynchronizedTask(Document document, WordCounter wordCounter) {
        this.document = document;
        this.wordCounter = wordCounter;
    }

    @Override
    protected Statistics compute() {
        return wordCounter.getStatisticsSynchronized(document);
    }

}
