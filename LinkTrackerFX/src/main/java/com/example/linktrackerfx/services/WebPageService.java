package com.example.linktrackerfx.services;

import com.example.linktrackerfx.model.WebPage;
import com.example.linktrackerfx.utils.LinkReader;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebPageService extends Service<List<WebPage>> {
    private List<WebPage> list;
    private List<Future<WebPage>> futures = new ArrayList<>();

    private EventHandler<WorkerStateEvent> completionEvent;

    public WebPageService(List<WebPage> list, EventHandler<WorkerStateEvent> completionEvent) {
        this.list = list;
        this.completionEvent = completionEvent;
    }

    public static Callable<WebPage> completeWebPage(WebPage webPage) {
        return () -> {
            webPage.setLinks(LinkReader.getLinks(webPage.getUrl()));
            return webPage;
        };
    }

    @Override
    protected Task<List<WebPage>> createTask() {
        return new Task<List<WebPage>>() {
            @Override
            protected List<WebPage> call() throws Exception {
                // 1 - Llamar al ScheduledService
                var schedServ = configuredScheduledService();
                schedServ.start();

                // 2 - Recorrer webpage call
                ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                for(WebPage wp : list){
                    // 3 - Get the future
                    Future<WebPage> future = executor.submit(completeWebPage(wp));
                    futures.add(future);
                }

                // 4 - Get the future result
                List<WebPage> result = new ArrayList<WebPage>();
                int totalLinks = 0;
                for (Future<WebPage> future : futures) {
                    WebPage wp = future.get();
                    totalLinks += wp.getLinks().size();
                    updateMessage("Total Links : " + totalLinks);

                    // 5 - Fill the webpage list
                    result.add(wp);
                }

                // 6 - Return the webpage list filled
                return result;
            }
        };
    }

    int checkFutures() {
        var count = 0;
        for (Future<WebPage> future : futures) {
            if (future.isDone()) count++;
        }
        return count;
    }

    ScheduledService<Integer> configuredScheduledService() {
        ScheduledService<Integer> schedServ = new ScheduledService<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() {
                        return checkFutures();
                    }
                };
            }
        };
        schedServ.setPeriod(Duration.millis(100)); // Execute every second after
        schedServ.setOnSucceeded(completionEvent);

        return schedServ;
    }
}
