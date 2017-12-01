import util.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Day15 {

    public static void main(String[] args) {
        String[] input = InputReader.getInput(15).split("\n");
        int result = solveProblem2(input);
        System.out.println("Result: "+result);
    }

    private static int solveProblem1(String[] input) {
        List<Disc> discs = parse(input);
        return solve(discs);
    }

    private static int solveProblem2(String[] input) {
        List<Disc> discs = parse(input);
        discs.add(new Disc(discs.size(), 11, 0));
        return solve(discs);
    }

    private static int solve(List<Disc> discs) {
        int shouldPressButtonTime = 0;
        boolean passedAllDiscs;

        do {
            passedAllDiscs = true;
            for(int i = 0; i<discs.size(); i++) {
                int time = shouldPressButtonTime+i+1;
                if(!discs.get(i).willPassAt(time)) {
                    passedAllDiscs = false;
                    shouldPressButtonTime++;
                    break;
                }
            }
        } while(!passedAllDiscs);

        return shouldPressButtonTime;
    }

    private static List<Disc> parse(String[] input) {
        List<Disc> discs = new ArrayList<>();
        String[] crap = new String[]{"Disc #", "has ", "positions; at time=0, it is at position ", "\\."};
        for(String line : input) {
            for(String c : crap) {
                line = line.replaceAll(c, "");
            }

            String[] data = line.split(" ");
            int num = Integer.parseInt(data[0]);
            int positions = Integer.parseInt(data[1]);
            int startPosition = Integer.parseInt(data[2]);

            discs.add(new Disc(num, positions, startPosition));
        }

        return discs;
    }

    private static class Disc {
        private int num, positions, startPosition;

        public Disc(int num, int positions, int startPosition) {
            this.num = num;
            this.positions = positions;
            this.startPosition = startPosition;
        }

        boolean willPassAt(int time) {
            return (startPosition+time) % positions == 0;
        }
    }
}
