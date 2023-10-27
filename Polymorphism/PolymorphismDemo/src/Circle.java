public class Circle extends Shape {


    public Circle(String name, int side) {
        super(name, side);
    }

    public void erase() {
        System.out.println("Erase Circle");
    }
    public int erase(int sum) {
        return sum;
    }
}
