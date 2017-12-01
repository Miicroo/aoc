import util.InputReader;

public class Day8 {

    private static char[][] display;

    public static void main(String[] args) {
        generateDisplay();

        String[] input = InputReader.getInput(8).split("\n");
        int result = solveProblem1(input);
        System.out.println("Result: " + result);

        solveProblem2(input);
    }

    private static void generateDisplay() {
        display = new char[6][50];

        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[0].length; j++) {
                display[i][j] = '.';
            }
        }
    }

    private static int solveProblem1(String[] input) {
        for (String in : input) {
            runCommand(in);
        }

        return countTurnedOn();
    }

    private static int countTurnedOn() {
        int result = 0;

        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[0].length; j++) {
                if (display[i][j] == '#') {
                    result++;
                }
            }
        }

        return result;
    }

    private static void runCommand(String cmd) {
        if (cmd.startsWith("rect ")) {
            String[] data = cmd.replaceAll("rect ", "").split("x");
            int cols = Integer.parseInt(data[0]);
            int rows = Integer.parseInt(data[1]);

            turnOn(rows, cols);
        } else if (cmd.startsWith("rotate column x=")) {
            String[] data = cmd.replaceAll("rotate column x=", "").split(" by ");
            int col = Integer.parseInt(data[0]);
            int howMuch = Integer.parseInt(data[1]);

            rotateColumn(col, howMuch);
        } else if (cmd.startsWith("rotate row y=")) {
            String[] data = cmd.replaceAll("rotate row y=", "").split(" by ");
            int row = Integer.parseInt(data[0]);
            int howMuch = Integer.parseInt(data[1]);

            rotateRow(row, howMuch);
        } else {
            throw new RuntimeException("No such cmd: " + cmd);
        }
    }

    private static void turnOn(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                display[i][j] = '#';
            }
        }
    }

    private static void rotateColumn(int col, int amount) {
        for (int i = 0; i < amount; i++) {
            char writeVal = display[0][col];
            char tmp = display[1][col];
            for (int j = 0; j < display.length; j++) {
                display[(j + 1) % display.length][col] = writeVal;
                writeVal = tmp;
                tmp = display[(j + 2) % display.length][col];
            }
        }
    }

    private static void rotateRow(int row, int amount) {
        for (int i = 0; i < amount; i++) {
            char writeVal = display[row][0];
            char tmp = display[row][1];
            for (int j = 0; j < display[row].length; j++) {
                display[row][(j + 1) % display[row].length] = writeVal;
                writeVal = tmp;
                tmp = display[row][(j + 2) % display[row].length];
            }
        }
    }

    private static void solveProblem2(String[] input) {
        debugDisplay();
    }

    private static void debugDisplay() {
        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[0].length; j++) {
                System.out.print(display[i][j]);
            }
            System.out.println();
        }
    }
}
