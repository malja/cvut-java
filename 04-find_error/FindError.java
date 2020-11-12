/*
 * This file contains fixed code.
*/

package finderror; 

/*
    Change: 
    Import for Scanner class
*/
import java.util.Scanner;

public class FindError { 

    /** 
     * @param args the command line arguments 
     */ 
    public static void main(String[] args) { 

        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}; 

        /* 
            Change: 
            Following code is unnecessary.
            In addition, it causes that Monday is rewritten with "Free Day!".
            'nextWeek' is a "weak" copy of 'week' and that means that every change made to 'nextWeek' is applied to 'week' as well.
        */
            // String[] nextWeek = week; 
            // nextWeek[0] = "Free day!"; 

        /*
            Change:
            Bounds for the loop are wrong. For the first iteration, 'i' equals to 6. But to continue, it has to be smaller or equal to 0.
        */
            // for (int i=6; i<=0; i--){ 
            //      System.out.println(week[i]); 
            // }
        
        for (int i = week.length - 1; i >= 0; i--){ 
            System.out.println(week[i]); 
        } 

        System.out.println("------------------------------------------------");

        Scanner scanner = new Scanner(System.in); 
        String pattern = scanner.nextLine(); // input from keyboard 

        /*
            Change:
            Just small change with bounds. I prefer to use length of array than "magic" numbers :)
        */
        for (int i = 0; i < week.length; i++) { 
            /*
                Change:
                Well, it makes sense that default behavior of comparison is to compare references instead of the content. Right? RIGHT?!
            */
            if (pattern.equals(week[i])){ 
                System.out.println("SUCCESS!"); 
                System.out.println(week[i]); 
                System.exit(0);  // exit the application 
            } 
        } 

        System.out.println("NOT FOUND"); 
    } 
} 