package com.example.futurefx.services;

import com.example.futurefx.model.WebPage;
import com.example.futurefx.utils.LinkReader;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WebPageService extends Service<List<WebPage>> {

    private ThreadPoolExecutor executor;

    public WebPageService(ThreadPoolExecutor executor) {
        this.executor = executor;
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

                // 1 - Get the webpage list
                List<WebPage> list = Arrays.asList(
                        new WebPage("marca","https://marca.com"),
                        new WebPage("futbol.nu","https://aa.futbol.nu"),
                        new WebPage("el pais","https://www.elpais.com")
                );
                List<Future<WebPage>> futures = new ArrayList<>();
                // 2 - Recorrer webpage call
                for(WebPage wp : list){
                    // 3 - Get the future
                    Future<WebPage> future = executor.submit(completeWebPage(wp));
                    futures.add(future);
                }
                executor.shutdown();
                // 4 - Get the future result
                List<WebPage> result = new ArrayList<>();
                int totalLinks = 0;
                for (Future<WebPage> future : futures) {
                    WebPage wp = future.get();
                    totalLinks += wp.getLinks().size();
                    updateMessage("Total Links: " + totalLinks);

                    // 5 - Fill the webpage list
                    result.add(wp);
                }
                // 6 - Return the webpage list filled
                return result;
            }
        };
    }
}
