/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanrail;

import java.util.HashMap;

/**
 *
 * @author Todd
 */
public class TaiwanRail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String test = "<A,B,5><B,C,6>";
        System.out.println(test.length());
        readInput(test);
    }

    public static void readInput(String input) {
        HashMap<Integer, Edge> edges = new HashMap<Integer, Edge>();
        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        int count = 0;
        for (int split = 7; split < input.length() + 1;) {
            
            Edge newEdge = new Edge(input.substring(split - 6, split - 5), input.substring(split - 4, split - 3), Integer.parseInt(input.substring(split - 2, split - 1)));
            edges.put(count, newEdge);
            Node tempNode1 = new Node(input.substring(split - 6, split - 5));
            Node tempNode2 = new Node(input.substring(split - 4, split - 3));
            tempNode1.getEdges().put(count, newEdge);
            tempNode2.getEdges().put(count, newEdge);
            
            if (!nodes.containsValue(tempNode1)) {
                nodes.put(count, tempNode1);
            }
            
            if (!nodes.containsValue(tempNode2)) {
                nodes.put(count, tempNode2);
            }
            
            System.out.println(edges.get(count).toString());
            System.out.println(nodes.get(count).toString());
            
            split += split;
            count++;
        }
    }
}
