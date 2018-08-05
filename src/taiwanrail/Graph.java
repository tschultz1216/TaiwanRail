/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Todd
 */
public class Graph {

    HashMap<Integer, Node> nodes;
    HashMap<Integer, Edge> edges;

    public Graph(HashMap<Integer, Node> nodes, HashMap<Integer, Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public void printPathWeight(String[] stops) {
        int weight = this.getPathWeight(stops);
        if (weight == -1) {
            System.out.println("NO SUCH ROUTE");
            return;
        }
        System.out.println(weight);
        return;
    }

    private boolean containsEdge(String start, String end) {
        for (Map.Entry<Integer, Edge> e : edges.entrySet()) {
            Edge edge = e.getValue();
            if (edge.getStart().equals(start) && edge.getEnd().equals(end)) {
                return true;
            }
        }
        return false;
    }

    public Node getNode(String symbol) {
        for (Map.Entry<Integer, Node> n : nodes.entrySet()) {
            if (n.getValue().getSymbol().equals(symbol)) {
                return n.getValue();
            }
        } return null;
    }

    public int getPathWeight(String[] stops) {
        int weight = 0;
        for (int i = 0; i < stops.length - 1; i++) {
            String start = stops[i];
            String end = stops[i + 1];
            for (Map.Entry<Integer, Edge> e : edges.entrySet()) {
                Edge edge = e.getValue();
                if (this.containsEdge(start, end)) {
                    if (edge.getStart().equals(start) && edge.getEnd().equals(end)) {
                        weight += edge.getWeight();
                    }
                } else {
                    return -1;
                }
            }
        }
        return weight;
    }

    public ArrayList<Edge> getNeighbors(Node n) {

        ArrayList<Edge> neighbors = new ArrayList<Edge>();
        for (Map.Entry<Integer, Edge> e : edges.entrySet()) {
            if (e.getValue().getStart().equals(n.getSymbol())) {
                neighbors.add(e.getValue());
            }
        }
        return neighbors;
    }
}
