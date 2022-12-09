public class Main {
    public static void main(String[] args) {
        LiquorStore ls = new LiquorStore( 8.95, 20);
        ls.welcome();
        ls.payDrinks(10);
        System.out.printf("Total cash: %.2f\n", ls.getCash());

        Store JoseStore = new Store(10) {
            @Override
            public void welcome() {
                System.out.println("JoseStore");
            }
        };
        JoseStore.payDrinks(100);
    }

}