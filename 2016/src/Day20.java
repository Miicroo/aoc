import util.InputReader;

import java.util.*;

public class Day20 {

    public static void main(String[] args) {
        long result = solveProblem2(InputReader.getInput(20).split("\n"));
        System.out.println("Result: "+result);
    }

    private static long solveProblem1(String[] input) {
        List<Range> ranges = new ArrayList<>();

        for(String block : input) {
            String[] data = block.split("-");

            Range current = new Range(Long.parseLong(data[0]), Long.parseLong(data[1]));
            ranges.add(current);
        }

        Collections.sort(ranges);

        Range result = ranges.get(0);
        int nextRange = 1;
        while(result.isInside(ranges.get(nextRange))) {
            result.extend(ranges.get(nextRange));
            nextRange++;
        }

        return result.getEnd()+1;
    }

    private static long solveProblem2(String[] input) {
        long count = 0;
        List<Range> ranges = new ArrayList<>();

        for(String block : input) {
            String[] data = block.split("-");

            Range current = new Range(Long.parseLong(data[0]), Long.parseLong(data[1]));
            ranges.add(current);
        }

        Collections.sort(ranges);

        Range result = ranges.get(0);
        for(int i = 0; i<ranges.size(); i++) {
            Range next = ranges.get(i);
            if(result.isInside(next)) {
                result.extend(next);
            } else {
                long diff = next.getStart()-result.getEnd()-1;
                assert diff >= 0;

                count += diff;
                result = next;
            }
        }
        long max = 4294967295L;
        count += max-result.getEnd();

        return count;
    }

    private static class Range implements Comparable<Range> {
        private long start, end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        public boolean isInside(Range other) {
            if((start <= other.getStart() && end >= other.getStart()) || (other.getStart() <= start && other.getEnd() >= start)) {
                return true;
            } else if((start <= other.getEnd() && end >= other.getEnd()) || (other.getStart() <= end && other.getEnd() >= end)) {
                return true;
            } else if((other.getStart() == end+1) || (start == other.getEnd()+1)) {
                return true;
            }

            return false;
        }

        public void extend(Range other) {
            start = Math.min(start, other.start);
            end = Math.max(end, other.getEnd());
        }

        @Override
        public int compareTo(Range o) {
            if(start == o.getStart()) {
                return 0;
            } else {
                return start < o.getStart() ? -1 : 1;
            }
        }
    }
}
