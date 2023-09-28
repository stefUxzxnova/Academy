import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println(sumFirstAndLastArrayElement(scanner));
        //dayOfWeek(scanner);
        //reverseAnArrayOfNumber(scanner);
        //reverseAnArrayOfStrings(scanner);
        //System.out.println(sumEvenNums(scanner));
        //System.out.println(evenAndOddSubtraction(scanner));
        //equalArrays(scanner);
        //System.out.println(condenseArrayToNumber(scanner));
        //printEveryNthElement(scanner);
        //addAndRemoveElementsFromArray(scanner);
        //rotateArray(scanner);
        //extractNonDecreasingSubsequence(scanner);
        //negativeOrPositiveNumbers(scanner);
        //firstAndLastKNumbers(scanner);
        //lastKElements(scanner);
        //processOddNumbers(scanner);
        //smallestTwoNumbers(scanner);
        //listOfProducts(scanner);
        //arrayManipulations(scanner);
        longestSequenceOfIdenticalElements(scanner);
    }
    //1.
    public static int sumFirstAndLastArrayElement(Scanner scanner){
        String input = scanner.nextLine();
        int[] array = Arrays.stream(input.trim().split("\s*,\s*")).mapToInt(Integer:: parseInt).toArray();
        return array[0] + array[array.length - 1];
    }

    //2.
    public static void dayOfWeek(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        String[] array = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        if (n < 1 || n > 7) {
            System.out.println("Invalid day!");
        }else{
            System.out.println(array[n - 1]);
        }
    }

    //3.
    public static void reverseAnArrayOfNumber(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        //we convert the input from the console into array with limited
        //number of elements
        Integer[] newArray = Arrays.stream(input
                        .trim()
                        .split(","))
                .map(Integer::parseInt)
                .limit(n)
                .toArray(Integer[] :: new);


        reverseArray(newArray);
        printArray(newArray);
    }

    public static void printArray(Object... elements){
        for (Object element : elements) {
            //check if the element is an array
            if (element instanceof Object[]) {
                Arrays.stream((Object[]) element)
                        .forEach(e -> System.out.print(e + " "));
            }else{
                System.out.print(element + " ");
            }
        }
    }

    public static Object[] reverseArray(Object[] array){
        for (int i = 0; i < array.length/2; i++) {
            Object temp = array[i];
            array[i] = array[(array.length - 1) - i];
            array [(array.length - 1) - i] = temp;
        }
        return array;
    }

    //4.
    public static void reverseAnArrayOfStrings(Scanner scanner){
        String input = scanner.nextLine();
        //reads the console input and converts it to String array
        String[] array = Arrays.stream(input.trim().split(","))
                .toArray(String[] :: new);
        reverseArray(array);
        printArray(array);
    }

    //5.
    public static int sumEvenNums(Scanner scanner){
        String input = scanner.nextLine();
        //reads the console input and converts it to String array
        int sum = Arrays.stream(input.trim().split(","))
                .mapToInt(e -> Integer.parseInt(e))
                .filter(e -> e % 2 == 0)
                .sum();
        return sum;
    }

    //6.
    public static int evenAndOddSubtraction(Scanner scanner){
        String input = scanner.nextLine();
        int sum = Arrays.stream(input
                                .trim()
                                .split(","))
                    .mapToInt(Integer :: parseInt)
                    .reduce(0, (subtraction, element ) ->
                    {
                        if (element % 2 == 0) {
                            subtraction += element;
                        }else{
                            subtraction -= element;
                        }
                        return subtraction;
                    });
        return sum;
    }

    //7.
    public static void equalArrays(Scanner scanner) {
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();
        int[] array1 = Arrays.stream(input1.trim().split("\s*,\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] array2 = Arrays.stream(input2.trim().split("\s*,\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                System.out.printf("Arrays are not identical. Found difference at %d index", i);
                return;
            }else{
                sum += array1[i];
            }
        }
        System.out.printf("Arrays are identical. Sum: %d", sum);
    }

    //8.
    public static int condenseArrayToNumber(Scanner scanner){
        String input = scanner.nextLine();
        int[] array = Arrays.stream(input.trim().split("\s*,\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] newArray = new int[array.length-1];
        //counts how many times the original array must be condensed in the newArray
        int count = 0;
        while (count <= array.length - 1){
            for (int i = 0; i < array.length -1; i++) {
                array[i] += array[i+1];
                newArray[i] = array[i];
            }
            array = newArray;
            count ++;
        }
        return newArray[0];
    }

    //9.
    public static void printEveryNthElement(Scanner scanner){
        String input = scanner.nextLine();
        int step = Integer.parseInt(scanner.nextLine());
        List<Object> list = Arrays.stream(input.trim().split("\s*,\s*"))
                .collect(Collectors.toList());
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (count == 0 || count == step) {
                System.out.print(list.get(i) + " ");
                count = 1;
            }else{
                count++;
            }
        }
    }

    //10.
    public static void addAndRemoveElementsFromArray(Scanner scanner){
        String command = scanner.nextLine();
        List<Integer> list = new ArrayList<>();
        int temp = 1;
        while(!command.equals("Stop")){
            switch (command){
                case "add":
                    list.add(temp);
                    temp++;
                    printList(list);
                    break;
                case "remove":
                    if (list.isEmpty() ) {
                        System.out.println("Empty");
                    }else{
                        list.remove(list.size()-1);
                        printList(list);
                    }
                    break;
                default:
                    System.out.println("Invalid command!");
            }
            command = scanner.nextLine();
        }
    }

    public static void printList(List<Integer> list){
        if (list.isEmpty()) {
            System.out.print("Empty");
        }else{
            Arrays.stream(list.toArray()).forEach(e -> System.out.print(e + " "));
        }
        System.out.println();
    }

    //11.
    public static void rotateArray(Scanner scanner){
        Object[] array = Arrays.stream(scanner.nextLine()
                        .trim().split("\\s*,\\s*"))
                .toArray();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Object temp = array[0];
            for (int j = 0; j < array.length - 1 ; j++) {
                array[j] = array[j+1];
            }
            array[array.length-1] = temp;
        }
        printArray(array);
    }

    //12.
    public static void extractNonDecreasingSubsequence(Scanner scanner){
        int[] array = Arrays.stream(scanner.nextLine()
                    .trim()
                    .split("\\s*,\\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();
        List<Integer> list = new ArrayList<>(List.of(array[0]));
        int biggestNum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= biggestNum) {
                list.add(array[i]);
                biggestNum = array[i];
            }
        }
        printList(list);
    }

    //13.
    public static void negativeOrPositiveNumbers(Scanner scanner){
        int[] array = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split("\\s*,\\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Deque<Integer> arraydeque = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                arraydeque.push(array[i]);
            }else{
                arraydeque.offer(array[i]);
            }
        }

        Arrays.stream(arraydeque.toArray()).forEach(e -> System.out.println(e));
    }

    //14.
    public static void firstAndLastKNumbers (Scanner scanner){
        int k = Integer.parseInt(scanner.nextLine());
        int[] array = Arrays.stream(scanner.nextLine()
                    .trim()
                    .split("\\s*,\\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < k ; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        for (int i = k; i >= 1 ; i--) {
            System.out.print(array[(array.length)-i] + " ");
        }
    }

    //15.
    public static void lastKElements(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        int[] array = new int[n];
        array[0] = 1;
        int temp = 0;
        for (int i = 1; i < array.length; i++) {
            temp = 0;
            int left = 0;
            int right = i;
            if (i - k < 0) {
                left = 0;
            }else{
                left = i - k;
            }
            for (int j = left; j < right; j++) {
                temp += array[j];
            }
            array[i] = temp;
        }
        for ( var element : array) {
            System.out.print(element + " ");
        };
    }

    //16.
    public static void processOddNumbers(Scanner scanner){
        //take the input from the console -> convert the elements to Integers
        //double them and collect to List of Integers
        List<Integer> list = Arrays.stream(scanner.nextLine()
                    .trim()
                    .split("\\s*,\\s*"))
                .map(Integer::parseInt)
                .map(e -> e * 2)
                .toList();
        //Make a stream of int numbers, which represents the positions,
        //filter and take only the odd positions and map them to the
        //Integers(obj) from the list. Collect them in an array
        Integer[] oddPositionNumbers = IntStream.range(0, list.size())
                .filter(n -> n % 2 == 1)
                .mapToObj(list::get)
                .toArray(Integer[]::new);

        reverseArray(oddPositionNumbers);
        printArray(oddPositionNumbers);
    }

    //17.
    public static void smallestTwoNumbers(Scanner scanner){
        int[] smallestNumbers = new int[2];
        int[] array = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split("\\s*,\\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int j = 0 ; j < array.length; j++) {
            for (int i = j + 1; i < array.length ; i++) {
                if (array[j] > array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }

        Arrays.stream(array).limit(2).forEach(e -> System.out.print(e+ " "));
    }

    //18.
    public static void listOfProducts(Scanner scanner){
        String[] array = Arrays.stream(scanner.nextLine().trim().split("\s*,\s*"))
                //.sorted()
                .toArray(String[]::new);
        for (int j = 0; j < array.length; j++) {
            for (int i = j + 1; i < array.length ; i++) {
                if (array[j].charAt(0) > array[i].charAt(0)) {
                    String temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }

        printArray(array);
    }

    //19.
    public static void arrayManipulations(Scanner scanner){
        List<Integer> list = Arrays.stream(scanner.nextLine().trim().split(" "))
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toCollection(ArrayList::new));
        String[] commands = scanner.nextLine().trim().split("\s*,\s*");
        for (var command: commands) {
            String[] splittedCommand = command.trim().split(" ");
            switch(splittedCommand[0]){
                case "Add":
                    //list.add(Integer.parseInt(command[1]));
                    addNumberToList(list, Integer.parseInt(splittedCommand[1]));
                    break;
                case "Remove":
                    removeElementToList(list, Integer.parseInt(splittedCommand[1]));
                    break;
                case "RemoveAt":
                    removeElementAtIndex(list, Integer.parseInt(splittedCommand[1]));
                    break;
                case "Insert":
                    insertNumberAtIndex(list, Integer.parseInt(splittedCommand[1]), Integer.parseInt(splittedCommand[2]));
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }

        printList(list);
    }

    public static void addNumberToList(List<Integer> list, int num){
        list.add(num);
    }
    public static void removeElementToList(List<Integer> list, int num){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == num) {
                list.remove(i);
            }
        }
    }
    public static void removeElementAtIndex(List<Integer> list, int index){
        list.remove(index);
    }
    public static void insertNumberAtIndex(List<Integer> list, int num, int index){
        list.add(index, num);
    }

    //20.
    public static void longestSequenceOfIdenticalElements(Scanner scanner){
        int[] array = Arrays.stream(scanner.nextLine().trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        //dictionary<number, count>
        int[] sequence = new int[2];
        int count = 1;
        int checkedNum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (checkedNum == array[i]) {
                count ++;
                if (i != array.length - 1) {
                    continue;
                }
            }
            if (count >= sequence[1]) {
                sequence[0] = checkedNum;
                sequence[1] = count;
            }
            checkedNum = array[i];
            count = 1;
        }
        for (int i = 1; i <= sequence[1]; i++) {
            System.out.print(sequence[0] + " ");
        }
    }
}
