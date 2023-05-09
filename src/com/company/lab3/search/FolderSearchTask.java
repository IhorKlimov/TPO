package com.company.lab3.search;

import com.company.lab3.Document;
import com.company.lab3.Folder;
import com.company.lab3.WordCounter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static com.company.lab3.WordCounter.wordsIn;

public class FolderSearchTask extends RecursiveTask<List<String>> {
    private final Folder folder;
    private final WordCounter wordCounter;


    public FolderSearchTask(Folder folder, WordCounter wordCounter) {
        this.folder = folder;
        this.wordCounter = wordCounter;
    }

    @Override
    protected List<String> compute() {
        ArrayList<String> commonWords = new ArrayList<>();

        Document firstDocument = null;
        ArrayList<Document> otherDocuments = new ArrayList<>();

        if (folder.getDocuments().size() < 2) {
            return commonWords;
        }

        for (int i = 0; i < folder.getDocuments().size(); i++) {
            Document doc = folder.getDocuments().get(i);
            if (i == 0) {
                firstDocument = doc;
            } else {
                otherDocuments.add(doc);
            }
        }

        for (String line : firstDocument.getLines()) {
            for (String word : wordsIn(line)) {
                List<DocumentSearchTask> tasks = new LinkedList<>();

                for (Document document : otherDocuments) {
                    DocumentSearchTask task = new DocumentSearchTask(document, word, wordCounter);
                    tasks.add(task);
                    task.fork();
                }
                for (DocumentSearchTask task : tasks) {
                    if (task.join()) {
                        if (!commonWords.contains(task.getSearchedWord())) {
                            commonWords.add(task.getSearchedWord());
                        }
                    }
                }
            }
        }

        return commonWords;
    }
}
