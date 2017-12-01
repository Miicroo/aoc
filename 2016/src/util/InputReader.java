package util;

import java.io.*;

public class InputReader {

    public static String getInput(int day) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./resources/inputDay"+day+".txt"));
            StringBuffer input = new StringBuffer();
            String line = null;
            while((line = reader.readLine()) != null) {
                input.append(line+"\n");
            }

            reader.close();

            return input.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
