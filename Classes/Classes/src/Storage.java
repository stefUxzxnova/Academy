import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public class Storage {

    private int capacity;

    private static List<Product> products = new ArrayList<>();

    private double totalCost;

    public Storage(int capacity){
        this.capacity = capacity;
    }
    public void addProduct(Product product){
        products.add(product);
        capacity -= product.getQuantity();
        calculateTotalCost();
    }

    //method which calculate Total Cost every time a product is added to the list
    private void calculateTotalCost(){
        totalCost = 0;
        for (var product : products) {
            this.totalCost += product.getQuantity() * product.getUnitPrice();
        }
    }

    //method that returns all the products in storage in JSON format
    public String getProducts(){
        StringBuilder json = new StringBuilder("{");
        for (var product : products) {
            json.append("\"name\": " + product.getName() + ", \"unitPrice:\" " + product.getQuantity() + ", \"price\": " + product.getUnitPrice() + ", \n");
        }
        json.append("}");
        return json.toString();
    }

}
