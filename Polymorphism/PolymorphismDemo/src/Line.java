public class Line extends Shape {

    public Line(String name, int side) {
        super(name, side);
    }

    @Override
    public void draw() {
        System.out.println("Draw Line");
    }

    @Override
    public void print() {
        System.out.println("Print Line");
    }
    public void print(int n) {
        System.out.println("Print Line");
    }
}
