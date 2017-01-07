import util.Direction;
import util.Position;
import util.Vector;

import java.util.ArrayList;
import java.util.List;

import static util.Direction.NORTH;

public class Day1 {

    private static final String input = "R4, R4, L1, R3, L5, R2, R5, R1, L4, R3, L5, R2, L3, L4, L3, R1, R5, R1, L3, L1, R3, L1, R2, R2, L2, R5, L3, L4, R4, R4, R2, L4, L1, R5, L1, L4, R4, L1, R1, L2, R5, L2, L3, R2, R1, L194, R2, L4, R49, R1, R3, L5, L4, L1, R4, R2, R1, L5, R3, L5, L4, R4, R4, L2, L3, R78, L5, R4, R191, R4, R3, R1, L2, R1, R3, L1, R3, R4, R2, L2, R1, R4, L5, R2, L2, L4, L2, R1, R2, L3, R5, R2, L3, L3, R3, L1, L1, R5, L4, L4, L2, R5, R1, R4, L3, L5, L4, R5, L4, R5, R4, L3, L2, L5, R4, R3, L3, R1, L5, R5, R1, L3, R2, L5, R5, L3, R1, R4, L5, R4, R2, R3, L4, L5, R3, R4, L5, L5, R4, L4, L4, R1, R5, R3, L1, L4, L3, L4, R1, L5, L1, R2, R2, R4, R4, L5, R4, R1, L1, L1, L3, L5, L2, R4, L3, L5, L4, L1, R3";

    public static void main(String[] args) {
        List<Vector> parsedInput = parseInput(input);
        int result = solveProblem2(parsedInput);
        System.out.println("Result: " + result);
    }

    private static List<Vector> parseInput(String inputStr) {
        List<Vector> result = new ArrayList<>();
        String[] vectorStrs = inputStr.split(", ");
        for(String vector : vectorStrs) {
            String dir = vector.substring(0, 1);
            int len = Integer.parseInt(vector.substring(1));
            result.add(new Vector(dir, len));
        }

        return result;
    }

    public static int solveProblem1(List<Vector> vectors) {
        int stepsNorth = 0;
        int stepsEast = 0;
        Direction currentDirection = NORTH;

        for(Vector vector : vectors) {
            currentDirection = getNextDirection(currentDirection, vector.getDirection());

            switch (currentDirection) {
                case NORTH:
                    stepsNorth += vector.getLength();
                    break;
                case SOUTH:
                    stepsNorth -= vector.getLength();
                    break;
                case EAST:
                    stepsEast += vector.getLength();
                    break;
                case WEST:
                    stepsEast -= vector.getLength();
                    break;
            }
            System.out.println("You are now at ("+stepsEast+","+stepsNorth+")");
        }
        return Math.abs(stepsEast) + Math.abs(stepsNorth);
    }

    private static Direction getNextDirection(Direction current, String nextDir) {
        switch(current) {
            case NORTH:
                switch(nextDir) {
                    case "L":
                        return Direction.WEST;
                    case "R":
                        return Direction.EAST;
                }
                break;
            case SOUTH:
                switch(nextDir) {
                    case "L":
                        return Direction.EAST;
                    case "R":
                        return Direction.WEST;
                }
                break;
            case EAST:
                switch(nextDir) {
                    case "L":
                        return NORTH;
                    case "R":
                        return Direction.SOUTH;
                }
                break;
            case WEST:
                switch(nextDir) {
                    case "L":
                        return Direction.SOUTH;
                    case "R":
                        return NORTH;
                }
                break;
        }

        return NORTH;
    }

    public static int solveProblem2(List<Vector> vectors) {
        Direction currentDirection = NORTH;
        List<Position> visitedPositions = new ArrayList<>();

        Position hqPosition = null;
        Position currentPosition = new Position(0, 0);
        visitedPositions.add(currentPosition);

        for(Vector vector : vectors) {
            currentDirection = getNextDirection(currentDirection, vector.getDirection());

            for(int i = 0; i<vector.getLength(); i++) {
                currentPosition = move(currentPosition, currentDirection);

                if(!visitedPositions.contains(currentPosition)) {
                    visitedPositions.add(currentPosition);
                } else {
                    hqPosition = currentPosition;
                    break;
                }

                //System.out.println("You are at ("+ currentPosition.getY()+","+currentPosition.getX() +")");
            }

            if(hqPosition != null) {
                break;
            }
        }
        return Math.abs((int)hqPosition.getY()) + Math.abs((int)hqPosition.getX());
    }

    private static Position move(Position current, Direction direction) {
        switch(direction) {
            case NORTH:
                return new Position(current.getY() + 1, current.getX());
            case SOUTH:
                return new Position(current.getY() - 1, current.getX());
            case EAST:
                return new Position(current.getY(), current.getX() + 1);
            case WEST:
                return new Position(current.getY(), current.getX() - 1);
        }

        return current;
    }
}
