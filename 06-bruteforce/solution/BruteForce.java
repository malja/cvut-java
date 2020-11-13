package solution;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BruteForce {
    private static String[] towns = {"Prague", "New York", "London", "Moscow", "Peking"};
    private static int[][] tickets = {
        {0, 4279, 1799, 2602, 10236},   // Prague
        {4279, 0, 2017, 9873, 14300},   // New York
        {1799, 3922, 0, 0, 12110},      // London
        {2602, 9873, 2435, 0, 7503},    // Moscow
        {10236, 14300, 12110, 7503, 0}  // Peking
    };

    /**
     * Check if 'towns' and 'tickets' variables exist in this class.
     * @return Boolean
     */
    private Boolean checkVariablesExists() {
        // Don't know what was the purpose of checking that there are the variables, since I wrote the code and know
        // they are declared. But it is in the assignment :/
        try {
            BruteForce.class.getDeclaredField("towns");
            BruteForce.class.getDeclaredField("tickets");
        } catch (NoSuchFieldException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if length of 'towns' is 5 and 'tickets' is 5x5 2D array. 
     * @return Boolean
     */
    private Boolean checkVariablesSize() {
        // I dont want to iterate over the array twice. This should be enought for now.
        return (
            towns.length == 5 && 
            tickets.length == 5 && 
            tickets[0].length == 5 &&
            tickets[1].length == 5 &&
            tickets[2].length == 5 &&
            tickets[3].length == 5 &&
            tickets[4].length == 5
        );
    }

    /**
     * Check if the content of 'towns' and 'tickets' is OK. Towns may not be empty. Ticket prices
     * must have 0s on main diagonal and must be non-negative numbers.
     * @return Boolean
     */
    private Boolean checkVariablesContent() {
        // All towns have to be filled in
        for(String town : towns) {
            if (town.length() == 0) {
                return false;
            }
        }

        // Non-negative values. Zeroes on the main diagonal
        for(int x = 0; x < tickets.length; x++) {
            for (int y = 0; y < tickets[x].length; y++) {
                // Zeroes on main diagonal
                if (x==y && tickets[x][y] != 0) return false;
                // Price has to be positive
                if (tickets[x][y] < 0) return false;
            }
        }

        return true;
    }

    /**
     * Run all checks and print error-messages if needed.
     * @see checkVariablesExists
     * @see checkVariableSize
     * @see checkVariableContent
     */
    private Boolean check() {
        if (!checkVariablesExists()) {
            System.out.println("Variables 'towns' and 'tickets' have to be declared");
            return false;
        }

        if (!checkVariablesSize()) {
            System.out.println("Variable 'towns' has to have 5 items. Variable 'tickets' has to be 5x5  2D array.");
            return false;
        }

        if (!checkVariablesContent()) {
            System.out.println(
                "Variable 'towns' is required to be filled with non-empty string. Variable 'tickets'" +
                "may contain only non-negative values. Values on main diagonal in 'tickets' has to be zeroes."
            );
            return false;
        }

        return true;
    }

    /**
     * Find cheapest combination of tickets to get from specified start to the end destination.
     * Then, print the trace and corresponding ticket prices.
     * @param iteneraries All possible combinations of flights. Even that does not reach the destination.
     * @param startId   ID of the town (index in 'towns') from which are we starting.
     * @param endId ID of the town (index in 'towns') to which are we flying.
     * @return String Trace of cheapest flight. Or "No flights found".
     */
    public String trace(ArrayList<Itinerary> iteneraries, int startId, int endId) {
        // Remove all iteneraries, whcih don't end in specified destination
        iteneraries.removeIf( i -> i.getLastTownId() != endId);

        // We did not reach it :O
        if (iteneraries.size() == 0) {
            return "No flights found";
        }

        // Find itinerary with the lowes price
        Itinerary minPriceItinerary = iteneraries.get(0);
        int minPrice = minPriceItinerary.getPrice();

        for (Itinerary itinerary: iteneraries) {
            if (itinerary.getPrice() < minPrice) {
                minPriceItinerary = itinerary;
                minPrice = itinerary.getPrice();
            }
        }

        // Trace cheapest flights
        String output = "Tracing flights from " + towns[startId] + " to " + towns[endId] + "\n";
        output += minPriceItinerary.trace(towns);
        return output;
    }

    /**
     * Do bruteforce search for all possible combination of flights. They may not reach the destination.
     * Or be the cheapest to arrive in the destination. But there are all of them :)
     * @param start Name of the starting destination.
     * @param end Name of the target destination.
     * @return Trace of the cheapest flights or "No flights found".
     */
    public String bruteForce(String start, String end) {
        int startIndex = Arrays.asList(towns).indexOf(start);
        int endIndex = Arrays.asList(towns).indexOf(end);

        // Active iteneraries are those, who may travel to at least one more destination.
        ArrayList<Itinerary> activeItineraries = new ArrayList<Itinerary>();
        // List of "used" iteneraries. There are those, which finished in end destination, or which 
        // got "stuck" on the way. Basically this array contains all possible combinations of flights.
        ArrayList<Itinerary> inactiveItineraries = new ArrayList<Itinerary>();

        // All available iteneraries from start
        for(int townId = 0; townId < tickets[startIndex].length; townId++) {
            // Check that there is a ticket from startIndex to townId
            if (tickets[startIndex][townId] != 0) {
                activeItineraries.add( new Itinerary( startIndex, townId, tickets[startIndex][townId] ) );
            }
        }

        // For each existing itinerary in activeItineraries
        while(activeItineraries.size() != 0) {
            // Get the first one in the array
            Itinerary itinerary = activeItineraries.get(0);
            activeItineraries.remove(0);
            // And move it to inactive. 
            inactiveItineraries.add(itinerary);

            // Find available destinations
            int[] availableTickets = tickets[itinerary.getLastTownId()];
            
            // Limit available destinations which have existing iteneraries and have not been already
            // visited
            for(int townId = 0; townId < availableTickets.length; townId++) {

                // If the itinerary is available and the city was not already visited
                if (availableTickets[townId] != 0 && !itinerary.isVisited(townId)) {
                    
                    // Create new itinerary containgin the flights from previous itinerary
                    Itinerary newItinerary = new Itinerary(itinerary);
                    // but with added new flight and price
                    // This way, I just keep adding new and new iteneraries to the 'activeItineraries' and don't have
                    // to think about what to do with the previous. 
                    // For assignment this small (5x5), it is OK
                    newItinerary.moveTo(townId, availableTickets[townId]);

                    // Found the end destination
                    if (townId == endIndex) {
                        // Preserve this itinerary in 'inactiveItineraries' so no more flights are upon it.
                        // This saves some moves
                        inactiveItineraries.add(newItinerary);
                    } else {
                        // Move it to 'activeItineraries' so I could use it later.
                        activeItineraries.add(newItinerary);
                    }
                }
            }
        }

        return trace(inactiveItineraries, startIndex, endIndex);
    }

    /**
     * Get starting and finish destination and start bruteforce.
     * @return Trace for cheapest flight.
     */
    public String solve() {
        if (!check()) {
            return "Invalid input parameters.";
        }

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
        return bruteForce(start, end);
    }
}
