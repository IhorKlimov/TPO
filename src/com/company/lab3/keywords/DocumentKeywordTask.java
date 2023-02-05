package com.company.lab3.keywords;

import com.company.lab3.Document;
import com.company.lab3.WordCounter;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentKeywordTask extends RecursiveTask<Boolean> {
    private final Document document;
    private List<String> keywords;
    private final WordCounter wordCounter;

    public DocumentKeywordTask(Document document, List<String> keywords, WordCounter wordCounter) {
        this.document = document;
        this.keywords = keywords;
        this.wordCounter = wordCounter;
    }

    @Override
    protected Boolean compute() {
        return wordCounter.doesDocumentContainKeywords(document, keywords);
    }

    public Document getDocument() {
        return document;
    }
}
