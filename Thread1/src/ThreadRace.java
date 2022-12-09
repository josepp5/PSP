public class ThreadRace extends Thread{
    private int num;
    public int getValue(){
        return this.num;
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                this.sleep((int) (Math.random() * 10));
                num = i;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
