public class MultiplierThreads extends Thread{
    private int num;
    private  Thread waitThread;
    public MultiplierThreads(int num) {
        this.num = num;
    }

    public MultiplierThreads(Thread th,int num) {
        this.num = num;
        this.waitThread = th;
    }

    @Override
    public void run() {
        for (int i = 0; i < 11; i++) {
            System.out.println(i + " x " + num + " = " + num*i);
        }
    }
}
