import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Day14 {

    private static final String input = "cuanljph";
    private static final Map<String, String> md5Cache = new HashMap<>();

    public static void main(String[] args) {
        int result = solveProblem2(input);
        System.out.println("Result: "+result);
    }

    private static int solveProblem1(String input) {
        return solve(input, 0);
    }

    private static int solveProblem2(String input) {
        return solve(input, 2016);
    }

    private static int solve(String input, int extraHashRounds) {
        int index = 0;
        int okHashes = 0;
        while(okHashes < 64) {
            String nextStr = input+index;
            String md5 = md5(nextStr, extraHashRounds);

            Character triplet = getTripletChar(md5);
            if(triplet != null) {
                boolean isKey = false;
                String mustContain = new String(new char[]{triplet, triplet, triplet, triplet, triplet});
                for(int i = index+1; i<index+1001; i++) {
                    String newMd5 = md5(input+i, extraHashRounds);
                    if(newMd5.contains(mustContain)) {
                        isKey = true;
                        break;
                    }
                }

                if(isKey) {
                    okHashes++;
                }
            }

            index++;
        }

        return index-1;
    }

    private static Character getTripletChar(String s) {
        for(int i = 0; i<s.length()-3; i++) {
            if(s.charAt(i) == s.charAt(i+1) && s.charAt(i) == s.charAt(i+2)) {
                return s.charAt(i);
            }
        }
        return null;
    }

    private static String md5(String input, int extraRounds) {
        if(md5Cache.containsKey(input)) {
            return md5Cache.get(input);
        } else {
            String md5 = md5Impl(input);
            for(int i = 0; i<extraRounds; i++) {
                md5 = md5Cache.containsKey(md5) ? md5Cache.get(md5) : md5Impl(md5);
            }
            md5Cache.put(input, md5);
            return md5;
        }
    }

    private static String md5Impl(String input) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(input.getBytes());
        byte byteData[] = m.digest();

        StringBuilder sb = new StringBuilder();
        int DESIRED_LENGTH = byteData.length;
        for (int i = 0; i < DESIRED_LENGTH; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
