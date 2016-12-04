import util.InputReader;
import util.Room;

import java.util.*;

public class Day4 {

    public static void main(String[] args) {
        String input = InputReader.getInput(4);
        List<Room> rooms = parseInput(input);

        int result = solveProblem2(rooms);
        System.out.println("Result: "+result);
    }

    private static List<Room> parseInput(String input) {
        List<Room> rooms = new ArrayList<>();

        String[] lines = input.split("\n");
        for(String line : lines) {
            String[] parts = line.split("-");
            String[] endPart = parts[parts.length-1].split("\\[");
            String originalName = line.substring(0, line.lastIndexOf("-"));
            String name = concat(parts, 0, parts.length-2);
            int id = Integer.parseInt(endPart[0]);
            String checksum = endPart[1].replaceAll("\\]", "");

            rooms.add(new Room(originalName, name, checksum, id));
        }

        return rooms;
    }

    private static String concat(String[] strs, int start, int end) {
        StringBuilder builder = new StringBuilder();
        for(int i = start; i<=end; i++) {
            builder.append(strs[i]);
        }
        return builder.toString();
    }

    private static int solveProblem2(List<Room> rooms) {
        for(Room room : rooms) {
            if(checksumMatches(room)) {
                String realName = decrypt(room.getOriginalName(), room.getId());
                if("northpole object storage".equals(realName)) {
                    return room.getId();
                }
            }
        }
        return -1;
    }

    private static String decrypt(String name, int id) {
        StringBuilder builder = new StringBuilder();
        int diff = id % 26;
        for(char c : name.toCharArray()) {
            if(c == '-') {
                builder.append(' ');
            } else {
                int newCharOffset = ((c - 'a') + diff) % 26;
                char newChar = (char)('a' + newCharOffset);
                builder.append(newChar);
            }
        }
        return builder.toString();
    }

    private static int solveProblem1(List<Room> rooms) {
        int sumOfIds = 0;
        for(Room room : rooms) {
            if(checksumMatches(room)) {
                sumOfIds += room.getId();
            }
        }
        return sumOfIds;
    }

    private static boolean checksumMatches(Room room) {
        String calculatedChecksum = getChecksum(room.getName());
        return room.getChecksum().equals(calculatedChecksum);
    }

    private static String getChecksum(String str) {
        Map<Character, Integer> data = new HashMap<>();

        for(char c : str.toCharArray()) {
            if(data.containsKey(c)) {
                data.put(c, data.get(c)+1);
            } else {
                data.put(c, 1);
            }
        }

        List<ChecksumData> checksumData = convetMapToData(data);
        Collections.sort(checksumData, getChecksumDataComparator());

        StringBuilder checksum = new StringBuilder();
        int CHECKSUM_LENGTH = 5;
        for(int i = 0; i<CHECKSUM_LENGTH; i++) {
            checksum.append(checksumData.get(i).getChar());
        }

        return checksum.toString();
    }

    private static List<ChecksumData> convetMapToData(Map<Character, Integer> mapping) {
        List<ChecksumData> data = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : mapping.entrySet()) {
            data.add(new ChecksumData(entry.getKey(), entry.getValue()));
        }

        return data;
    }

    private static Comparator<ChecksumData> getChecksumDataComparator() {

        return new Comparator<ChecksumData>() {
            @Override
            public int compare(ChecksumData o1, ChecksumData o2) {
                if (o1.getCount() == o2.getCount()) {
                    if(o1.getChar() == o2.getChar()) {
                        return 0;
                    } else {
                        return o1.getChar() > o2.getChar() ? 1 : -1;
                    }
                } else {
                    return o1.getCount() < o2.getCount() ? 1 : -1;
                }
            }
        };
    }

    private static class ChecksumData {
        private char c;
        private int count;

        public ChecksumData(char c, int count) {
            this.c = c;
            this.count = count;
        }

        public char getChar() {
            return c;
        }

        public int getCount() {
            return count;
        }
    }
}
