package com.company.lab3;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<Long> {
    private final Folder folder;
    private final String searchedWord;
    private final WordCounter wordCounter;


    public FolderSearchTask(Folder folder, String searchedWord, WordCounter wordCounter) {
        this.folder = folder;
        this.searchedWord = searchedWord;
        this.wordCounter = wordCounter;
    }

    @Override
    protected Long compute() {
        long count = 0L;
        List<RecursiveTask<Long>> tasks = new LinkedList<>();
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document, searchedWord, wordCounter);
            tasks.add(task);
            task.fork();
        }
        for (RecursiveTask<Long> task : tasks) {
            count = count + task.join();
        }
        return count;
    }
}
