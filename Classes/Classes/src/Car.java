import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

    private String brand;

    private String model;

    private int horsepower;

    public Car(){
        this.brand = "unknown";
        this.model = "unknown";
        this.horsepower = -1;
    }
    public Car(String brand){
        this.brand = brand;
        this.model = "unknown";
        this.horsepower = -1;
    }

    @Override
    public String toString(){
        return "The car is: " + brand + " " + model + " - " + horsepower + " HP.";
    }
}
