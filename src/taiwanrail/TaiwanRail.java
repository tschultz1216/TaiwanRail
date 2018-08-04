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
        String test = "<A,B,5><B,C,6><A,C,3><A,D,3><D,B,10>";
        System.out.println(test.length());
        readInput(test);
    }

// TODO CHANGE TO SPLIT INPUT ON >
// StartNode = s.substr(1,2)
// EndNode = s.substr(3,4)
// Weight = Integer.parseInt(s.substr(5, s.length - 1))
    public static void readInput(String input) {
        HashMap<Integer, Edge> edges = new HashMap<Integer, Edge>();
        HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
        int count = 0;
        String[] paths = input.split(">");
        for (String s : paths) {
            Node tempNode1 = new Node(s.substring(1, 2));
            Node tempNode2 = new Node(s.substring(3, 4));
            Edge newEdge = new Edge(tempNode1.getSymbol(), tempNode2.getSymbol(), Integer.parseInt(s.substring(5, s.length())));

            if (!tempNode1.checkForNode(nodes)) {
                nodes.put(count, tempNode1);
                System.out.println(tempNode1);
            }

            if (!tempNode2.checkForNode(nodes)) {
                count++;
                nodes.put(count, tempNode2);
                System.out.println(tempNode2);
            }
            count++;
        }
        Graph graph = new Graph(nodes, edges);
    }
}
