public class Triangle extends Shape{

    public Triangle(String name, int side) {
        super(name, side);
    }

    @Override
    public void draw() {
        System.out.println("Draw Triangle");
    }

    @Override
    public void print() {
        System.out.println("Print Triangle");
    }
}
