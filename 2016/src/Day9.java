import util.InputReader;

import java.util.*;

public class Day9 {

    public static void main(String[] args) {
        String input = InputReader.getInput(9);
        int result = solveProblem2(input);
        System.out.println("Result: "+result);
    }

    private static int solveProblem1(String input) {
        int result = 0;
        String decompressed = decompress(input);
        result = decompressed.replaceAll("\\s", "").length();
        return result;
    }

    private static String decompress(String in) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i<in.length(); i++) {
            if(in.charAt(i) == '(') {
                // Found marker
                int markerEnd = in.indexOf(')', i)+1;
                String markerStr = in.substring(i+1, markerEnd-1);
                String[] data = markerStr.split("x");

                int markerLen = Integer.parseInt(data[0]);
                int markerRepeatCount = Integer.parseInt(data[1]);

                assert(in.length() > i + markerLen);

                String markerText = in.substring(markerEnd, markerEnd + markerLen);
                for(int j = 0; j<markerRepeatCount; j++) {
                    result.append(markerText);
                }

                i = markerEnd+markerLen-1; // Start looking after marker
            } else {
                result.append(in.charAt(i));
            }
        }

        return result.toString();
    }

    private static int solveProblem2(String input) {
        int result = 0;
        String decompressed = decompressV2(input);
        result = decompressed.replaceAll("\\s", "").length();
        return result;
    }


    private static String decompressV2(String in) {
        StringBuilder result = new StringBuilder();
        Map<String, String> expressions = preCacheExpressions(in);

        for(int i = 0; i<in.length(); i++) {
            if(in.charAt(i) == '(') {
                // Found marker
                int markerEnd = in.indexOf(')', i)+1;
                String markerStr = in.substring(i+1, markerEnd-1);
                String[] data = markerStr.split("x");

                int markerLen = Integer.parseInt(data[0]);

                assert(in.length() > i + markerLen);

                String text = in.substring(i, markerEnd+markerLen);
                result.append(expressions.get(text));

                i = markerEnd+markerLen-1; // Start looking after marker
            } else {
                result.append(in.charAt(i));
            }
        }
        System.out.println(result.toString());
        return result.toString();
    }

    private static Map<String, String> preCacheExpressions(String in) {
        Map<String, String> markerCache = new HashMap<>();

        int c = 0;
        for(int i = in.length()-1; i>= 0; i--) {
            if(in.charAt(i) == ')') {
                int endMarker = i;
                int startMarker = 0;
                for(int j = i; j>=0; j--) {
                    if(in.charAt(j) == '(') {
                        startMarker = j;
                        break;
                    }
                }

                String exprData = in.substring(startMarker);
                String[] expr = evaluateExpression(exprData, markerCache);
                markerCache.put(expr[0], expr[1]);

                c++;
                System.out.println("Solved "+c+" expressions");
            }
        }

        return markerCache;
    }


    private static String[] evaluateExpression(String expr, Map<String, String> markerCache) {
        StringBuilder result = new StringBuilder();

        int markerEnd = expr.indexOf(')')+1;
        String markerStr = expr.substring(1, markerEnd-1);
        String[] data = markerStr.split("x");

        int markerLen = Integer.parseInt(data[0]);
        int markerRepeatCount = Integer.parseInt(data[1]);

        assert(expr.length() > markerLen);

        String markerText = expr.substring(markerEnd, markerEnd + markerLen);
        String originalMarkerText = markerText;

        // Check if text contains inner expressions
        if(markerText.contains("(")) {
            List<Map.Entry<String, String>> orderedCache = orderCache(markerCache.entrySet()); // Make sure that the longest caches appear first
            for(Map.Entry<String, String> cache : orderedCache) {
                if(markerText.contains(cache.getKey())) {
                    String oldExpr = cache.getKey().replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
                    markerText = markerText.replaceAll(oldExpr, cache.getValue());
                }
            }
        }

        for(int j = 0; j<markerRepeatCount; j++) {
            result.append(markerText);
        }

        return new String[]{"("+markerStr+")"+originalMarkerText, result.toString()};
    }

    private static List<Map.Entry<String, String>> orderCache(Set<Map.Entry<String, String>> entries) {
        List<Map.Entry<String, String>> sorted = new ArrayList<>();
        sorted.addAll(entries);

        Collections.sort(sorted, (o1, o2) -> count(o2.getKey(), '(')-count(o1.getKey(), '('));

        return sorted;
    }

    private static int count(String s, char match) {
        int count = 0;
        for(char c : s.toCharArray()) {
            if(c == match) {
                count++;
            }
        }
        return count;
    }
}
