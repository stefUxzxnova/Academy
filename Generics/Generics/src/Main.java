import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Path readableFile = Path.of("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\Generics\\input.txt");
        Path outputFile = Path.of("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\Generics\\output.txt");
        Path folder = Path.of("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\Generics");
        File fol = new File("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\Generics");
        File serialization = new File("C:\\Users\\User\\OneDrive\\Desktop\\sirma\\Generics\\output.txt");
        //readFile(readableFile);
        //writeFile(readableFile, outputFile);
        //copyBytes(readableFile, outputFile);
        //extractIntegers(readableFile, outputFile);
        //listFiles(folder);
        //nestedFolders(fol);
        //nestedFoldersRecursively(fol, 0);
        //serializeAndDeserializeCustomObject(serialization);
        //jarProba();

//        Object[] ar = ArrayCreator.create(5, "dks");
//
//        Object[] array = ArrayCreator.create(String.class, 6, "jsalf");
//        for (var item : ArrayCreator.getAll(array)) {
//            System.out.println(item);
//        }

        //genericScale();
        //genericBox();

//        //14.
//        List<String> list = new ArrayList<>();
//        fillList(list);
//        list = genericSwapMethodStrings(list);
//        Box<String> box = new Box<>(list);
//        System.out.println(box);

//        //15.
//        List<Integer> list = new ArrayList<>();
//        fillList(list);
//        list = genericSwapMethodStrings(list);
//        Box<Integer> box = new Box<>(list);
//        System.out.println(box);

        //16.
        List<Double> list = new ArrayList<>();
        Box<Double> box = new Box<>(list);
        System.out.println(genericCountMethodStrings(box));
    }

    //1.
    public static void readFile(Path path) {
        try (InputStreamReader fr = new FileReader(path.toFile())) {
            int byteOne = fr.read();
            while (byteOne != -1) {
                System.out.printf("%s, ", Integer.toBinaryString(byteOne));
                byteOne = fr.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //2.
    public static void writeFile(Path fileToRead, Path fileToWrite) {
        try (InputStreamReader fr = new FileReader(fileToRead.toFile());
             OutputStreamWriter ow = new FileWriter(fileToWrite.toFile())) {
            int byteOne = fr.read();
            while (byteOne != -1) {
                ow.write(byteOne);
                byteOne = fr.read();
            }
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    //3.
    public static void copyBytes(Path fileToRead, Path fileToWrite) {
        try (InputStream is = new FileInputStream(fileToRead.toFile());
             OutputStream os = new FileOutputStream(fileToWrite.toFile())) {
            int byteOne = is.read();
            while (byteOne != -1) {
                if (byteOne == 32) {
                    os.write("space".getBytes());
                    //System.out.println("space");
                } else if (byteOne == 13) {
                    os.write("new line".getBytes());
                    //System.out.println("new line");
                } else {
                    os.write(byteOne);
                    //System.out.println(byteOne + " " + (char) byteOne + "_");
                }
                byteOne = is.read();
            }
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    //4.
    public static void extractIntegers(Path fileToRead, Path fileToWrite) {
        String regex = "([ .,?!\\\"\\'\\{\\[(](?<num>[0-9]+)[ .,?!\\\")\\}\\]])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        try (InputStreamReader fr = new FileReader(fileToRead.toFile());
             OutputStreamWriter ow = new FileWriter(fileToWrite.toFile())) {
            //store sentence by sentence
            StringBuilder sentence = new StringBuilder();
            int byteOne = 0;
            while (byteOne != -1) {
                byteOne = fr.read();
                //while the character is not "." -> append to the sentence
                if ((char) byteOne != '.') {
                    sentence.append((char) byteOne);
                    continue;
                }
                sentence.append(".");
                matcher = pattern.matcher(sentence);
                //write each match (number, surrounded by punctuation) in the sentence
                while (matcher.find()) {
                    ow.write(matcher.group("num") + " ");
                }
                sentence.delete(0, sentence.length() - 1);
            }
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    //5.
    public static void listFiles(Path folder) {
        File[] files = null;
        if (folder.toFile().isDirectory()) {
            files = folder.toFile().listFiles();
        }
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName() + " -> " + file.length());
            }
        }
    }

    //6.
    public static void nestedFolders(File folder) {
        ArrayDeque<File> directories = new ArrayDeque<>();
        if (folder.isDirectory()) {
            directories.push(folder);
            while (!directories.isEmpty()) {
                File current = directories.pop();
                System.out.println(current);

                for (File file : current.listFiles()) {
                    if (file.isDirectory()) {
                        directories.push(file);
                    } else {
                        System.out.println(file.getName());
                    }
                }
            }
        }
    }

    //7.
    public static void nestedFoldersRecursively(File folder, int count) {
        if (folder.exists()) {
            if (folder.isDirectory()) {
                System.out.println(count + ".Directory: " + folder);
                File[] files = folder.listFiles();
                for (File file : files) {
                    if (!file.isDirectory()) {
                        System.out.println("    -> " + file.getName());
                    }
                    count++;
                    nestedFoldersRecursively(file, count);
                }
            }
        }
    }

    //8.
    public static void serializeAndDeserializeCustomObject(File file) {
        Toy doll = new Toy("Barbie", 33.5);
        if (file.isFile()) {
            try (FileOutputStream fs = new FileOutputStream(file);
                 ObjectOutput oo = new ObjectOutputStream(fs)) {
                oo.writeObject(doll);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            System.out.println("Error");
            return;
        }
        Toy deserializedDoll = null;
        try (InputStream is = new FileInputStream(file);
             ObjectInput ois = new ObjectInputStream(is)) {
            deserializedDoll = (Toy) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(deserializedDoll.getName() + " -> " + deserializedDoll.getPrice());
    }

    //9.
    //class Jar<T>...
    public static void jarProba() {
        Jar<Integer> jar = new Jar<>();
        jar.add(2);
        jar.add(8);
        jar.add(3);
        jar.remove();
        jar.add(10);
        for (var item : jar.getAll()) {
            System.out.println(item);
        }
    }

    //11.
    public static void genericScale() {
        Scale<Integer> scale = new Scale<>(23, 67);
        Scale<String> scale2 = new Scale<>("ab", "cd");
        System.out.println(scale.getHeavier());
        System.out.println(scale2.getHeavier());

    }

    //12.
    public static void genericBox() {
        List<String> list = new ArrayList<>();
        fillList(list);
        Box<String> box = new Box<>(list);
        System.out.println(box);
    }

    private static <T> List<T> fillList(List<T> list) {
        Scanner scanner = new Scanner(System.in);
        Integer numberOfElements = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= numberOfElements; i++) {
            try {
                T element = (T) scanner.nextLine();
                list.add(element);
            } catch (Exception e) {
                System.out.println("Illegal input");
            }
        }
        return list;
    }

    //14.
    public static <T> List<T> genericSwapMethodStrings(List<T> list) {
        Scanner scanner = new Scanner(System.in);
        int[] indexes = Arrays.stream(scanner.nextLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        T temp = list.get(indexes[0]);
        list.set(indexes[0], list.get(indexes[1]));
        list.set(indexes[1], temp);
        return list;
    }

    //16.
    public static <T extends Comparable<T>> int genericCountMethodStrings(Box<T> box){
        Scanner scanner = new Scanner(System.in);
        List<T> list = fillList2(box.getList());
        T comparableValue = null;
        try {
            comparableValue = (T) scanner.nextLine();
        }catch (Exception e){
            System.out.println("Illegal argument");
        }
        box.setList(list);
        return box.countGreaterElements(comparableValue);
    }

    private static <T> List<T> fillList2(List<T> list){
        Scanner sc = new Scanner(System.in);
        Integer num = Integer.parseInt(sc.nextLine());
        while(num > 0){
            try {
                T element = (T) sc.nextLine();
                list.add(element);
                num--;
            }catch (Exception e){
                System.out.println("Illegal argument");
            }

        }
        return list;
    }
}