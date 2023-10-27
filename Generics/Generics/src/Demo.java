import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Demo{
    private int num;
    public void print() {
        System.out.println(num);
    }
    public abstract void kajiNesho();
}
class Subclass1 extends Demo{
    @Override
    public void print(){
        System.out.println("subclass1");
    }

    @Override
    public void kajiNesho() {

    }
}
class Subclass2 extends Demo{

    @Override
    public void kajiNesho() {

    }
}
class Mainn{
    public static void main(String[] args) {
        Subclass1 sc = new Subclass1();
        Subclass2 sc2 = new Subclass2();
//        sc.print();
//        sc2.print();

    }
}
