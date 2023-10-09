import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //countRealNumbers(scanner);
        //averageStudentsGrades(scanner);
        //countSymbols(scanner);
        //phonebook(scanner);
        //handsOnCards(scanner);
        //populationCounter(scanner);
        //wordSynonyms(scanner);
        //oddOccurrences(scanner);
        //wordFilter(scanner);
        //citiesByContinentAndCountry(scanner);
        //largestThreeNums(scanner);
        //countCharsInAString(scanner);
        //parkingSystem(scanner);
        //studentAcademy(scanner);
        //companyUsers(scanner);
        //parkingLot(scanner);
        //partyList(scanner);
        //warGame(scanner);
        //uniqueUsernames(scanner);
        //setsOfElements(scanner);
        periodicTable(scanner);
    }

    //1.
    public static void countRealNumbers(Scanner scanner){
        double[] numbers = Arrays.stream(scanner.nextLine().trim().split(" "))
                .mapToDouble(e -> Double.parseDouble(e))
                .toArray();
        Map<Double, Integer> occurrenceOfNumbers = new LinkedHashMap<>();
        for (double num : numbers) {
            occurrenceOfNumbers.put(num, occurrenceOfNumbers.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry entry : occurrenceOfNumbers.entrySet()) {
            System.out.printf("%.1f -> %d %n", entry.getKey(), entry.getValue());
        }
    }

    //2.
    public static void averageStudentsGrades(Scanner scanner){

        //key -> name of the student / value -> arraylist with grades
        TreeMap<String, ArrayList<Double>> studentRecords = new TreeMap<>();
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            //we take the name of the student and their grade form the console
            String[] input = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .toArray(String[]::new);
            //check if the student's name already exists in the map
            //if the map already contains the name -> the grade is added to the arraylist
            if (studentRecords.containsKey(input[0])) {
                studentRecords.get(input[0]).add(Double.parseDouble(input[1]));
            }else{
                //else -> the student name is added to the map with their first record
                ArrayList<Double> grades = new ArrayList<>();
                grades.add(Double.parseDouble(input[1]));
                studentRecords.put(input[0], grades);
            }
        }

        //we traverse the map and print the student's names with their average grades
        for (Map.Entry<String, ArrayList<Double>> entry : studentRecords.entrySet())
        {
            String name = entry.getKey().toString();
            ArrayList<Double> grades = entry.getValue();
            double average = calculateAverage(grades);
            System.out.printf("%s -> ", name);
            printList(grades);
            System.out.printf("(avg: %.2f)%n", average);
        }
    }

    //method that calculates the average grades
    private static double calculateAverage (ArrayList<Double> list){
        double average = 0;
        for (int i = 0; i < list.size(); i++) {
            average += list.get(i);
        }
        return average / (list.size());
    }
    private static void printList(ArrayList<Double> list){
        for (double num : list) {
            System.out.printf("%.2f ", num);
        }
    }

    //3.
    public static void countSymbols(Scanner scanner){
        String[] characters = Arrays.stream(scanner.nextLine().trim().split(""))
                .toArray(String[]::new);
        Map<String, Integer> charactersCount = new TreeMap<>();
        for ( String c : characters) {
            charactersCount.put(c, charactersCount.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : charactersCount.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    //4.
    public static void phonebook(Scanner scanner){
        Map<String, String> phonebook = new HashMap<>();
        while (true){
            String[] command = Arrays.stream(scanner.nextLine().split("-"))
                    .toArray(String[]::new);
            if (command[0].equals("search")) {
                break;
            }
            phonebook.put(command[0], command[1]);
        }

        while (true){
            String name = scanner.nextLine();
            if (name.equals("stop")) {
                break;
            }
            if (phonebook.containsKey(name)) {
                System.out.printf("%s -> %s%n", name, phonebook.get(name));
            }else{
                System.out.printf("Contact %s not found.%n", name);
            }
        }
    }

    //5.
    public static void handsOnCards(Scanner scanner){
        //stores the players and their set of cards
        Map<String, Set<String>> players = new HashMap<>();
        //add players to the game until we receive JOKER
        while (true){
            String[] player = Arrays.stream(scanner.nextLine()
                    .trim()
                    .split(": |, "))
                    .toArray(String[]::new);

            if (player[0].equalsIgnoreCase("JOKER")) {
                break;
            }

            //if we already have information about a player, we add new cards to its set
            //if we don't have this player in the map, we put it in the map and add new set of cards
            if (!players.containsKey(player[0])) {
                Set<String> cards = new HashSet<>();
                for (int i = 1; i < player.length; i++) {
                    cards.add(player[i]);
                }
                players.put(player[0], cards);
            }
            else{
                Set<String> cardsOfPlayer = players.get(player[0]);
                for (int i = 1; i < player.length; i++) {
                    cardsOfPlayer.add(player[i]);
                }
                players.put(player[0], cardsOfPlayer);
            }
        }

        //we traverse the map entries and for each of them calculate the sets of cards
        for (Map.Entry<String, Set<String>> entry : players.entrySet()) {
            //we use to accumulate total sum of cards
            int temp = 0;
            Set<String> cards = entry.getValue();
            //we need the letters/digits we have in the card
            for (String element : cards) {
                //we use cardCount to calculate the current card
                int cardCount = 0;
                String[] cardElements = Arrays.stream(element.split(""))
                        .toArray(String[]::new);

                //check if the first element(power) of the card is digit
                if (Character.isDigit(element.charAt(0))){
                    cardCount = Integer.parseInt(cardElements[0]);
                }else{
                    switch (cardElements[0]){
                        case "J":
                            cardCount = 11;
                            break;
                        case "Q":
                            cardCount = 12;
                            break;
                        case "K":
                            cardCount = 13;
                            break;
                        case "A":
                            cardCount = 14;
                            break;
                    }
                }

                //if we have length greater than 2 -> the power of the number is 10
                if (cardElements.length == 2) {
                    cardCount *= getValue(cardElements[1]);
                }else {
                    cardCount = 10 * getValue(cardElements[2]);
                }
                //
                temp += cardCount;
            }

            System.out.printf("%s: %d%n", entry.getKey(), temp);
        }
    }
    public static int getValue(String letter){
        switch (letter){
            case "S":
                return 4;
            case "H":
                return 3;
            case "D":
                return 2;
            case "C":
                return 1;
            default:
                return -1;
        }
    }

    //6.
    public static void populationCounter(Scanner scanner){
        //Map<Country, Map<city, population>>
        Map<String, Map<String, Long>> countries = new HashMap<>();
        //add country info until we receive report
        while (true){
            String[] input = Arrays.stream(scanner.nextLine()
                            .trim()
                            .split("\\|"))
                    .toArray(String[]::new);

            if (input[0].equalsIgnoreCase("report")) {
                break;
            }
            if (countries.containsKey(input[1])) {
                Map<String, Long> cityPopulation = countries.get(input[1]);
                cityPopulation.put(input[0], Long.parseLong(input[2]));
                countries.put(input[1], cityPopulation);
            }else{
                Map<String, Long> cityPopulation = new HashMap<>();
                cityPopulation.put(input[0], Long.parseLong(input[2]));
                countries.put(input[1], cityPopulation);
            }
        }
        for (Map.Entry<String, Map<String, Long>> entry : countries.entrySet()) {
            long population = 0;
            Map<String, Long> cityPopulationMap = entry.getValue();
            for (Map.Entry<String, Long> city : cityPopulationMap.entrySet()) {
                Long cityPopulation = cityPopulationMap.get(city.getKey());
                population += cityPopulation;
            }
            System.out.printf("%s (total population: %d)%n", entry.getKey(), population);
            for (Map.Entry<String, Long> city : cityPopulationMap.entrySet()) {
                System.out.printf("=>%s: %d%n", city.getKey(), city.getValue());
            }
        }
    }

    //7.
    public static void wordSynonyms(Scanner scanner){
        Map<String, List<String>> dictionary = new HashMap<>();
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {

            String word = scanner.nextLine();
            String synonym = scanner.nextLine();
            List<String> synonyms;
            if (dictionary.containsKey(word)) {
                synonyms = dictionary.get(word);
            }
            else{
                synonyms = new ArrayList<>();
            }
            synonyms.add(synonym);
            dictionary.put(word, synonyms);
        }

        for (Map.Entry<String, List<String>> entry : dictionary.entrySet()) {

            System.out.print(entry.getKey() + " - ");
            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.print(entry.getValue().get(i) + ", ");
            }
            System.out.println();
        }
    }

    //8.
    public static void oddOccurrences(Scanner scanner){
        String[] input = Arrays.stream(scanner.nextLine().trim().split(" "))
                .toArray(String[]::new);
        Map<String, Integer> wordOccurrence = new HashMap<>();
        for (String word : input) {
            wordOccurrence.put(word.toLowerCase(), wordOccurrence.getOrDefault(word.toLowerCase(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : wordOccurrence.entrySet()) {
            if (entry.getValue() % 2 == 1 ) {
                System.out.print(entry.getKey() + ", ");
            }
        }
    }

    //9.
    public static void wordFilter(Scanner scanner){
        String[] words = Arrays.stream(scanner.nextLine().trim().split(" "))
                .toArray(String[]::new);
        Map<String, Integer> wordCharCount = new LinkedHashMap<>();
        for (String word : words) {
            if (word.length() % 2 == 0) {
                wordCharCount.put(word, word.length());
            }
        }
        for (Map.Entry<String,Integer> entry : wordCharCount.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    //10.
    public static void citiesByContinentAndCountry(Scanner scanner) {
        //our main map, which includes continents -> countries ->  (set of cities)
        Map<String, Map<String, Set<String>>> worldMap = new LinkedHashMap<>();
        int num = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < num; i++) {
            //the nested map (Country -> set of Cities)
            Map<String, Set<String>> countryCity = new LinkedHashMap<>();
            //words[0] = continent, words[1] = country, words[2] = city
            String[] words = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .toArray(String[]::new);

            Set<String> cities;
            //we start from the outer side of the map
            //we check if we already have this continent in the map
            if (worldMap.containsKey(words[0])) {
                //we retrieve the continent's cities
                countryCity = worldMap.get(words[0]);
                //check if the city is already in the continent map
                if (countryCity.containsKey(words[1])) {
                    cities = countryCity.get(words[1]);
                    cities.add(words[2]);
                    countryCity.put(words[1], cities);
                }else{
                    cities = new LinkedHashSet<>();
                    cities.add(words[2]);
                    countryCity.put(words[1], cities);
                }
            }
            else{
                cities = new LinkedHashSet<>();
                cities.add(words[2]);
                countryCity.put(words[1], cities);
                worldMap.put(words[0], countryCity);
            }
        }

        //printing
        for (var entry : worldMap.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (var country : entry.getValue().entrySet()) {
                System.out.print(country.getKey() + " -> ");
                for (String city : country.getValue()) {
                    System.out.printf("%s, ", city);
                }
                System.out.println();
            }
        }

    }

    //11.
    public static void largestThreeNums(Scanner scanner){
        List<Integer> list = Arrays.stream(scanner.nextLine().trim().split(" "))
                .sorted(Comparator.reverseOrder())
                .map(Integer::parseInt)
                .limit(3)
                .collect(Collectors.toCollection(LinkedList::new));

        list.stream().forEach(e -> System.out.print(e + " "));
    }

    //12.
    public static void countCharsInAString(Scanner scanner){
        Map<String, Integer> charOccurrence = new HashMap<>();
        String[] charsArray = Arrays.stream(scanner.nextLine()
                        .trim()
                        .replaceAll("\\s+", "")
                        .split(""))
                .toArray(String[]::new);

        for (String symbol : charsArray) {
            charOccurrence.put(symbol, charOccurrence.getOrDefault(symbol, 0) + 1);
        }
        printHashMap(charOccurrence);
    }

    private static void printHashMap(Map<String ,Integer> map){
        for (Map.Entry entry : map.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }

    //13.
    public static void parkingSystem(Scanner scanner){
        Map<String, String> usersPlates = new LinkedHashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] command = Arrays.stream(scanner.nextLine().trim().split(" "))
                    .toArray(String[]::new);
            switch (command[0]){
                case "Register":
                    if (!usersPlates.containsKey(command[1])) {
                        usersPlates.put(command[1], command[2]);
                        System.out.printf("%s registered %s successfully.%n", command[1], command[2]);
                    }else{
                        System.out.printf("ERROR: already registered with plate number %s.%n", usersPlates.get(command[1]));
                    }
                    break;
                case "Unregister":
                    if (usersPlates.containsKey(command[1])) {
                        usersPlates.remove(command[1]);
                        System.out.printf("%s unregistered successfully.%n", command[1]);
                    }else{
                        System.out.printf("ERROR: user %s not found.%n", command[1]);
                    }
                    break;
                default:
                    System.out.println("Invalid command!.");
            }
        }
        for (Map.Entry entry : usersPlates.entrySet()) {
            System.out.printf("%s => %s%n", entry.getKey(), entry.getValue());
        }
    }

    //14.
    public static void studentAcademy(Scanner scanner){
        Integer n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> studentsGrades = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String name = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());
            List<Double> grades;
            if (studentsGrades.containsKey(name)) {
                grades = studentsGrades.get(name);
            }else{
                grades = new ArrayList<>();
            }
            grades.add(grade);
            studentsGrades.put(name, grades);
        }
        for (Map.Entry<String,List<Double>> entry : studentsGrades.entrySet()) {
            if (getAverageGrade(entry.getValue()) < 4.50) {
                studentsGrades.remove(entry.getKey());
            }
        }
        for (var entry : studentsGrades.entrySet()) {
            System.out.printf("%s -> %.2f%n", entry.getKey(), getAverageGrade(entry.getValue()));
        }
    }
    private static double getAverageGrade(List<Double> grades){
        double average = 0;
        for (double grade : grades) {
            average += grade;
        }
        return average/grades.size();
    }

    //15.
    public static void companyUsers(Scanner scanner){
        Map<String, Set<String>> companyEmployeesIds = new LinkedHashMap<>();
        while (true){
            String[] input = Arrays.stream(scanner.nextLine().trim().split(" -> "))
                    .toArray(String[]::new);
            if (input[0].equalsIgnoreCase("End")) {
                break;
            }
            Set<String> ids;
            if (companyEmployeesIds.containsKey(input[0])) {
                ids = companyEmployeesIds.get(input[0]);
            }else{
                ids = new HashSet<>();
            }
            ids.add(input[1]);
            companyEmployeesIds.put(input[0], ids);
        }
        for(var entry : companyEmployeesIds.entrySet()) {
            System.out.println(entry.getKey());
            for (String id : entry.getValue()) {
                System.out.printf("-- %s%n", id);
            }

        }
    }


    //16.
    public static void parkingLot(Scanner scanner){

        Set<String> plates = new LinkedHashSet<>();
        while (true){
            String[] input = Arrays.stream(scanner.nextLine()
                            .trim()
                            .split(", "))
                    .toArray(String[]::new);
            if (input[0].equalsIgnoreCase("End")) {
                break;
            }
            switch (input[0]){
                case "IN":
                    plates.add(input[1]);
                    break;
                case "OUT":
                    plates.remove(input[1]);
                    break;
                default:
                    System.out.println("INVALID COMMAND");
            }
        }

        //print
        if (plates.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        }else{
            for ( String plate : plates) {
                System.out.println(plate);
            }
        }
    }

    //17.
    public static void partyList(Scanner scanner){
        List<String> vipGuests = new ArrayList<>();
        List<String> regularGuests = new ArrayList<>();
        while (true){
            String guest = scanner.nextLine();
            if (guest.equalsIgnoreCase("PARTY")) {
                break;
            }
            if (guest.length() != 8) {
                System.out.println("Invalid guest");
                continue;
            }
            if(Character.isDigit(guest.charAt(0))){
                vipGuests.add(guest);
            }else{
                regularGuests.add(guest);
            }
        }
        while (true) {
            String cameGuest = scanner.nextLine();
            if (cameGuest.equalsIgnoreCase("END")) {
                break;
            }
            if (!(vipGuests.contains(cameGuest) || regularGuests.contains(cameGuest))) {
                System.out.println("Invalid!");
                continue;
            }
            if(vipGuests.contains(cameGuest)){
                vipGuests.remove(cameGuest);
            }else{
                regularGuests.remove(cameGuest);
            }
        }
        System.out.println(vipGuests.size() + regularGuests.size());
        printListElements(vipGuests);
        printListElements(regularGuests);
    }

    private static <T> void printListElements(List<T> list ){
        for (T item : list) {
            System.out.println(item);
        }
    }

    //18.
    public static void warGame(Scanner scanner){
        ArrayDeque<Integer> firstPlayerDeck = Arrays.stream(scanner.nextLine().trim().split(" "))
                .map(e -> Integer.parseInt(e))
                .distinct()
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondPlayerDeck = Arrays.stream(scanner.nextLine().trim().split(" "))
                .map(e -> Integer.parseInt(e))
                .distinct()
                .collect(Collectors.toCollection(ArrayDeque::new));


        for (int i = 1; i <= 50; i++) {
            int card = 0;
            if (firstPlayerDeck.peek() > secondPlayerDeck.peek()) {
                card = secondPlayerDeck.poll();
                firstPlayerDeck.offer(card);
            }else{
                card = firstPlayerDeck.poll();
                secondPlayerDeck.offer(card);
            }

            if (firstPlayerDeck.isEmpty()) {
                System.out.println("Second player wins!");
                return;
            } else if (secondPlayerDeck.isEmpty()) {
                System.out.println("First player wins!");
                return;
            }
        }

        if (secondPlayerDeck.size() > firstPlayerDeck.size()) {
            System.out.println("Second player wins!");
        } else if (secondPlayerDeck.size() == firstPlayerDeck.size()) {
            System.out.println("Tie");
        } else{
            System.out.println("First player wins!");
        }

    }

    //19.
    public static void uniqueUsernames(Scanner scanner){
        int num = Integer.parseInt(scanner.nextLine());
        Set<String> usernames = new LinkedHashSet<>();
        for (int i = 0; i < num; i++) {
            String username = scanner.nextLine();
            usernames.add(username);
        }
        System.out.println(usernames);
    }

    //20.
    private static void setsOfElements(Scanner scanner) {
        int[] lengths = Arrays.stream(scanner.nextLine().trim().split(" "))
                .mapToInt(e -> Integer.parseInt(e))
                .limit(2)
                .toArray();

        Set<Integer> setOne = new LinkedHashSet<>();
        Set<Integer> setTwo = new LinkedHashSet<>();
        for (int i = 0; i < (lengths[0] + lengths[1]); i++) {
            int element = Integer.parseInt(scanner.nextLine());
            if (i < lengths[0]) {
                setOne.add(element);
            }else{
                setTwo.add(element);
            }
        }
        for (int element : setOne) {
            if (setTwo.contains(element)) {
                System.out.print(element + " ");
            }
        }
    }

    //21.
    private static void periodicTable(Scanner scanner) {

        Set<String> compounds = new TreeSet<>();
        int num = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < num; i++) {
            String[] input = Arrays.stream(scanner.nextLine()
                            .trim()
                            .split(" "))
                    .toArray(String[]::new);

            for (String item : input) {
                compounds.add(item);
            }
        }
        System.out.println(compounds);
    }
}