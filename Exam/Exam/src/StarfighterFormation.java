import java.util.*;

public class StarfighterFormation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> formation = new ArrayList<>(
                Arrays.asList(scanner.nextLine().trim().split(", "))
                        .stream()
                        .map(Integer::parseInt)
                        .toList()
        );

        String[] command = Arrays.stream(scanner.nextLine()
                        .trim()
                        .split(" "))
                .toArray(String[]::new);

        while(!command[0].equals("end")){
            formation = switch (command[0]) {
                case "destroy" -> destroy(formation, Integer.parseInt(command[1]));
                case "swap" -> swap(formation,  Integer.parseInt(command[1]),  Integer.parseInt(command[2]));
                case "add" -> add(formation,  Integer.parseInt(command[1]));
                case "insert" -> insert(formation,  Integer.parseInt(command[1]),  Integer.parseInt(command[2]));
                case "center" -> center(formation);
                default -> null;
            };

            if (!command[0].equals("center")) {
                print(formation);
            }
            command = Arrays.stream(scanner.nextLine()
                            .trim()
                            .split(" "))
                    .toArray(String[]::new);
        }
    }
    private static List<Integer> destroy(List<Integer> formation, int position) {
        if (isPositionValid(formation, position)) {
            formation.remove(position);
        }
        return formation;
    }

    private static List<Integer> swap(List<Integer> formation, int fighterPosition1, int fighterPosition2) {
        if (isPositionValid(formation, fighterPosition1) && isPositionValid(formation, fighterPosition2) ) {
            Collections.swap(formation, fighterPosition1, fighterPosition2);
        }else {
        }
        return formation;
    }

    private static List<Integer> add(List<Integer> formation, int idOfNewFighter) {
        formation.add(idOfNewFighter);
        return formation;
    }

    private static List<Integer> insert(List<Integer> formation, int idOfNewFighter, int positionOfNewFighter) {
        if (isPositionValid(formation, idOfNewFighter) && isPositionValid(formation, positionOfNewFighter) ) {
            formation.add(positionOfNewFighter, idOfNewFighter);
        }
        return formation;
    }

    private static List<Integer> center(List<Integer> formation) {
        if (formation.size() % 2 == 1) {
            displayMiddleElement(formation);
        } else {
            displayTwoElementsInTheMiddle(formation);
        }
        return formation;
    }

    private static void displayMiddleElement(List<Integer> formation) {
        int positionOfMiddleElement = formation.size() / 2;
        System.out.println(formation.get(positionOfMiddleElement));
    }

    private static void displayTwoElementsInTheMiddle(List<Integer> formation) {
        int positionOfFirstMiddleElement = (formation.size() / 2) - 1;
        int positionOfSecondMiddleElement = positionOfFirstMiddleElement + 1;
        System.out.println(formation.get(positionOfFirstMiddleElement) + " " + formation.get(positionOfSecondMiddleElement));
    }

    private static boolean isPositionValid(List<Integer> list, int position) {
        return position >= 0 && position < list.size();
    }

    private static void print(List<Integer> list){
        for (Integer number : list) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
