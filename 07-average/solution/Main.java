package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File file = new File("./numbers.txt");

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File 'numbers.txt' does not exist.");
            System.exit(1);
        }

        BigInteger sum = BigInteger.ZERO;
        int count = 0;

        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                sum = sum.add(new BigInteger(line));
                count++;
            } catch (NumberFormatException e) {
                System.out.println("Number on line " + count + " is invalid.");
                System.exit(2);
            }
        }

        System.out.println("Loaded " + count + " numbers.");
        System.out.println("The sum is: " + sum + " and the average is: " + sum.divide(BigInteger.valueOf(count)));

    }
}