import java.util.*;
import java.util.stream.Collectors;

public class GalacticSenatorsVotes {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] votes = Arrays.stream(scanner.nextLine().trim().split(", "))
                .toArray(String[]::new);

        Map<String, Integer> countedVotes = new HashMap<>();
        for (String vote : votes) {
            countedVotes.put(vote, countedVotes.getOrDefault(vote, 0) + 1);
        }

        List<String> majorityVotes = new ArrayList<>();
        int biggestMajorityVoteCount = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> vote : countedVotes.entrySet()) {
            if (vote.getValue() > biggestMajorityVoteCount) {
                biggestMajorityVoteCount = vote.getValue();
                majorityVotes.clear();
                majorityVotes.add(vote.getKey());
            } else if (vote.getValue() == biggestMajorityVoteCount) {
                majorityVotes.add(vote.getKey());
            }
        }

        if (majorityVotes.size() == 1) {
            System.out.println(majorityVotes.get(0));
        } else if (majorityVotes.size() == 3) {
            System.out.println("Tie");
        } else{
            if (majorityVotes.contains("Yes") && majorityVotes.contains("Abstain")) {
                System.out.println("Yes");
            } else if (majorityVotes.contains("No") && majorityVotes.contains("Abstain")) {
                System.out.println("No");
            } else{
                System.out.println("Tie");
            }
        }
    }
}
