import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day18 {

    private static final String INPUT = ".^^.^^^..^.^..^.^^.^^^^.^^.^^...^..^...^^^..^^...^..^^^^^^..^.^^^..^.^^^^.^^^.^...^^^.^^.^^^.^.^^.^.";
    private static final char TRAP = '^',
            SAFE = '.';

    public static void main(String[] args) {
        int result = solveProblem2(INPUT);
        System.out.println("Result: " + result);
    }

    private static int solveProblem1(String input) {
        int rowCount = 40;
        return solve(input, rowCount);
    }

    private static int solveProblem2(String input) {
        int rowCount = 400000;
        return solve(input, rowCount);
    }

    private static int solve(String input, int rowCount) {
        List<String> map = new ArrayList<>();
        map.add(input);

        String row = input;
        for (int i = 0; i < rowCount - 1; i++) {
            row = getNextRow(row);
            map.add(row);
        }

        return count(map, SAFE);
    }

    private static String getNextRow(String input) {
        String nextRow = "";
        String inputWithWalls = "." + input + ".";

        for (int i = 1; i < inputWithWalls.length() - 1; i++) {
            char c = nextRowCharacter(inputWithWalls.substring(i - 1, i + 2));
            nextRow += c;
        }

        return nextRow;
    }

    private static char nextRowCharacter(String neighbours) {
        assert neighbours.length() == 3;

        char left = neighbours.charAt(0);
        char right = neighbours.charAt(2);

        boolean isTrap = left == TRAP && right == SAFE; // mid can be anything now
        isTrap |= left == SAFE & right == TRAP; // mid is still not considered

        return isTrap ? TRAP : SAFE;
    }

    private static int count(List<String> list, char match) {
        int count = 0;
        for (String s : list) {
            for (char c : s.toCharArray()) {
                if (c == match) {
                    count++;
                }
            }
        }
        return count;
    }
}
