import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //reverseNumbersStack(scanner);
        //basicStackOperations(scanner);
        //browser(scanner);
        //System.out.println(simpleCalculator(scanner));
        //decimalToBinaryConverter(scanner);
        //matchingBrackets(scanner);
        //printerQueue(scanner);
        //System.out.println(balanceParentheses(scanner));
        //hotPotato(scanner);
        //maximumElement(scanner);
        //taskScheduler(scanner);
        //documentEditor(scanner);
        //fibonacci(scanner);
        Factorio(scanner);

    }

    //1.
    public static void reverseNumbersStack(Scanner scanner){
        int[] array =Arrays.stream(scanner.nextLine()
                .trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }
       while (!stack.isEmpty()){
           System.out.print(stack.pop() +" ");
       }
    }

    //2.
    public static void basicStackOperations(Scanner scanner){
        //n, s, x - 3 elements
        int[] integers = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] arrayOfNums = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" \s*"))
                .mapToInt(Integer::parseInt)
                .toArray();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < integers[0]; i++) {
            stack.push(arrayOfNums[i]);
        }
        for (int i = 0; i < integers[1]; i++) {
            stack.pop();
        }
        if (stack.contains(integers[2])) {
            System.out.println("True");
        }else{
            System.out.println(smallestNumInStack(stack));
        }

    }
    private static int smallestNumInStack(Deque<Integer> stack){
        if (stack.size() == 0) {
            return 0;
        }
        int smallestNum = stack.peek();
        stack.pop();
        for (int i = 1; i <= stack.size(); i++) {
            if (smallestNum > stack.peek()) {
                smallestNum = stack.peek();
            }
            stack.pop();
        }
        return smallestNum;
    }

    //3.
    public static void browser(Scanner scanner){
        Deque<String> stackURLs = new ArrayDeque<>();
        String instruction = scanner.nextLine();
        while(!instruction.equals("Home")){
            if (instruction.equals("back")) {
                if (stackURLs.isEmpty() || stackURLs.size() == 1) {
                    System.out.println("no previous URLs");
                }else{
                    stackURLs.pop();
                    System.out.println(stackURLs.peek());
                }
            }else{
                stackURLs.push(instruction);
                System.out.println(instruction);
            }
            instruction = scanner.nextLine();
        }
    }

    //4.
    public static int simpleCalculator(Scanner scanner){
        Deque<Integer> stack = new ArrayDeque<>();
        String[] input = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .toArray(String[]::new);
        char operation = ' ';
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("+") || input[i].equals("-")) {
                operation = input[i].charAt(0);
            }
            else{
                if (stack.size() == 1) {
                    int num = 0;
                    switch (operation){
                        case '+':
                            num = stack.peek() + Integer.parseInt(input[i]);
                            break;
                        case '-':
                            num = stack.peek() - Integer.parseInt(input[i]);
                            break;
                    }
                    stack.pop();
                    stack.push(num);
                }else{
                    stack.push(Integer.parseInt(input[i]));
                }
            }
        }
        return stack.peek();
    }

    //5.
    public static void decimalToBinaryConverter(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        if (n == 0) {
            System.out.println(0);
            return;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        while(n != 0){
            stack.push(n % 2);
            n /= 2;
        }
        int stackInitialSize = stack.size();
        for (int i = 0; i < stackInitialSize; i++) {
            System.out.print(stack.pop());
        }
    }
    //6.
    public static void matchingBrackets(Scanner scanner) {
        String[] expression = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split("\s*"))
                .filter(e -> !e.equals(""))
                .toArray(String[]::new);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals("(")) {
                stack.push(i);
            } else if (expression[i].equals(")")) {
                StringBuilder str = new StringBuilder("(");
                for (int j = stack.peek() + 1; j <= i - 1; j++) {
                    if (j == i -1 ) {
                        str.append(expression[j]);
                    }else {
                        str.append(expression[j] + " ");
                    }
                }
                str.append(")");
                System.out.print(str);
                stack.pop();
                System.out.println();
            }

        }
    }

    //7.
    public static void printerQueue(Scanner scanner){
        String input = scanner.nextLine();
        Deque<String> que = new ArrayDeque<>();
        while (!input.equals("print")){
            if (input.equals("cancel")) {
                if (que.size() == 0) {
                    System.out.println("Standby");
                }else{
                    System.out.println("Canceled " + que.peek());
                    que.poll();
                }
            }else{
                que.offer(input);
            }
            input = scanner.nextLine();
        }
        for(var file : que)
        {
            System.out.println(file);
        }
    }

    //8.
    public static boolean balanceParentheses(Scanner scanner){
        String[] parentheses = Arrays.stream(scanner.nextLine()
                    .trim()
                    .split("\s*"))
                .toArray(String[]::new);

        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < parentheses.length; i++) {
            if ((parentheses[i].equals("{")) || (parentheses[i].equals("(")) || (parentheses[i].equals("["))) {
                stack.push(parentheses[i]);
            }else{
                int lastParenthesis = (int)stack.peek().charAt(0);
                switch (lastParenthesis) {
                    case 40:
                        if (!parentheses[i].equals(")")) {
                            return false;
                        }
                        break;
                    case 91:
                        if (!parentheses[i].equals("]")) {
                            return false;
                        }
                        break;
                    case 123:
                        if (!parentheses[i].equals("}")) {
                            return false;
                        }
                        break;
                }
                stack.pop();
            }
        }
        return true;
    }

    //9.
    public static void hotPotato(Scanner scanner){
        Deque<String> players = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .collect(Collectors.toCollection(ArrayDeque<String>::new));
        int num = Integer.parseInt(scanner.nextLine());
        while(players.size() > 1){
            for (int i = 1; i < num ; i++) {
                String removedPlayer = players.getFirst();
                players.poll();
                players.offer(removedPlayer);
            }
            System.out.println("Remove " + players.poll());
        }
        System.out.println("Last is " + players.peek());
    }

    //10.
    public static void maximumElement(Scanner scanner){
        int numberOfCommands = Integer.parseInt(scanner.nextLine());
        while(numberOfCommands < 1 || numberOfCommands > Math.pow(10, 9)){
            numberOfCommands = Integer.parseInt(scanner.nextLine());
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i <= numberOfCommands; i++) {
            Integer[] command = Arrays.stream(scanner.nextLine()
                            .trim()
                            .split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            switch (command[0]){
                case 1:
                    stack.push(command[1]);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    System.out.println(maxElementInDeque(stack));
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }

    private static int maxElementInDeque(Deque<Integer> stack){
        if (stack.size() == 0) {
            return 0;
        }
        int maxNum = stack.peek();
        stack.pop();
        for (int i = 1; i <= stack.size(); i++) {
            if (maxNum < stack.peek()) {
                maxNum = stack.peek();
            }
            stack.pop();
        }
        return maxNum;
    }

    //11.
    public static void taskScheduler(Scanner scanner){
        PriorityQueue<Task> queue = new PriorityQueue<>(
                (a, b) ->{
                    if (a.getPriority() == b.getPriority()) {
                        return 0;
                    }

                    if (a.getPriority() < b.getPriority()) {
                        return 1;//върни първото
                    }else{
                        return -1;//върни второто
                    }
                }
        );
        while(true){
            String[] input = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .toArray(String[]::new);
            if (input[0].equals("Add")) {
                Task task = new Task(input[1], Integer.parseInt(input[2]));
                queue.offer(task);
            }else if(input[0].equals("getNextTask")){
                System.out.println(queue.peek().getName() + " - " + queue.peek().getPriority());
            }else{
                System.out.println("Invalid command");
            }
        }

    }

    //13.
    public static void documentEditor(Scanner scanner){
        String[] command = Arrays.stream(scanner.nextLine().trim().split("[(\")]+"))
                .toArray(String[]::new);
        Deque<String> undoStack = new ArrayDeque<>();
        Deque<String> redoStack = new ArrayDeque<>();
        while(!command[0].equals("End")){
            String temp = null;
            switch (command[0]){
                case "Insert":
                    undoStack.push(command[1]);
                    break;
                case "Undo":
                    temp = undoStack.peek();
                    undoStack.pop();
                    redoStack.push(temp);
                    break;
                case "Redo":
                    temp = redoStack.peek();
                    redoStack.pop();
                    undoStack.push(temp);
                    break;
            }
            List<String> reversed = new ArrayList<>(undoStack);
            Collections.reverse(reversed);
            for (var item : reversed) {
                System.out.print(item + " ");
            }
            System.out.println();
            command =  Arrays.stream(scanner.nextLine().trim().split("[(\")]+"))
                    .toArray(String[]::new);
        }
    }

    //14.
    public static void fibonacci(Scanner scanner){
        int n = Integer.parseInt(scanner.nextLine());
        while (n < 1 || n > 49){
            n = Integer.parseInt(scanner.nextLine());
        }
        System.out.println(fibRecursion(n));
    }

    //memorization - we create cache where we store the inputs and the results
    //for optimized performance and to avoid expensive or time-consuming computing
    //...they can be reused later
    private static Map<Integer, Integer> cache = new HashMap<>();
    private static int fibRecursion(int n){
        if (n <= 1) {
            return n;
        }

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int result = fibRecursion(n - 1) + fibRecursion(n - 2);
        cache.put(n, result);
        return result;
    }

    //15.
    public static void Factorio(Scanner scanner){
        Map<String, Integer> robots = Arrays.stream(scanner.nextLine()
                    .trim()
                    .split("\\|"))
                .map(entry -> entry.split("-"))
                .collect(Collectors.toMap(
                        part -> part[0], //Key before the dash
                        part -> Integer.parseInt(part[1]),
                        (existingValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
        ArrayDeque<String> freeRobots = new ArrayDeque<>(robots.keySet());


        int[] timeArray = Arrays.stream(scanner.nextLine().split(":"))
                .mapToInt(Integer::parseInt)
                .toArray();
        LocalTime time = LocalTime.of(timeArray[0], timeArray[1], timeArray[2]);

        Map<String, LocalTime> startTime = new HashMap<>();
        for (Map.Entry<String, Integer> entry : robots.entrySet()) {
            startTime.put(entry.getKey(), LocalTime.of(time.getHour(),time.getMinute(),time.getSecond() + 1));
        }

        long seconds = convertTimeToSeconds(time);
        ArrayDeque<String> products = new ArrayDeque<>();
        String product = scanner.nextLine();
        while (!product.equals("End")){
            products.offer(product);
            product = scanner.nextLine();
        }

        while(!product.isEmpty()){

            long temp = convertTimeToSeconds(startTime.get(freeRobots.peekFirst()));
            if (temp <= seconds ) {
                String queuedRobot = freeRobots.peekFirst();
                freeRobots.poll();
                freeRobots.offer(queuedRobot);
            }else{
                LocalTime time1 = LocalTime.of(convertSecondsToTime(temp).getHour(), convertSecondsToTime(temp).getMinute(), convertSecondsToTime(temp).getSecond());
                System.out.println(freeRobots.peekFirst() + " - " + products.peekFirst() + " [" + time1 + "}");
                String queuedRobot = freeRobots.peekFirst();
                freeRobots.poll();
                freeRobots.offer(queuedRobot);
                startTime.put(queuedRobot, time);
                products.poll();
            }
            time.plusSeconds(1);
        }
    }

    private static long convertTimeToSeconds(LocalTime time){
        return time.getHour() * 3600 + time.getMinute() * 60 + time.getSecond();
    }

    private static LocalTime convertSecondsToTime(long seconds){

        int hours = (int)seconds % 3600;
        seconds /= 3600;
        int minutes = (int)seconds % 60;
        seconds /= 60;
        int sec = (int)seconds;
        LocalTime time = LocalTime.of(hours, minutes, sec);
        return time;
    }

}