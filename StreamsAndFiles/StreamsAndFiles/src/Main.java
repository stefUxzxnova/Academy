import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) {

        String path = "C:\\Users\\User\\Downloads\\input.txt";
        try(InputStream is = new FileInputStream(path)) {
            int oneByte = is.read();
            //until the input stream returns -1
            while (oneByte >= 0){
                System.out.printf("%s", Integer.toBinaryString(oneByte));
                oneByte = is.read();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void readingBytePerByte(){

    }
}