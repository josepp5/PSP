public class Counter {
    private int num = 0;

    public Counter(int num) {
        this.num = num;
    }

    // if its syncronized it will execute this just once till is finished
    public synchronized void increment(){
        this.num++;
    }
    public synchronized void decrement(){
        this.num--;
    }

    public int getValue() {
        return num;
    }

    public void setValue(int num) {
        this.num = num;
    }
}
