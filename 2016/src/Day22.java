import util.GridNode;
import util.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Day22 {

    public static void main(String[] args) {
        List<GridNode> gridNodes = parseGridNodes(InputReader.getInput(22).split("\n"));
        int result = solveProblem1(gridNodes);
        System.out.println("Result: "+result);
    }

    private static List<GridNode> parseGridNodes(String[] input) {
        List<GridNode> nodes = new ArrayList<>();
        int firstNodeRow = 2;
        for(int i = firstNodeRow; i<input.length; i++) {
            String[] data = input[i].split(" +");

            String name = data[0].trim();
            String size = data[1].trim();
            int used = Integer.parseInt(data[2].trim().replaceAll("T", ""));
            int available = Integer.parseInt(data[3].trim().replaceAll("T", ""));
            String usage = data[4].trim();

            nodes.add(new GridNode(name, size, used, available, usage));
        }
        return nodes;
    }

    private static int solveProblem1(List<GridNode> gridNodes) {
        int result = 0;
        for(GridNode current : gridNodes) {
            for(GridNode other : gridNodes) {
                if(!current.getName().equals(other.getName()) && current.getUsed() > 0) {
                    if(current.getUsed() <= other.getAvailable()) {
                        result++;
                    }
                }
            }
        }

        return result;
    }
}
