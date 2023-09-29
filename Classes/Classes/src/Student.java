import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Student {

    private String firstName;

    private String lastName;

    private int age;

    private String hometown;

    public Student(String firstName, String lastName, String hometown){
        this.firstName = firstName;
        this.lastName = lastName;
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " is " + age + " years old";
    }

}
