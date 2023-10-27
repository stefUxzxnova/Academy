import java.util.*;

public class Jar<T> {
    private ArrayDeque<T> stack = new ArrayDeque<>();

    public void add(T element){
        stack.push(element);
    }
    public void remove(){
        stack.pop();
    }
    public List<T> getAll(){
        return stack.stream().toList();
    }


}
