import util.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {

    public static void main(String[] args) {
        String input = InputReader.getInput(3);

        int result = solveProblem2(input);
        System.out.println("Result: "+result);
    }


    private static int solveProblem1(String input) {
        List<int[]> parsedInput = parseInputByRow(input);
        return solve(parsedInput);
    }


    private static List<int[]> parseInputByRow(String input) {
        List<int[]> result = new ArrayList<>();

        String[] lines = input.split("\n");
        for(String line : lines) {
            String[] numberStrs = line.trim().split("\\s+"); // Split line per whitespace
            int[] numbers = new int[numberStrs.length];
            for(int i = 0; i<numberStrs.length; i++) {
                numbers[i] = Integer.parseInt(numberStrs[i]);
            }

            result.add(numbers);
        }

        return result;
    }

    private static int solve(List<int[]> triangles) {
        int validTriangles = 0;
        for(int[] triangle : triangles) {
            int triangleSum = sum(triangle);
            int maxSide = max(triangle);

            int smallTriangleSum = triangleSum-maxSide;
            if(smallTriangleSum > maxSide) {
                validTriangles++;
            }
        }
        return validTriangles;
    }

    private static int sum(int[] numbers) {
        int sum = 0;
        for(int number : numbers) {
            sum += number;
        }
        return sum;
    }

    private static int max(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for(int number : numbers) {
            if(number > max) {
                max = number;
            }
        }
        return max;
    }

    private static int solveProblem2(String input) {
        List<int[]> parsedInput = parseInputByCol(input);
        return solve(parsedInput);
    }

    private static List<int[]> parseInputByCol(String input) {
        List<int[]> result = new ArrayList<>();
        int TRIANGLE_SIDES = 3;

        List<int[]> parseByRow = parseInputByRow(input);
        for(int i = 0; i<TRIANGLE_SIDES; i++) {
            int[] currentTriangle = new int[TRIANGLE_SIDES];
            for(int j = 0; j<parseByRow.size(); j++) {
                if(j > 0 && j % TRIANGLE_SIDES == 0) {
                    result.add(currentTriangle);
                    currentTriangle = new int[TRIANGLE_SIDES];
                }

                currentTriangle[j % TRIANGLE_SIDES] = parseByRow.get(j)[i];
            }
            result.add(currentTriangle);
        }
        return result;
    }
}
