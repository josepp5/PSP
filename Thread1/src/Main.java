import java.sql.Time;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
/*
        /// Proceso con Thread
        // El Thread es el canal y con el overrride de Run() le damos los datos a procesar
        MyThread t = new MyThread("Primero");
        t.start();

        MyThread t2 = new MyThread("Segundo");
        t2.start();

        /// Proceso con Runnable
        // El Runnable solo es codigo para ejecutar y necesita el canal (Thread) y tambien overrride del metodo Run()
        MyRunnable r = new MyRunnable("Runnable");
        Thread threadRunnable = new Thread(r);
        threadRunnable.start();

        /// Fibonacci
        FibonacciThread fib = new FibonacciThread();
        fib.start();

        System.out.println("Hello world!");

        /// Fibonacci Recursiva con utils
        utils.fibonacci(30);

        /// MultiplierThread
        for (int i = 0; i < 11; i++) {
            MultiplierThreads th = new MultiplierThreads(i);
            th.start();
        }


        // Mas caracteristicas del Thread (nombre , estado , id , sleep)
        MyThread tt = new MyThread("Primero");
        tt.setName("Prueba");
        tt.start();

        printThread(tt);
        printThread(Thread.currentThread());

        /// Estas dos lineas hacen lo mismo (duermen el thread por el tiempo que se defina por param)
        Thread.sleep(2000);
        tt.sleep(2000);

        FibonacciThread thread = new FibonacciThread();
        thread.start();

        do {
            System.out.println("Waiting...");
            Thread.sleep(1000);
        }while (thread.getState() != Thread.State.TERMINATED);
        System.out.println("Finish...");

        ThreadRace thr = new ThreadRace();
        thr.start();
        ThreadRace thr1 = new ThreadRace();
        thr1.start();
        ThreadRace thr2 = new ThreadRace();
        thr2.start();


        MyThread thr = new MyThread();
        thr.start();
        MyThread thr1 = new MyThread();
        thr1.start();
        MyThread thr2 = new MyThread();
        thr2.start();

        do {
            System.out.println("Thread A:" + thr.getValue() + "Thread B:" + thr1.getValue() + "Thread B:" + thr2.getValue());
            Thread.sleep(100);
            if (thr1.getValue() >=700){
                thr1.setCancelled(true);
            }
        }while (thr.getState() != Thread.State.TERMINATED ||
                thr1.getState() != Thread.State.TERMINATED ||
                thr2.getState() != Thread.State.TERMINATED);
        System.out.println("Finish...");

 */

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10;i++)
        {
            MyThread th = new MyThread("Name:"+i);
            //th.start();
            executor.execute(th);
        }
        System.out.println("finish...");
        Thread.sleep(1000*10);
    }

    public static void printThread(Thread t){
        System.out.println("El nombre del Thread es : " + t.getName());
        System.out.println("El estado del Thread es : " + t.getState());
        System.out.println("El ID del Thread es : " + t.getId());
    }
}