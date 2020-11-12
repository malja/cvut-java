/*
 * Given program „FindError“, also available in project folder. This program does not work as expected: 
 * - It doesn't print days in week at all 
 * - Explain, why week[0] doesn't print "Monday" 
 * - Never prints "SUCCESS!" 
 * Find and repair all errors, attach a screenshot of working program. 
*/

package finderror; 

public class FindError { 

    /** 
     * @param args the command line arguments 
     */ 
    public static void main(String[] args) { 

        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}; 
        String[] nextWeek = week; 
        nextWeek[0] = "Free day!"; 

        for (int i=6; i<=0; i--){ 
            System.out.println(week[i]); 
        } 

        System.out.println("------------------------------------------------"); 

        Scanner scanner = new Scanner(System.in); 
        String pattern = scanner.nextLine(); // input from keyboard 

        for (int i=0; i<7; i++) { 
            if (pattern == week[i]){ 
                System.out.println("SUCCESS!"); 
                System.out.println(week[i]); 
                System.exit(0);  // exit the application 
            } 
        } 

        System.out.println("NOT FOUND"); 
    } 
} 