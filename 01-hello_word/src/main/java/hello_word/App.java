package hello_word;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class App {

    public static void main(String[] args) {
        // Check number of command line parameters
        if (args.length != 2) {
            System.err.println("Exactly two command line parameters are required.");
            System.out.println("Use: java -jar [FILE] [FirstName] [LastName].");
            System.exit(1);
        }

        ByteBuffer first_name_buffer = StandardCharsets.UTF_8.encode(args[0]); 
        ByteBuffer last_name_buffer = StandardCharsets.UTF_8.encode(args[1]);
        
        String first_name = StandardCharsets.UTF_8.decode(first_name_buffer).toString();
        String last_name = StandardCharsets.UTF_8.decode(last_name_buffer).toString();

        System.out.println("First name: " + first_name);
        System.out.println("Last name: " + last_name);
    }
}
