public class Shape {
    private int side;
    private String name;
    public void draw(){
        System.out.println("Draw Shape");
    };

    public void print(){
        System.out.println("Print Shape");
    };

    public Shape(String name, int side){
        this.name = name;
        this.side = side;
    }
}
