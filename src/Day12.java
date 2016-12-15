import util.InputReader;

import java.util.HashMap;
import java.util.Map;

public class Day12 {

    private static final Map<Character, Integer> registers = new HashMap<>();

    static {
        registers.put('a', 0);
        registers.put('b', 0);
        registers.put('c', 0);
        registers.put('d', 0);
    }

    public static void main(String[] args) {
        String[] input = InputReader.getInput(12).split("\n");
        int result = solveProblem2(input);
        System.out.println("Result: "+result);
    }

    private static int solveProblem1(String[] input) {
        return solve(input);
    }

    private static int solveProblem2(String[] input) {
        registers.put('c', 1);
        return solve(input);
    }

    private static int solve(String[] input) {
        int currentLineIndex = 0;
        while(currentLineIndex <  input.length) {
            String currentLine = input[currentLineIndex];

            if(currentLine.startsWith("jnz")) {
                // jnz is related to parsing and not to registers
                currentLineIndex = jnz(currentLineIndex, currentLine);
            } else {
                executeRegisterCommand(currentLine);
                currentLineIndex++;
            }
        }

        return registers.get('a');
    }

    private static int jnz(int currentLineIndex, String currentLine) {
        String[] data = currentLine.split(" ");

        int value;

        if(isRegister(data[1])) {
            char reg = data[1].charAt(0);
            value = registers.get(reg);
        } else {
            value = Integer.parseInt(data[1]);
        }

        if(value != 0) {
            return currentLineIndex + Integer.parseInt(data[2]);
        } else {
            return currentLineIndex + 1;
        }
    }

    private static void executeRegisterCommand(String line) {
        String[] data = line.split(" ");
        String cmd = data[0];
        if("cpy".equals(cmd)) {
            char toReg = data[2].charAt(0);
            if(isRegister(data[1])) {
                cpy(data[1].charAt(0), toReg);
            } else {
                cpy(Integer.parseInt(data[1]), toReg);
            }
        } else if("inc".equals(cmd)) {
            inc(data[1].charAt(0));
        } else if("dec".equals(cmd)) {
            dec(data[1].charAt(0));
        }
    }

    private static boolean isRegister(String s) {
        return !Character.isDigit(s.charAt(0));
    }

    private static void cpy(char fromRegister, char toRegister) {
        int value = registers.get(fromRegister);
        cpy(value, toRegister);
    }

    private static void cpy(int value, char register) {
        registers.put(register, value);
    }

    private static void inc(char register) {
        int value = registers.get(register);
        registers.put(register, value+1);
    }

    private static void dec(char register) {
        int value = registers.get(register);
        registers.put(register, value-1);
    }


}
