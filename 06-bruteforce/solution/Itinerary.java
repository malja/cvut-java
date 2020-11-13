package solution;

import java.util.ArrayList;

public class Itinerary {
    private ArrayList<Integer> visited = new ArrayList<Integer>();
    private ArrayList<Integer> prices = new ArrayList<Integer>();

    public Itinerary(Itinerary other) {
        for(Integer townId: other.visited) {
            this.visited.add(townId);
        }

        for(Integer price: other.prices) {
            this.prices.add(price);
        }
    }

    public Itinerary(int fromId, int toId, int price) {
        visited.add(fromId);
        visited.add(toId);
        prices.add(price);
    }

    public void moveTo(int townId, int price) {
        visited.add(townId);
        prices.add(price);
    }

    public Boolean isVisited(int townId) {
        return visited.indexOf(townId) != -1;
    }

    public int getLastTownId() {
        // Hate Java already
        return  visited.get(visited.size() - 1);
    }

    public int getPrice() {
        // Hate Java a bit more
        return prices.stream().mapToInt(a -> a).sum();
    }

    public String trace(String[] towns) {
        String output = "";
        for (int i = 0; i < visited.size()-1; i++) {
            output += towns[visited.get(i)] + " -> " + towns[visited.get(i+1)] + " for " + prices.get(i) + "\n";
        }
        output += "Total price: " + getPrice();

        return output;
    }
}
