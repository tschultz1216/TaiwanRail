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
        Graph graph = readInput(test);
        printOutput(graph);
    }

    public static void printOutput(Graph graph) {
// Build list of stops to build trips with        
        ArrayList<String> allStops = new ArrayList<String>();
        allStops.add("A");
        allStops.add("B");
        allStops.add("C");
        allStops.add("D");
        allStops.add("E");
// Build trip for problem A        
        ArrayList<String> routeA = new ArrayList<String>();
        routeA.add(allStops.get(0));
        routeA.add(allStops.get(1));
        routeA.add(allStops.get(2));

// Build trip for problem B        
        ArrayList<String> routeB = new ArrayList<String>();
        routeB.add(allStops.get(0));
        routeB.add(allStops.get(3));

// Build trip for problem C        
        ArrayList<String> routeC = new ArrayList<String>();
        routeC.add(allStops.get(0));
        routeC.add(allStops.get(3));
        routeC.add(allStops.get(2));

// Build trip for problem D        
        ArrayList<String> routeD = new ArrayList<String>();
        routeD.add(allStops.get(0));
        routeD.add(allStops.get(4));
        routeD.add(allStops.get(1));
        routeD.add(allStops.get(2));
        routeD.add(allStops.get(3));

// Build trip for problem E        
        ArrayList<String> routeE = new ArrayList<String>();
        routeE.add(allStops.get(0));
        routeE.add(allStops.get(4));
        routeE.add(allStops.get(3));

// Print out solutions of Problems A-E
        System.out.println("Question A");
        System.out.println("Trip from A -> B -> C Of weight:");
        graph.printPathWeight(routeA);
        System.out.println();

        System.out.println("Question B");
        System.out.println("Trip from A -> D Of weight:");
        graph.printPathWeight(routeB);
        System.out.println();

        System.out.println("Question C");
        System.out.println("Trip from A -> D -> C Of weight:");
        graph.printPathWeight(routeC);
        System.out.println();

        System.out.println("Question D");
        System.out.println("Trip from A -> E -> B -> C Of weight:");
        graph.printPathWeight(routeD);
        System.out.println();

        System.out.println("Question E");
        System.out.println("Trip from A -> E -> C Of weight:");
        graph.printPathWeight(routeE);
        System.out.println();

// Print answer to question F
        System.out.println("Question F");
        int f = graph.loopWithMaxUniqueStops(graph.getNode("C"), 3);
        System.out.println("Number of routes from A -> C with less than three stops: " + f);
        System.out.println();

// Print answer to question G: (number of 4 stop trips from A to C)
        System.out.println("Question G");
        int g = graph.findRouteBetweenNodesWithFourStops(graph.getNode("A"), graph.getNode("C"));
        System.out.println("Number of routes from A -> C with exactly four stops:  " + g);
        System.out.println();

// Question H
// Perform Dijkstra traversal to find shortest path from origin
        System.out.println("Question H");
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.execute(graph.getNode("A"));
// gets shortest path from A to any other Node
        LinkedList<Node> path = dijkstra.getPath(graph.getNode("C"));
        System.out.println("The shortest path from A -> C is:");
        for (Node node : path) {
            System.out.println(node);
        }
        System.out.println();
//Question I
// gets shortest cycle from a given node, if no cycle is present it will return null
        System.out.println("Question I");
        ArrayList<Node> otherPath = graph.getShortestCycle(graph.getNode("C"));
        System.out.println("The shortest cycle beginning on C");
        for (Node n : otherPath) {
            System.out.println(n);
        }
        System.out.println();
//Question J
//Gets Number of Cycles under a given weight limit
        System.out.println("Question J");
        System.out.println("Number of cycles that exist with total weight less than 50:");
        System.out.println(graph.getNumberOfCyclesUnderWeightLimit(graph.getNode("C"), 50));
    }

    public static Graph readInput(String input) {
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
        return graph;
    }
}
