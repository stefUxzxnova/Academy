import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<Integer> formation = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5));

        String operation = "insert".toLowerCase();
        int positionToDestroy = 0;
        int positionOfFirstFighter = 0, positionOfSecondFighter = 1;
        int idOfNewFighter = 4;
        int positionOfNewFighter = 3;

        formation = switch (operation) {
            case "destroy" -> destroy(formation, positionToDestroy);
            case "swap" -> swap(formation, positionOfFirstFighter, positionOfSecondFighter);
            case "add" -> add(formation, idOfNewFighter);
            case "insert" -> insert(formation, idOfNewFighter, positionOfNewFighter);
            case "center" -> center(formation);
            default -> null;
        };

        System.out.println(formation);
    }

    private static List<Integer> destroy(List<Integer> formation, int position) {
        if (isPositionValid(formation, position)) {
            formation.remove(position);
            return formation;
        } else {
            return formation;
        }
    }

    private static List<Integer> swap(List<Integer> formation, int fighterPosition1, int fighterPosition2) {
        Collections.swap(formation, fighterPosition1, fighterPosition2);
        return formation;
    }

    private static List<Integer> add(List<Integer> formation, int idOfNewFighter) {
        formation.add(idOfNewFighter);
        return formation;
    }

    private static List<Integer> insert(List<Integer> formation, int idOfNewFighter, int positionOfNewFighter) {
        formation.add(positionOfNewFighter, idOfNewFighter);
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

    private static boolean isPositionValid(List<Integer> list, int idOfNewFighter, int positionOfNewFighter) {
        return true;
    }
}