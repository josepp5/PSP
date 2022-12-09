public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        ThreadKiller thread = new ThreadKiller();
        thread.start();

        try {
            Thread.sleep(5000);

        } catch (InterruptedException e){
            throw new RuntimeException();
        }
        thread.setCancelled(true);
        thread.interrupt();

        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();
        System.out.println("Hello world!");



        MyThread myThread = new MyThread();
        myThread.start();

        MyThread myThread2 = new MyThread(myThread);
        myThread2.start();

        myThread2.join();

        System.out.println("Hello mundo");
        */


        Counter c = new Counter(100);
        Thread tinc = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.increment();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
            System.out.println("Finishing inc. Final value= " + c.getValue());
        });
        Thread tdec = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                c.decrement();
                try {Thread.sleep(100);
                } catch (InterruptedException e) { }
            }
            System.out.println("Finishing dec. Final value= " + c.getValue());
        });
        tinc.start();
        tdec.start();
    }
}