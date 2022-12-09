package com.example.futurefx.services;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class WebPageScheduledService extends ScheduledService<Boolean> {
    private ThreadPoolExecutor executor;

    public WebPageScheduledService(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                // Returns how many threads have finished
                updateMessage("Processed : " + executor.getCompletedTaskCount());
                return executor.isTerminated();
            }
        };
    }
}
