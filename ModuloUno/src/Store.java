public abstract class Store {
    private double drinkPrice, cash;

    public Store(double drinkPrice) {
        this.drinkPrice = drinkPrice;
        this.cash = 0.0;
    }

    public void addCash(double cash){
        this.cash += cash;
    }

    public abstract void welcome();

    public void payDrinks(int numOfDrinks){
        addCash(numOfDrinks * this.drinkPrice);
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double drinkPrice) {
        this.drinkPrice = drinkPrice;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
