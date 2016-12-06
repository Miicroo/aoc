import util.InputReader;

import java.util.*;

public class Day6 {

    public static void main(String[] args) {
        String input = InputReader.getInput(6);
        String result = solveProblem2(input);
        System.out.println("Result: " + result);
    }

    private static String solveProblem1(String input) {
        return solve(input, getMaxCharPrioComparator());
    }

    private static String solveProblem2(String input) {
        return solve(input, getMinCharPrioComparator());
    }

    private static String solve(String input, Comparator<CharPrio> comparator) {
        String[] rows = input.split("\n");
        String[] cols = getColumnsFromRows(rows);

        char[] mostCommon = new char[cols.length];
        for(int i = 0; i<cols.length; i++) {
            mostCommon[i] = mostCommon(cols[i], comparator);
        }
        return new String(mostCommon);
    }

    private static String[] getColumnsFromRows(String[] rows) {
        String[] cols = new String[rows[0].length()];
        for(int i = 0; i<cols.length; i++) {
            cols[i] = "";
        }

        for(String row : rows) {
            for(int i = 0; i<row.length(); i++) {
                cols[i] += row.charAt(i);
            }
        }
        return cols;
    }

    private static char mostCommon(String str, Comparator<CharPrio> comparator) {
        Map<Character, Integer> data = new HashMap<>();

        for(char c : str.toCharArray()) {
            if(data.containsKey(c)) {
                data.put(c, data.get(c)+1);
            } else {
                data.put(c, 1);
            }
        }

        List<CharPrio> charPrio = convertMapToData(data);
        Collections.sort(charPrio, comparator);

        return charPrio.get(0).getChar();
    }

    private static List<CharPrio> convertMapToData(Map<Character, Integer> mapping) {
        List<CharPrio> data = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : mapping.entrySet()) {
            data.add(new CharPrio(entry.getKey(), entry.getValue()));
        }

        return data;
    }

    private static Comparator<CharPrio> getMaxCharPrioComparator() {
        return new Comparator<CharPrio>() {
            @Override
            public int compare(CharPrio o1, CharPrio o2) {
                if (o1.getPrio() == o2.getPrio()) {
                    if(o1.getChar() == o2.getChar()) {
                        return 0;
                    } else {
                        return o1.getChar() > o2.getChar() ? 1 : -1;
                    }
                } else {
                    return o1.getPrio() < o2.getPrio() ? 1 : -1;
                }
            }
        };
    }

    private static Comparator<CharPrio> getMinCharPrioComparator() {
        return new Comparator<CharPrio>() {
            @Override
            public int compare(CharPrio o1, CharPrio o2) {
                if (o1.getPrio() == o2.getPrio()) {
                    if(o1.getChar() == o2.getChar()) {
                        return 0;
                    } else {
                        return o1.getChar() > o2.getChar() ? 1 : -1;
                    }
                } else {
                    return o1.getPrio() > o2.getPrio() ? 1 : -1;
                }
            }
        };
    }

    private static class CharPrio {
        private char c;
        private int prio;

        public CharPrio(char c, int prio) {
            this.c = c;
            this.prio = prio;
        }

        public char getChar() {
            return c;
        }

        public int getPrio() {
            return prio;
        }
    }
}
