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
public class TaiwanRail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String test = "<A,B,5><B,C,4><C,D,8><D,C,8><D,E,6><A,D,5><C,E,2><E,B,3><A,E,7>";
        readInput(test);
    }

// TODO CHANGE TO SPLIT INPUT ON >
// StartNode = s.substr(1,2)
// EndNode = s.substr(3,4)
// Weight = Integer.parseInt(s.substr(5, s.length - 1))
    public static void readInput(String input) {
        HashMap<Integer, Edge> edges = new HashMap<Integer, Edge>();
        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        int nodeCount = 0;
        int edgeCount = 0;
        String[] paths = input.split(">");
        for (String s : paths) {
            Node tempNode1 = new Node(s.substring(1, 2));
            Node tempNode2 = new Node(s.substring(3, 4));
            Edge newEdge = new Edge(tempNode1.getSymbol(), tempNode2.getSymbol(), Integer.parseInt(s.substring(5, s.length())));
            edges.put(edgeCount, newEdge);
            edgeCount++;
                    
            if (!tempNode1.checkForNode(nodes)) {
                nodes.put(nodeCount, tempNode1);
                System.out.println(tempNode1);
            }

            if (!tempNode2.checkForNode(nodes)) {
                nodeCount++;
                nodes.put(nodeCount, tempNode2);
                System.out.println(tempNode2);
            }
            nodeCount++;
        }
        Graph graph = new Graph(nodes, edges);
        String[] s = {"A", "E", "B", "C", "D"};
        System.out.println(graph.getPathWeight(s));
    }
}
