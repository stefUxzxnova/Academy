import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Shape shapeLine = new Line("line", 3);
        Line line = new Line("line", 2);
        Circle circle = new Circle("circle", 6);

        List<Shape> list = new ArrayList<>();

        list.add(shapeLine);
        list.add(line);
        list.add(circle);



        for (Shape item : list) {
            if (item instanceof Circle) {
                ((Circle) item).erase();
            }

            //metod(item);
        }
    }
    public static void metod (Shape c){
        c.draw();
    }

}