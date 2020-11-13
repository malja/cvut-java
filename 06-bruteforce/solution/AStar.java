package solution;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;


public class AStar {

    private static String[] towns = {"Prague", "New York", "London", "Moscow", "Peking"};
    private static int[][] tickets = {
        {0, 4279, 1799, 2602, 10236},   // Prague
        {4279, 0, 2017, 9873, 14300},   // New York
        {1799, 3922, 0, 0, 12110},      // London
        {2602, 9873, 2435, 0, 7503},    // Moscow
        {10236, 14300, 12110, 7503, 0}  // Peking
    };

    public static int indexFromName(String name) {
        return Arrays.asList(towns).indexOf(name);
    }
    
    public static int price(String from, String to) {
        int start_node = indexFromName(from);
        int end_node = indexFromName(to);

        if (start_node < 0 || end_node < 0) {
            return -1;
        }

        return tickets[start_node][end_node];
    }

    public String astar(String start, String destination) {
        // TODO: Validate whether theare is a way to solve it!

        ArrayList<Node> open = new ArrayList<Node>();
        ArrayList<Node> closed = new ArrayList<Node>();

        open.add( new Node( indexFromName(start) ) );
        
        // While there are some nodes on the open list
        while (open.size() != 0) {

            // Get node with minimum price on the open list
            Node current = open.get(0);
            for(Iterator<Node> iter = open.iterator(); iter.hasNext(); ) {
                Node item = iter.next();
                if (item.getPrice() < current.getPrice()) {
                    current = item;
                }
            }

            // Now 'current' contains node with lowest price in the open list

            // Remove the node with minimum price from the open list
            open.remove(current);

            // Get all available ticket from current node
            int[] available_tickets = tickets[ current.getId() ];

            ArrayList<Node> successors = new ArrayList<Node>();
            // Successors are nodes with non-zero price tickets from current node
            for (int i = 0; i < available_tickets.length; i++) {
                if (available_tickets[i] != 0) {
                    Node item = new Node(i);
                    item.setPrevious(current.getId(), available_tickets[i]);
                    successors.add(item);
                }
            }

            // For each successor
            for(Iterator<Node> iter = successors.iterator(); iter.hasNext(); ) {
                Node item = iter.next();
                if ( towns[item.getId()].equals(destination) ) {
                    // TODO trace
                    return (start + " -> " + destination + " via " + towns[item.getPreviousId()]);
                }

                Boolean skip = false;
                // Is the successor's target on the open list?
                for( Iterator<Node> it = open.iterator(); it.hasNext(); ) {
                    Node i = it.next();
                    if (i.getId() == item.getId() && i.getPrice() < item.getPrice()) {
                        skip = true;
                        break;
                    }
                }

                // If there is a node on the open list, with lower price, ignore this sucessor
                if (skip) {
                    continue;
                }

                skip = false;
                // Is the sucessor's target on the closed list
                for( Iterator<Node> it = closed.iterator(); it.hasNext(); ) {
                    Node i = it.next();
                    if (i.getId() == item.getId() && i.getPrice() < item.getPrice() ) {
                        skip = true;
                        break;
                    }
                }

                // If there is a node on the closed list with a lower price, ignore this sucessor
                if (skip) {
                    continue;
                }
                
                open.add(item);
            }

            closed.add(current);
        }

        return "No way found";

    }

    public String solve() {
        // TODO: Some testing of 'towns' and 'tickets'

        Scanner scanner = new Scanner(System.in);
        System.out.println("Available places: " + String.join(", ", towns) );

        // Ask user about start and end destination
        System.out.print("Start: ");
        String start = scanner.nextLine().trim();

        while(!Arrays.stream(towns).anyMatch(start::equals)) {
            System.out.print("Start: ");
            start = scanner.nextLine().trim();
        }

        System.out.print("End: ");
        String end = scanner.nextLine().trim();

        while(!Arrays.stream(towns).anyMatch(end::equals) || end.equals(start)) {
            System.out.print("End: ");
            end = scanner.nextLine().trim();
        }

        scanner.close();
        return astar(start, end);

    }


}