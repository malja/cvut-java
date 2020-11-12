/*
    Find approximate value of PI using Leibnitz formula, for given N.  
    N is entered from the keyboard and must be treated against all possible variants of incorrect entry, at least: empty
    entry, no-number, negative number, decimal number, too large number. Attach source code. 
*/

package leibniz;

import java.util.Scanner;
import java.lang.Math;

public class Leibniz {

    /**
     * Convert text to integer. If conversion fails, returns 0. 
     * @note I know this behavior is not perfect and using exceptions would be better. On the other hand,
     * it is easier (and I would say more readable) this way.
     * @param text Input string with numerical value.
     * @return Integer
     */
    private static int convertStringToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Check if the input value from the user is valid integer smaller than 10^6.
     * @param input User input from terminal.
     * @return Boolean
     */
    private static Boolean isInputValid(String input) {
        if (input.length() == 0) return false;

        // Check if the first character of the line is number
        // Thie eliminates negative numbers (starts with minus sign), decimal number without leading zero (starts with dot), etc..
        Boolean isFirstCharNumber = Character.isDigit(input.charAt(0));

        if (!isFirstCharNumber) return false;

        int value = convertStringToInt(input);
        return value > 0 && value <= Math.pow(10, 6);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of iterations: ");
        
        // Get one line from command line
        String input = scanner.nextLine().trim();

        while(!isInputValid(input)) {
            System.out.println("Only integers in range (1;10^6> are allowed.");
            System.out.print("> ");
            input = scanner.nextLine().trim();
        }

        int iterations = convertStringToInt(input);
        double pi_approximation = 1.0;

        for (int i = 1; i <= iterations; i++) {
            if(i % 2 == 0) {
                pi_approximation = pi_approximation + (1.0/ (1.0 +2.0*i) );
            } else {
                // For some reason, Java thinks, that without MANUALY specifying that the fraction is double, it is int!
                // 1.0 is thus necessary to make it work
                pi_approximation = pi_approximation - (1.0/(1.0+2.0*i));
            }
        }

        System.out.print("pi = ");
        System.out.println(pi_approximation*4);
    }
}