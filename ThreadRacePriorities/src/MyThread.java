public class MyThread extends Thread {
    private String name;
    private int num;

    private boolean cancelled;

    public int getValue(){
        return this.num;
    }

    public void setCancelled(boolean cancelled){
        this.cancelled = cancelled;
    }

    public MyThread() {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000 && !this.cancelled; i++) {
            try {
                this.sleep((int) (Math.random() * 10));
                num = i;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
