package solution;

/**
 * This class contains one node - transition from one town to another and corresponding ticket price.
 */
public class Node {
    // ID of previous town - this is the index of 'towns' array in BruteForce class
    private int previous;
    // ID of current town  - this is the index of 'towns' array in BruteForce class
    private int current;
    // Ticket price for getting from previous to current town
    private int price;

    /**
     * Constructor for new node.
     * @param nodeId Current town ID. It is an index of array 'towns' in BruteForce class.
     */
    public Node(int nodeId) {
        current = nodeId;
        previous = -1;  // -1 means this is the starting node
        price = 0;
    }

    /**
     * Record move from previous node to this node.
     * @param nodeId Previous node ID. It is an index of array 'towns' in BruteForce class.
     * @param cost Ticket cost for moving from previous to current node.
     */
    public void setPrevious(int nodeId, int cost) {
        previous = nodeId;
        price += cost;
    }

    /**
     * Get price for getting from previous node to this one.
     * @return Ticket price for the flight.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get current node ID - aka index in array 'towns' in BruteForce class.
     * @return Current node ID.
     */
    public int getId() {
        return current;
    }

    /**
     * Get ID of previous node - aka index in array 'towns' in BruteForce class.
     * @return Previous node ID.
     */
    public int getPreviousId() {
        return previous;
    }
}