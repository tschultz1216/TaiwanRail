package taiwanrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Todd
 */
public class Node implements Comparable<Node> {

    public enum Status {
        NEW,
        VISITED
    }

    private String symbol;
    private Status status;
    private int distance = Integer.MAX_VALUE;
    private Node previous = null;

    public Node() {
    }

    public Node(String symbol) {
        this.symbol = symbol;
        status = Status.NEW;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void visit() {
        this.status = Status.VISITED;
        return;
    }

    public Status getStatus() {
        return this.status;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    public void setPrevious(Node n){
        this.previous = n;
    }
    
    public Node getPrevious(){
        return this.previous;
    }
    
    @Override
    public int compareTo(Node other) {
        return Integer.compare(distance, other.distance);
    }
    
    @Override
    public String toString() {

        return "Node: " + symbol;
    }

    public boolean checkForNode(HashMap<Integer, Node> nodes) {
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            String value = entry.getValue().symbol;
            if (this.symbol.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
