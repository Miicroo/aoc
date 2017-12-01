

public class Day16 {

    private static final String input = "10001001100000001";

    public static void main(String[] args) {
        String result = solveProblem2(input);
        System.out.println("Result: " + result);
    }

    private static String solveProblem1(String input) {
        return solve(input, 272);
    }

    private static String solveProblem2(String input) {
        return solve(input, 35651584);
    }

    private static String solve(String input, int diskSize) {
        String diskContents = input;
        while(diskContents.length() < diskSize) {
            diskContents = generateData(diskContents);
        }

        diskContents = diskContents.substring(0, diskSize);
        return generateChecksum(diskContents);
    }

    private static String generateData(String a) {
        String b = a;
        b = reverse(b);
        b = switchZerosAndOnes(b);

        return a + "0" + b;
    }

    private static String reverse(String in) {
        final int LEN = in.length();
        char[] newStr = new char[LEN];
        for (int i = 0; i < LEN; i++) {
            newStr[LEN - 1 - i] = in.charAt(i);
        }
        return new String(newStr);
    }

    private static String switchZerosAndOnes(String b) {
        String newCopy = b.replaceAll("0", "2");
        newCopy = newCopy.replaceAll("1", "0");
        return newCopy.replaceAll("2", "1");
    }

    private static String generateChecksum(String data) {
        StringBuilder checksum = new StringBuilder();
        char[] current = data.toCharArray();

        while (true) {
            for (int i = 0; i < current.length; i += 2) {
                if(current[i] == current[i+1]) {
                    checksum.append(1);
                } else {
                    checksum.append(0);
                }
            }

            if(checksum.length() % 2 == 1) {
                break;
            }

            current = checksum.toString().toCharArray();
            checksum = new StringBuilder();
        }

        return checksum.toString();
    }
}
