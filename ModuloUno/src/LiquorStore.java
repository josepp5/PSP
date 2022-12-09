public class LiquorStore extends Store{
    private  int tax;
    public LiquorStore(double drinkPrice, int tax) {
        super(drinkPrice);
        this.tax = tax;
    }

    @Override
    public void welcome() {
        System.out.println("LiquorStore");
    }

    @Override
    public void payDrinks(int numOfDrinks) {
        super.payDrinks(numOfDrinks);
        addCash(numOfDrinks * getDrinkPrice() * tax / 100.0);
    }
}
