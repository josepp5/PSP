public class LiquorStore extends Store implements ISayGoodbye, ISayHello{
    @Override
    public void welcome() {
        System.out.println("Welcome to our LiquorStore! tenemos ");
        super.call();
    }

    @Override
    public void sayGoodbye() {

    }

    @Override
    public void sayHello() {

    }
}
