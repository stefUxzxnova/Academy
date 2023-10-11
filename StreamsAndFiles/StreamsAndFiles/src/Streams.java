import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Streams {
    public static void main(String[] args) {
        String input = "C:\\Users\\User\\OneDrive\\Desktop\\sirma\\StreamsAndFiles\\input.txt";
        String output = "C:\\Users\\User\\OneDrive\\Desktop\\sirma\\StreamsAndFiles\\output.txt";
        Path inputPath = Paths.get("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\StreamsAndFiles\\input.txt");
        File dir = new File("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\StreamsAndFiles");
        File serialization = new File("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\StreamsAndFiles\\outputSerialize.txt");

        Car car = new Car("Mercedes", 300, 2023);
        File serializationCustomObject = new File("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\StreamsAndFiles\\SerializeCustomObject.txt");

        //readFile(inputPath);
        //readFileSkipSymbols(path);
        //writeFile(input, output);
        //filesInDirectory(dir);
        //traverseFilesInDirectoryRecursively(dir, 1);
        //nestedDirectories(dir);
        //writeObject(serialization);
        //readObjects(serialization);
        //writeCustomObject(car, serializationCustomObject);
        readCustomObject(serializationCustomObject);
    }

    public static void readFile(Path path){
        //try-with-resources
        int count = 0;
        try (java.io.InputStream is = new FileInputStream(path.toFile())){
            int oneByte = is.read();
            while (oneByte != -1){
                count++;
                System.out.print((char)(oneByte));
                oneByte = is.read();
            }
        }catch(IOException e){
            System.out.println("error occurred");
        }
        System.out.println(count);
    }

    public static void readFileSkipSymbols(String inputPath){
        String regex = "[,\\.?!]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        try (java.io.InputStream is = new FileInputStream(inputPath)){
            int byteChar = is.read();
            while(byteChar != -1){
                matcher = pattern.matcher(String.valueOf((char)byteChar));
                if (!matcher.find()){
                    System.out.print((char)byteChar);
                }
                byteChar = is.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String inputStream, String outputStream){
        String regex = "[,\\.?!]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        try (java.io.InputStream is = new FileInputStream(inputStream)){
            OutputStream os = new FileOutputStream(outputStream);
            int b = is.read();
            while (b != -1){
                matcher = pattern.matcher(String.valueOf((char)b));
                if (!matcher.find()) {
                    os.write(b);
                }
                b = is.read();
            }
            System.out.println("Success");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void filesInDirectory(File dir){
        File[] files = new File[0];
        if (dir.isDirectory()) {
                files = dir.listFiles();
        }
        for (File file : files) {
            System.out.println(file);
        }
    }

    public static void traverseFilesInDirectoryRecursively(File file, int count){
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println(count + " " + file);
                File[] files = file.listFiles();
                for (File f : files) {
                    if (!f.isDirectory()) {
                        System.out.println("    " + f.getName());
                    }else{
                        count++;
                        traverseFilesInDirectoryRecursively(f, count);
                    }
                }
            }
        }
    }

    public static void nestedDirectories(File root){
        int count = 0;
        ArrayDeque<File> dirs = new ArrayDeque<>();
        if (root.exists()) {
            if (root.isDirectory()) {
                dirs.offer(root);
                while (!dirs.isEmpty()){
                    count++;
                    File current = dirs.pop();
                    File[] files = current.listFiles();
                    for (File f : files) {
                        if (f.isDirectory()) {
                            dirs.offer(f);
                        }
                    }
                    System.out.println(count + " " + current.getName());
                }
            }
        }
    }

    public static void writeObject(File file){
        List<String> names = new ArrayList<>();
        Collections.addAll(names,"Ivan", "Stefka", "Dragan");
        if (!file.isDirectory()) {
            try(FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(names);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Unsuccessful!");
        }
    }

    public static void readObjects(File file){
        List<String> names = null;
        if (!file.isDirectory()) {
            try (FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)){
                names = (List<String>) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (String name : names) {
                System.out.println(name);
            }
        }else{
            System.out.println("Directory, not file");
        }

    }

    public static <T> void writeCustomObject(T customObj, File file){
        //customObj.getClass();
        if (!file.isDirectory()) {
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                oos.writeObject(customObj);
                System.out.println("Success!");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void readCustomObject(File file){
        if (!file.isDirectory()) {
            T object;
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis);)
            {
                object = (T) ois.readObject();
                System.out.println(object.toString());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
