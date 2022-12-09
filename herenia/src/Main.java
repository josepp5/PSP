import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle(10);
        circle.hello();

        Triangle triangle = new Triangle(10, 10);
        triangle.hello();

        Square square = new Square(10);
        square.hello();

        ShapeCompare<Shape, Shape> shapeCompare = new ShapeCompare<>(new Circle(10), new Circle(10));
        shapeCompare.compare();

        List<Shape> arrayShape = new ArrayList<>();
        arrayShape.add(new Circle(10));
        arrayShape.add(new Square(10));
        arrayShape.add(new Triangle(10, 20));

        printShapes(arrayShape);

        Shape shape = new Shape() {
            @Override
            int getArea() {
                return 0;
            }
        };

        arrayShape.add(shape);
    }

    public static void printShapes(List<Shape> arrayShape) {
        ListIterator<Shape> iterator = arrayShape.listIterator();
        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            shape.hello();
            if (shape instanceof Triangle) {
                ((Triangle) shape).printHola();
            }
        }
    }
}