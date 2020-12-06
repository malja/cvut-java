package solution;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;

public class Main {

    /**
     * Check that one (and only) command line argument of this program is
     * path to the file.
     */
    private static boolean checkCommandLineArguments(String[] args) {
        if (args.length != 1) {
            return false;
        }

        File input = new File(args[0]);
        return input.exists() && input.isFile();
    }

    /**
     * Convert text to numeric value.
     * @return Numerical value, 0 for non-numerical text.
     */
    private static int stringToNumber(String text) {
        int number;
        try {
            number = Integer.parseInt(text);
        } catch(NumberFormatException e) {
            // Since 0 ends the loop in main method, this suits here
            return 0;
        }

        return number;
    }

    /**
     * Ask user for the input. Then check it. If the input is not valid, ask again until it is.
     * @return Input as String.
     */
    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);

        // Prepare Regex pattern for 1 to 4 numerical characters
        Pattern numberPattern = Pattern.compile("^[0-9]{1,4}$");
        
        // Temporary variable for user input
        String input = "";

        boolean userInputValid = false;
        
        // Test until right input
        while (!userInputValid) {
            System.out.print("Enter patent number (0 for exit): ");
            input = scanner.nextLine();
            userInputValid = numberPattern.matcher(input).matches();
        }

        // Be careful, this closes System.in and in next call of this function, scanner.nextLine throws NoSuchElementException
        // scanner.close();
        // Docs: "When a Scanner is closed, it will close its input source if the source implements the Closeable interface."
        // https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
        return input;
    }

    public static void main(String[] args) {

        // Check command line arguments
        if (!checkCommandLineArguments(args)) {
            System.out.println("First and only command line parameter has to be a file path.");
            System.out.println("Usage: map.java [FILE_WITH_PATENTS]");
            System.exit(1);
        }
        
        // Map with all the values
        Map<Integer, String> map = new Map<Integer, String>();
        
        // Load file from command line arguments
        FileParser parser = new FileParser();
        parser.load(args[0]);

        // Read line line by line
        Pair<Integer, String> pair = null;
        while ( (pair = parser.getLine()) != null ) {
            map.put(pair.getLeft(), pair.getRight());
        }
        
        // Value for the first loop
        int patentId = 0;

        // Wait for 0 which ends the execution
        while ((patentId = stringToNumber(getUserInput())) != 0) {

            // Inform the user, that this ID is not present.
            if (!map.containsKey(patentId)) {
                System.out.println("There is no patent with this ID. Try again.");
                continue;
            }

            // Print patent text.
            System.out.println("Patent number " + patentId + ": " + map.get(patentId));
        }
    }
}