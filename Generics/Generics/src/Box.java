import java.util.ArrayList;
import java.util.List;

public class Box <T extends Comparable<T>> {
    private List<T> list;

    public Box(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void add(T item){
        list.add(item);
    }



    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T item : this.list) {
           str.append(list.get(0).getClass().getName() + ": " + item + "\n");
        }
        return str.toString();
    }

    public int countGreaterElements(T comparableValue){
        int count = 0;
        for (T item : this.list) {
            if (item.compareTo(comparableValue) > 0) count++;
        }
        return count;
    }

}
