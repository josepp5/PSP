import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static Callable<List<String>> getSumCallable(String url) {
        return () -> {
            List<String> list = LinkReader.getLinks(url);
            return list;
        };
    }
    // Para la practica esta parte
    public static Callable<WebPage> completeWebPage(WebPage webPage) {
        return () -> {
            webPage.setLinks(LinkReader.getLinks(webPage.getUrl()));
            return webPage;
        };
    }


    public static void main(String[] args) throws InterruptedException {
        // this code is executed in Background, we have to set what element to return
        List<Callable<WebPage>> callables = Arrays.asList(
                completeWebPage(new WebPage("marca","https://marca.com")),
                completeWebPage(new WebPage("futbol.nu","https://aa.futbol.nu")),
                completeWebPage(new WebPage("el pais","https://www.elpais.com")));

        ExecutorService executor = Executors.newFixedThreadPool(1);
        // Calling submit executes the thread and returns a Future
        List<Future<WebPage>> futures = executor.invokeAll(callables);
        executor.shutdown();

        futures.forEach(future -> {
            try {
                WebPage wp = future.get();
                System.out.println("Nombre : " +
                        wp.getName()+ " url : " +
                        wp.getUrl()+ " total : " +
                        wp.getLinks().size());
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        });

    // The line below checks if the future is done.
        /*
        System.out.println("future done? " + futures.isDone());
        List<String> result;
        try {
            result = futures.get(); // It BLOCKS main thread until it returns!
            System.out.println("future done? " + futures.isDone());
            System.out.println("Result: " + result); // Prints 20
        } catch (InterruptedException | ExecutionException ex) {}

         */
    }
}