package solution;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available methods: ");
        System.out.println("- 1 = BruteForce (default)");
        System.out.println("- 2 = Broad search algorithm");
        System.out.print("Method (1,2): ");
        String method = scanner.nextLine();

        // Make BruteForce method as default
        if (method.length() == 0) {
            method = "1";
        }

        // Test until right input
        while (!method.equals("1") && !method.equals("2")) {
            System.out.print("Method (1, 2): ");
            method = scanner.nextLine();
        }

        String trace = "";

        if (method.equals("2")) {
            AStar as = new AStar();
            trace = as.solve();
        } else {
            BruteForce bf = new BruteForce();
            trace = bf.solve();
        }

        // Print out the output
        System.out.println(trace);

    }
}
