package com.company.lab3.statistics;

import com.company.lab3.Document;
import com.company.lab3.Folder;
import com.company.lab3.Statistics;
import com.company.lab3.WordCounter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderStatisticsTask extends RecursiveTask<Statistics> {
    private final Folder folder;
    private final WordCounter wordCounter;


    public FolderStatisticsTask(Folder folder, WordCounter wordCounter) {
        this.folder = folder;
        this.wordCounter = wordCounter;
    }

    @Override
    protected Statistics compute() {
        List<RecursiveTask<Statistics>> tasks = new LinkedList<>();
        for (Document document : folder.getDocuments()) {
            DocumentStatisticsTask task = new DocumentStatisticsTask(document, wordCounter);
            tasks.add(task);
            task.fork();
        }
        Statistics statistics = null;
        for (RecursiveTask<Statistics> task : tasks) {
            statistics = task.join();
//            count = count + task.join();
        }
        return statistics;
    }
}
