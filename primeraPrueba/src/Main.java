public class Main {
    public static void main(String[] args) {


        LiquorStore LiquorStore = new LiquorStore();
        LiquorStore.welcome();

        Store store1 = new Store() {
            @Override
            public void welcome() {
                System.out.println("Welcome to our Store");
            }
        };

    }

}