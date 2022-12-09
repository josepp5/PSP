public class Triangle extends Shape {

    private int altura = 0;
    private int base = 0;

    public Triangle(int altura, int base) {
        this.altura = altura;
        this.base = base;
    }

    @Override
    int getArea() {
        return base * altura / 2;
    }

    @Override
    public void hello() {
        super.hello();
    }

    public void printHola() {
    }
}
