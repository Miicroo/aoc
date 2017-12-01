import util.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {

    public static void main(String[] args) {
        String[] input = InputReader.getInput(7).split("\n");
        int result = solveProblem2(input);
        System.out.println("Result: "+result);
    }

    private static int solveProblem1(String[] input) {
        int result = 0;
        List<IPv7> ipList = parse(input);

        for(IPv7 ip : ipList) {
            if(hasTLS(ip)) {
                result++;
            }
        }
        return result;
    }


    private static List<IPv7> parse(String[] input) {
        List<IPv7> result = new ArrayList<>();
        for(String in : input) {
            List<String> hyperNets = new ArrayList<>();
            List<String> ips = new ArrayList<>();
            String currentStr = "";

            for(char c : in.toCharArray()) {
                if(c == '[') {
                    if(!currentStr.isEmpty()) {
                        ips.add(currentStr);
                        currentStr = "";
                    }
                } else if(c == ']') {
                    if(!currentStr.isEmpty()) {
                        hyperNets.add(currentStr);
                        currentStr = "";
                    }
                } else {
                    currentStr += c;
                }
            }

            if(!currentStr.isEmpty()) {
                ips.add(currentStr);
            }

            result.add(new IPv7(ips.toArray(new String[0]), hyperNets.toArray(new String[0])));
        }

        return result;
    }

    private static boolean hasTLS(IPv7 ip) {
        return hasABBA(ip.getSuperNets()) && !hasABBA(ip.getHyperNets());
    }

    private static boolean hasABBA(String[] strs) {
        for(String str : strs) {
            if(isABBA(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isABBA(String str) {
        int ABBA_LENGTH = 4;
        if(str.length() < ABBA_LENGTH) {
            return false;
        }

        for(int i = 0; i<str.length()-ABBA_LENGTH+1; i++) {
            if(isSubstrABBA(str.substring(i, i+ABBA_LENGTH))) {
                return true;
            }
        }

        return false;
    }

    private static boolean isSubstrABBA(String s) {
        return s.charAt(0) == s.charAt(3) && s.charAt(1) == s.charAt(2) && s.charAt(0) != s.charAt(1);
    }

    private static int solveProblem2(String[] input) {
        int result = 0;
        List<IPv7> ipList = parse(input);

        for(IPv7 ip : ipList) {
            if(hasSSL(ip)) {
                result++;
            }
        }
        return result;
    }

    private static boolean hasSSL(IPv7 ip) {
        String[] abas = getABAs(ip.getSuperNets());
        String[] babs = getBABs(abas);

        for (String hyperNet : ip.getHyperNets()) {
            for(String bab : babs) {
                if(hyperNet.contains(bab)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] getABAs(String[] strs) {
        List<String> abas = new ArrayList<>();
        int ABA_LEN = 3;
        for(String str : strs) {
            for(int i = 0; i<str.length()-ABA_LEN+1; i++) {
                String abaCandidate = str.substring(i, i + ABA_LEN);
                if(isABA(abaCandidate)) {
                    abas.add(abaCandidate);
                }
            }
        }

        return abas.toArray(new String[0]);
    }

    private static  boolean isABA(String s) {
        if(s.length() != 3) {
            return false;
        }

        return s.charAt(0) == s.charAt(2) && s.charAt(0) != s.charAt(1);
    }

    private static String[] getBABs(String[] abas) {
        List<String> babs = new ArrayList<>();
        for(String aba : abas) {
            babs.add(convertABAToBAB(aba));
        }

        return babs.toArray(new String[0]);
    }

    private static String convertABAToBAB(String aba) {
        return new String(new char[]{aba.charAt(1), aba.charAt(0), aba.charAt(1)});
    }

    private static class IPv7 {
        private String[] superNets, hyperNets;

        public IPv7(String[] superNets, String[] hyperNets) {
            this.superNets = superNets;
            this.hyperNets = hyperNets;
        }

        public String[] getSuperNets() {
            return superNets;
        }

        public String[] getHyperNets() {
            return hyperNets;
        }

        @Override
        public String toString() {
            return "IPv7{" +
                    "superNets=" + Arrays.toString(superNets) +
                    ", hyperNets=" + Arrays.toString(hyperNets) +
                    '}';
        }
    }
}
