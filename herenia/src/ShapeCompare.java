public class ShapeCompare<T extends Shape, M extends Shape> {
    private T object;
    private M object2;

    public ShapeCompare(T object, M object2) {
        this.object = object;
        this.object2 = object2;
    }

    public void printType() {
        System.out.println("Es un " + this.object.getClass().getName());
    }

    public void compare() {
        if (this.object.getArea() > this.object2.getArea()) {
            System.out.println("El ganador es el primero");
        } else {
            System.out.println("El ganador es el segundo");
        }
    }

}
