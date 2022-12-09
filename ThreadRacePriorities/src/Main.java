public class Main {
    public static void main(String[] args) throws InterruptedException {

        MyThread a = new MyThread();
        MyThread b = new MyThread();
        MyThread c = new MyThread();

        a.setPriority(Thread.MAX_PRIORITY);
        b.setPriority(Thread.NORM_PRIORITY);
        c.setPriority(Thread.MIN_PRIORITY);

        a.start();
        b.start();
        c.start();

        do {
            System.out.println("Thread A:" + a.getValue() + "Thread B:" + b.getValue() + "Thread C:" + c.getValue());
            Thread.sleep(100);
            if (b.getValue() >=700){
                b.setCancelled(true);
            }
        }while (a.getState() != Thread.State.TERMINATED ||
                b.getState() != Thread.State.TERMINATED ||
                c.getState() != Thread.State.TERMINATED);
        System.out.println("Finish...");
    }
}