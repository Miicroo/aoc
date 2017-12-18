import util.Bot;
import util.InputReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {

    private static final Map<Integer, Bot> botLookup = new HashMap<>();
    private static final Map<Integer, List<Integer>> outputBins = new HashMap<>();

    public static void main(String[] args) {
        String[] input = InputReader.getInput(10).split("\\n");
        int result = solveProblem1(input);
        System.out.println("Result: "+result);
    }

    private static int solveProblem1(String[] input) {
        int result = 0;

        List<Integer> readLines = new ArrayList<>();
        int lineIndex = 0;
        while(readLines.size() < input.length) {
            if(!readLines.contains(lineIndex)) {
                String line = input[lineIndex];

                boolean processed = processLine(line);
                if (processed) {
                    readLines.add(lineIndex);
                }
            }
            lineIndex = (lineIndex + 1) % input.length;
        }

        return result;
    }

    private static boolean processLine(String line) {
        if(line.startsWith("value ")) {
            String[] valueBot = line.replaceAll("value ", "").split(" goes to bot ");
            int chip = Integer.parseInt(valueBot[0]);
            int botNum = Integer.parseInt(valueBot[1]);

            giveChipToBot(chip, botNum);

            return true;
        } else if(line.startsWith("bot ")) {
            String reducedLine = line.substring(4).replaceAll("gives low to ", "").replaceAll("and high to ", "");
            String[] data = reducedLine.split(" ");

            int botNum = Integer.parseInt(data[0]);
            if(botLookup.containsKey(botNum) && botLookup.get(botNum).hasHighAndLow()) {
                int lowContainer = Integer.parseInt(data[2]);
                int highContainer = Integer.parseInt(data[4]);

                Bot bot = botLookup.get(botNum);

                giveChipToBot(bot.getHighChip(), highContainer);

                if("bot".equals(data[1])) {
                    giveChipToBot(bot.getLowChip(), lowContainer);
                } else {
                    giveChipToOutputBin(bot.getLowChip(), lowContainer);
                }

                if("bot".equals(data[3])) {
                    giveChipToBot(bot.getHighChip(), highContainer);
                } else {
                    giveChipToOutputBin(bot.getHighChip(), highContainer);
                }

                bot.setHighChip(-1);
                bot.setLowChip(-1);
                botLookup.put(botNum, bot);

                return true;
            }

            return false;
        } else {
            return false;
        }
    }

    private static void giveChipToBot(int chip, int botNum) {
        Bot bot = botLookup.containsKey(botNum) ? botLookup.get(botNum) : new Bot(botNum);
        bot.receiveChip(chip);
        botLookup.put(botNum, bot);
    }

    private static void giveChipToOutputBin(int chip, int container) {
        List<Integer> chips = new ArrayList<>();
        if(outputBins.containsKey(container)) {
            chips = outputBins.get(container);
        }
        chips.add(chip);
        outputBins.put(container, chips);
    }
}
