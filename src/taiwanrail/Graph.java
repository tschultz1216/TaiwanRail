/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanrail;

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

    public int getPathWeight(String[] stops) {
        int weight = 0;
        for (int i = 0; i < stops.length - 1; i++) {
            String start = stops[i];
            String end = stops[i + 1];
            for (Map.Entry<Integer, Edge> e : edges.entrySet()) {
                Edge edge = e.getValue();
                if (edge.getStart().equals(start) && edge.getEnd().equals(end)) {
                    weight += edge.getWeight();
                }

                // do what you have to do here
                // In your case, another loop.
            }
        }
        return weight;
    }
}
