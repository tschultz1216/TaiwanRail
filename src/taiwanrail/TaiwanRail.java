/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
//                System.out.println(tempNode1);
            }

            if (!tempNode2.checkForNode(nodes)) {
                nodeCount++;
                nodes.put(nodeCount, tempNode2);
//                System.out.println(tempNode2);
            }
            nodeCount++;
        }
        Graph graph = new Graph(nodes, edges);
        
        ArrayList<String> allStops = new ArrayList<String>();
        allStops.add("A");
        allStops.add("B");
        allStops.add("C");
        allStops.add("D");
        allStops.add("E");
        
        ArrayList<String> routeA = new ArrayList<String>();
        routeA.add(allStops.get(0));
        routeA.add(allStops.get(1));
        routeA.add(allStops.get(2));
        
        ArrayList<String> routeB = new ArrayList<String>();
        routeB.add(allStops.get(0));
        routeB.add(allStops.get(3));
        
        ArrayList<String> routeC = new ArrayList<String>();
        routeC.add(allStops.get(0));
        routeC.add(allStops.get(3));
        routeC.add(allStops.get(2));
        
        ArrayList<String> routeD = new ArrayList<String>();
        routeD.add(allStops.get(0));
        routeD.add(allStops.get(4));
        routeD.add(allStops.get(1));
        routeD.add(allStops.get(2));
        routeD.add(allStops.get(3));
        
        ArrayList<String> routeE = new ArrayList<String>();
        routeE.add(allStops.get(0));
        routeE.add(allStops.get(4));
        routeE.add(allStops.get(3));
// Print out solutions of Problems a-e
        graph.printPathWeight(routeA);
        graph.printPathWeight(routeB);
        graph.printPathWeight(routeC);
        graph.printPathWeight(routeD);
        graph.printPathWeight(routeE);
// Print answer to question f
        int i = graph.loopWithMaxUniqueStops(graph.getNode("C"), 3);
        System.out.println(i);
// Print answer to question g
// Print out number of unique 4 stop trips from A to C
        int plz = graph.tryMe(graph.getNode("A"), graph.getNode("C"));
        System.out.println("TOTAL TRIPS: " + plz);
// Perform Dijkstra traversal to find shortest path from origin
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.execute(nodes.get(0));
// Question H
// gets shortest path from A to any other Node
        LinkedList<Node> path = dijkstra.getPath(graph.getNode("C"));
        for (Node node : path) {
            System.out.println(node);
        }
//Question I
// gets shortest cycle from a given node, if no cycle is present it will return null
        ArrayList<Node> otherPath = graph.getShortestCycle(graph.getNode("C"));
        for(Node n : otherPath){
            System.out.println(n);
        }
//Question J
//Gets Number of Cyces under a given weight limit
        System.out.println(graph.getNumberOfCyclesUnderWeightLimit(graph.getNode("C"), 50));
    }
}
