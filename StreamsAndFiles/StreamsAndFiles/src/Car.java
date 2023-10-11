import java.io.Serializable;

public class Car implements Serializable {
    private String brand;
    private int horsePower;
    private int year;

    public Car(String brand, int horsePower, int year){
        this.brand = brand;
        this.horsePower = horsePower;
        this.year = year;
    }
    public String getBrand() {
        return brand;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getYear() {
        return year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "brand: " + brand + ", horsePower: " + horsePower + ", year: " + year;
    }
}
