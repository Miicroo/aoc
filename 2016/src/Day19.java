public class Day19 {

    private static final int INVALID_ELF = -1;

    public static void main(String[] args) {
        int result = solveProblem2(3014603);
        System.out.println("Result: " + result);
    }

    private static int solveProblem1(int numElves) {
        int[] elves = new int[numElves];
        for (int i = 0; i < numElves; i++) {
            elves[i] = i + 1;
        }

        int elvesLeft = numElves;
        int currentElf = 0;

        do {
            int elfOut = getNextAvailableElf(elves, currentElf);
            elves[elfOut] = INVALID_ELF;
            elvesLeft--;

            if (elvesLeft > 1) {
                currentElf = getNextAvailableElf(elves, currentElf);
            }
        } while (elvesLeft > 1);

        return currentElf + 1;
    }

    private static int solveProblem2(int numElves) {
        int[] elves = new int[numElves];
        for (int i = 0; i < numElves; i++) {
            elves[i] = i + 1;
        }

        int elvesLeft = numElves;
        int currentElf = 0;

        do {
            int elfOut = getNextAvailableElf(elves, currentElf, elvesLeft / 2);
            elves[elfOut] = INVALID_ELF;
            elvesLeft--;

            if (elvesLeft > 1) {
                currentElf = getNextAvailableElf(elves, currentElf);
                if(elvesLeft % 1000 == 0) {
                    System.out.println(elvesLeft);
                }
            }
        } while (elvesLeft > 1);

        return elves[currentElf];
    }

    private static int getNextAvailableElf(int[] elves, int currentElf) {
        for (int i = 1; i < elves.length; i++) {
            int nextElfIndex = (currentElf + i) % elves.length;
            if (elves[nextElfIndex] != INVALID_ELF) {
                return nextElfIndex;
            }
        }
        return -1;
    }

    private static int getNextAvailableElf(int[] elves, int currentElf, int stepsAway) {
        for (int i = 0; i < stepsAway; i++) {
            currentElf = getNextAvailableElf(elves, currentElf);
        }
        return currentElf;
    }
}
