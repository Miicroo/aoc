import util.Position;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Day17 {

    private static final String INPUT = "edjrjqaa";
    private static final Position END_POSITION = new Position(3, 3);

    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        solveProblem2(INPUT, new Position(0, 0), result);
        String path = result.toString().replaceAll(INPUT, "");
        System.out.println("Result: "+path.length());
    }

    private static void solveProblem1(String input, Position pos, StringBuilder bestSolution) {
        if(END_POSITION.equals(pos)) {
            if(bestSolution.length() == 0 || input.length() < bestSolution.length()) {
                bestSolution.replace(0, bestSolution.length(), input);
            }
            return;
        }
        if(bestSolution.length() > 0 && input.length() >= bestSolution.length()) {
            return;
        }

        List<Character> directionsToGo = getDirections(input, pos);
        for(Character dir : directionsToGo) {
            Position newPos = move(pos, dir);
            solveProblem1(input+dir, newPos, bestSolution);
        }
    }

    private static void solveProblem2(String input, Position pos, StringBuilder bestSolution) {
        if(END_POSITION.equals(pos)) {
            if(bestSolution.length() == 0 || input.length() > bestSolution.length()) {
                bestSolution.replace(0, bestSolution.length(), input);
            }
            return;
        }

        List<Character> directionsToGo = getDirections(input, pos);
        for(Character dir : directionsToGo) {
            Position newPos = move(pos, dir);
            solveProblem2(input+dir, newPos, bestSolution);
        }
    }

    private static Position move(Position pos, Character d) {
        Position newPos = null;
        switch(d) {
            case 'U':
                newPos = new Position(pos.getY()-1, pos.getX());
                break;
            case 'D':
                newPos = new Position(pos.getY()+1, pos.getX());
                break;
            case 'L':
                newPos = new Position(pos.getY(), pos.getX()-1);
                break;
            case 'R':
                newPos = new Position(pos.getY(), pos.getX()+1);
                break;
        }
        return newPos;
    }

    private static List<Character> getDirections(String input, Position p) {
        List<Character> directions = new ArrayList<>();
        String md5 = md5(input, 8); // First 4 chars
        if(isValidChar(md5.charAt(0)) && p.getY() > 0) {
            directions.add('U');
        }
        if(isValidChar(md5.charAt(1)) && p.getY() < END_POSITION.getY()) {
            directions.add('D');
        }
        if(isValidChar(md5.charAt(2)) && p.getX() > 0) {
            directions.add('L');
        }
        if(isValidChar(md5.charAt(3)) && p.getX() < END_POSITION.getX()) {
            directions.add('R');
        }

        return directions;
    }

    private static boolean isValidChar(char c) {
        return c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f';
    }


    private static String md5(String input, int len) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(input.getBytes());
        byte byteData[] = m.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
