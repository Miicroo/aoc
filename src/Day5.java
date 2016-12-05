import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day5 {

    private static final String input = "uqwqemis";

    public static void main(String[] args) {
        String result = solveProblem2(input);
        System.out.println("Result: " + result);
    }

    private static String solveProblem1(String input) {
        StringBuilder sb = new StringBuilder();
        int iteration = 0;
        while (sb.length() < 8) {
            String md5 = md5(input + iteration);
            if (md5.startsWith("00000")) {
                sb.append(md5.charAt(5));
            }
            iteration++;
        }
        return sb.toString();
    }

    private static String solveProblem2(String input) {
        char[] str = new char[8];
        int found = 0;
        int iteration = 0;
        while (found < 8) {
            String md5 = md5(input + iteration);
            if (md5.startsWith("00000")) {
                int index = md5.charAt(5) - '0';
                if (index < str.length && str[index] == '\u0000') { // Has value been set?
                    found++;
                    str[index] = md5.charAt(6);
                }
            }
            iteration++;
        }
        return new String(str);
    }


    private static String md5(String input) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(input.getBytes());
        byte byteData[] = m.digest();

        StringBuilder sb = new StringBuilder();
        int DESIRED_LENGTH = 4;
        for (int i = 0; i < DESIRED_LENGTH; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
