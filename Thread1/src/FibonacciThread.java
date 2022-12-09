public class FibonacciThread extends Thread{
    @Override
    public void run() {
        System.out.println(utils.fibonacci(100));
    }
}

