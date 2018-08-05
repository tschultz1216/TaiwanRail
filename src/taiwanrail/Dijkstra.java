/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanrail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Todd
 */
public class Dijkstra {

    private HashMap<Integer, Node> nodes;
    private HashMap<Integer, Edge> edges;
    private Set<Node> settledNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;

    public Dijkstra(Graph graph) {
        this.nodes = new HashMap<Integer, Node>(graph.getNodes());
        this.edges = new HashMap<Integer, Edge>(graph.getEdges());
    }

    public void execute(Node source) {
        settledNodes = new HashSet<Node>();
        unSettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Integer>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Node node, Node target) {
        for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
            if (edge.getValue().getStart().equals(node.getSymbol())
                    && edge.getValue().getEnd().equals(target.getSymbol())) {
                return edge.getValue().getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
            if (edge.getValue().getStart().equals(node.getSymbol())
                    && !isSettled(getNodeFromEdge(edge.getValue(), false))) {
                neighbors.add(getNodeFromEdge(edge.getValue(), false));
            }
        }
        return neighbors;
    }

    private Node getMinimum(Set<Node> nodes) {
        Node minimum = null;
        for (Node node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }

    public Node getNodeFromEdge(Edge edge, boolean flag) {
        if (flag) {
            for (Map.Entry<Integer, Node> n : nodes.entrySet()) {
                if (n.getValue().getSymbol().equals(edge.getStart())) {
                    return n.getValue();
                }
            }
        } else {
            for (Map.Entry<Integer, Node> n : nodes.entrySet()) {
                if (n.getValue().getSymbol().equals(edge.getEnd())) {
                    return n.getValue();
                }
            }
        }
        return null;
    }

    private int getShortestDistance(Node destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Node> getPath(Node target) {
        LinkedList<Node> path = new LinkedList<Node>();
        Node step = target;
        // check if a path exists
        if (getPredecessorByNode(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    public Node getPredecessorByNode(Node node) {

        for (Map.Entry<Node, Node> n : this.predecessors.entrySet()) {
            if (n.getKey().getSymbol() == node.getSymbol()) {
                return n.getValue();
            }
        }
        return null;
    }

}
