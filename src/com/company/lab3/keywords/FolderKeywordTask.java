package com.company.lab3.keywords;

import com.company.lab3.Document;
import com.company.lab3.Folder;
import com.company.lab3.WordCounter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderKeywordTask extends RecursiveTask<List<Document>> {
    private final Folder folder;
    private final List<String> keywords;
    private final WordCounter wordCounter;


    public FolderKeywordTask(Folder folder, List<String> keywords, WordCounter wordCounter) {
        this.folder = folder;
        this.keywords = keywords;
        this.wordCounter = wordCounter;
    }

    @Override
    protected List<Document> compute() {
        ArrayList<Document> documents = new ArrayList<>();

        List<DocumentKeywordTask> tasks = new LinkedList<>();
        for (Document document : folder.getDocuments()) {
            DocumentKeywordTask task = new DocumentKeywordTask(document, keywords, wordCounter);
            tasks.add(task);
            task.fork();
        }
        for (DocumentKeywordTask task : tasks) {
            boolean result = task.join();
            if (result) {
                documents.add(task.getDocument());
            }
        }

        return documents;
    }
}
