import java.util.List;

public class D1 {

    public static int solve1(List<String> input) {
        return input.stream()
            .mapToInt(D1::getCalibrationValue)
            .sum();
    }

    private static int getCalibrationValue(String input) {
        var numbers = input.replaceAll("[^0-9]+", "");

        var calibrationString = numbers.length() > 1 ?
            numbers.substring(0, 1) + numbers.substring(numbers.length() - 1, numbers.length()) :
            numbers + numbers;
        
        return Integer.parseInt(calibrationString);
    }
}
