package taiwanrail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Todd
 */
public class Graph {

    private final HashMap<Integer, Node> nodes;
    private final HashMap<Integer, Edge> edges;

    public Graph(HashMap<Integer, Node> nodes, HashMap<Integer, Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public HashMap<Integer, Node> getNodes() {
        return this.nodes;
    }

    public HashMap<Integer, Edge> getEdges() {
        return this.edges;
    }

    public void printPathWeight(ArrayList<String> stops) {
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
        }
        return null;
    }

    public Edge getEdge(String start, String end) {
        for (Map.Entry<Integer, Edge> e : edges.entrySet()) {
            if (e.getValue().getStart().equals(start) && e.getValue().getEnd().equals(end)) {
                return e.getValue();
            }
        }
        return null;
    }

    public int getPathWeight(ArrayList<String> path) {
        int weight = 0;
        for (String current : path) {
            if (path.indexOf(current) < path.size() - 1) {
                String next = path.get(path.indexOf(current) + 1);
                if (this.containsEdge(current, next)) {
                    Edge edge = getEdge(current, next);
                    weight += edge.getWeight();
                    if (edge.getEnd().equals(path.get(path.size() - 1))) {
                        return weight;
                    }
                } else {
                    return -1;
                }
            }
        }
        return weight;
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

    private Node getNearestNeighbor(Node start, ArrayList<Node> neighbors) {
        Node nearestNeighbor = new Node();
        int minWeight = Integer.MAX_VALUE;
        for (Node n : neighbors) {
            Edge tempEdge = this.getEdge(start.getSymbol(), n.getSymbol());
            if (minWeight > tempEdge.getWeight()) {
                minWeight = tempEdge.getWeight();
                nearestNeighbor = this.getNodeFromEdge(tempEdge, false);
            }
        }
        return nearestNeighbor;
    }

    public ArrayList<Node> getNeighbors(Node n) {

        ArrayList<Node> neighbors = new ArrayList<Node>();
        for (Map.Entry<Integer, Edge> e : edges.entrySet()) {
            if ((e.getValue().getStart().equals(n.getSymbol())) && n.getStatus().equals(Node.Status.NEW)) {
                neighbors.add(this.getNodeFromEdge(e.getValue(), false));
            }
        }
        n.visit();
        return neighbors;
    }

    public int shortestPath(Node start, Node end) {

        ArrayList<Node> neighbors = this.getNeighbors(start);
        int weight = 0;
        int minWeight = 0;
        String path = start.getSymbol();
        for (Node n : neighbors) {

//            if(e.getEnd().equals(end.getSymbol())){
//                weight = e.getWeight();
//                path = path + e.getEnd();
//                System.out.println(path);
//            }
        }
        return weight;
    }

    public void shortestPathHelper(Node start, Node end, int currentWeight) {

        ArrayList<Node> neighbors = this.getNeighbors(start);
        Node nearestNeighbor = this.getNearestNeighbor(start, neighbors);
    }

    public boolean moreNodes() {

        for (Map.Entry<Integer, Node> n : nodes.entrySet()) {
            if (n.getValue().getStatus().equals(Node.Status.NEW)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Edge> getNeighborEdges(Node n) {

        ArrayList<Edge> neighbors = new ArrayList<Edge>();
        for (Map.Entry<Integer, Edge> e : edges.entrySet()) {
            if ((e.getValue().getStart().equals(n.getSymbol()))) {
                neighbors.add(e.getValue());
            }
        }
        n.visit();
        return neighbors;
    }

    public ArrayList<Node> computeShortestPath(Node source, Node target) {
        source.setDistance(0);
        PriorityQueue<Node> nodeQueue = new PriorityQueue<Node>();
        nodeQueue.add(source);
        while (!nodeQueue.isEmpty()) {
            Node u = nodeQueue.poll();
            for (Edge e : this.getNeighborEdges(source)) {
                Node v = this.getNodeFromEdge(e, false);
                int weight = e.getWeight();
                int distanceThroughSource = u.getDistance() + weight;
                if (distanceThroughSource < v.getDistance()) {
                    nodeQueue.remove(v);

                    v.setDistance(distanceThroughSource);
                    v.setPrevious(u);
                    nodeQueue.add(v);
                }
            }
        }
        return this.getShortestPath(this.getNode(source.getSymbol()), this.getNode(target.getSymbol()));
    }

    private ArrayList<Node> getShortestPath(Node source, Node target) {
        ArrayList<Node> path = new ArrayList<Node>();
        for (Node node = target; node != null; node = node.getPrevious()) {
            path.add(node);
            System.out.println(node);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public int loopWithMaxUniqueStops(Node node, int limit) {
        ArrayList<String> stops = new ArrayList<String>();
        stops.add(node.getSymbol());
        ArrayList<Edge> edges = this.getNeighborEdges(node);
        int found = 0;
        for (Edge e : edges) {
            ArrayList<String> path = this.loopWithMaxUniqueStopsHelper(this.getNodeFromEdge(e, false), stops, limit);
            if (!path.isEmpty()) {
                stops = new ArrayList<String>();
                stops.add(node.getSymbol());
                found++;
            }
        }
        return found;
    }

    private ArrayList<String> loopWithMaxUniqueStopsHelper(Node node, ArrayList<String> stops, int limit) {
        stops.add(node.getSymbol());
        if (this.getUniqueStops(stops) <= limit && stops.get(0).equals(stops.get(stops.size() - 1))) {
            return stops;
        }
        if (this.getUniqueStops(stops) > limit) {
            return new ArrayList<String>();
        }
        ArrayList<Edge> edges = this.getNeighborEdges(node);
        for (Edge e : edges) {
            return this.loopWithMaxUniqueStopsHelper(this.getNodeFromEdge(e, false), stops, limit);
        }
        return new ArrayList<String>();
    }

    private int getUniqueStops(ArrayList<String> stops) {
        ArrayList<String> uniques = new ArrayList<String>();
        for (String s : stops) {
            if (!uniques.contains(s)) {
                uniques.add(s);
            }
        }
        return uniques.size();
    }

    public int getNumOfUniqueStopsForRoute(Node start, Node end, int numStops) {
        int found = 0;
        ArrayList<String> stops = new ArrayList<String>();
        stops.add(start.getSymbol());
        ArrayList<Edge> edges = this.getNeighborEdges(start);
        for (Edge e : edges) {
            ArrayList<String> path = this.getUniqueStopsForRoute(this.getNodeFromEdge(e, false), end, stops, numStops);
            if (!path.isEmpty()) {
                found = ((path.size() - 1) / numStops) + found;
                stops = new ArrayList<String>();
                stops.add(start.getSymbol());
                path = new ArrayList<String>();
            }
        }
        return found;
    }

    private ArrayList<String> getUniqueStopsForRoute(Node current, Node end, ArrayList<String> stops, int numStops) {
        stops.add(current.getSymbol());
        ArrayList<String> finalPath = new ArrayList<String>();
        if (stops.size() == numStops + 1 && stops.get(stops.size() - 1).equals(end.getSymbol())) {
            System.out.println("Path: ");
            for (String s : stops) {
                System.out.println(s);
            }
            ArrayList<String> result = stops;
            stops = new ArrayList<String>();
            return result;
        }
        if (stops.size() - 1 > numStops) {
            return new ArrayList<String>();
        }

        ArrayList<Edge> edges = this.getNeighborEdges(current);
        for (Edge e : edges) {
            ArrayList<String> path = this.getUniqueStopsForRoute(this.getNodeFromEdge(e, false), end, stops, numStops);
            if (!path.isEmpty()) {
                ArrayList<String> result = stops;
                stops = new ArrayList<String>();
                return result;
            }
        }
        return finalPath;
    }

    public int tryMe(Node Start, Node end) {
        int count = 0;
        ArrayList<Edge> parents = getParentEdges(end);
        ArrayList<Edge> children = getNeighborEdges(Start);
        for (Edge child : children) {
            ArrayList<Edge> grandChildren = getNeighborEdges(getNode(child.getEnd()));
            for (Edge grandChild : grandChildren) {
                ArrayList<Edge> greatGrandChildren = getNeighborEdges(getNode(grandChild.getEnd()));
                for (Edge greatGrandChild : greatGrandChildren) {
                    for (Edge parent : parents) {
                        if (parent.getStart().equals(greatGrandChild.getEnd())) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private ArrayList<Edge> getParentEdges(Node node) {
        ArrayList<Edge> parentEdges = new ArrayList<Edge>();
        for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
            if (edge.getValue().getEnd().equals(node.getSymbol())) {
                parentEdges.add(edge.getValue());
            }
        }
        return parentEdges;
    }

    public ArrayList<Node> getShortestCycle(Node source) {
        ArrayList<Edge> parents = getParentEdges(source);
        ArrayList<Edge> children = getNeighborEdges(source);
        ArrayList<Node> path = new ArrayList<Node>();
        path.add(source);
        for (Edge parent : parents) {
            for (Edge child : children) {
                if (child.getEnd().equals(parent.getStart())) {
                    path.add(getNode(parent.getStart()));
                    path.add(source);
                    return path;
                }
                ArrayList<Edge> grandChildren = getNeighborEdges(getNode(child.getEnd()));
                for (Edge grandChild : grandChildren) {
                    if (grandChild.getEnd().equals(parent.getStart())) {
                        path.add(getNodeFromEdge(child, false));
                        path.add(getNodeFromEdge(grandChild, false));
                        path.add(getNodeFromEdge(parent, false));
                        return path;
                    }
                }
            }
        }
        if (path.size() <= 1) {
            path = null;
        }
        return path;
    }

    public int getNumberOfCyclesUnderWeightLimit(Node source, int limit) {
        ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();
        ArrayList<Edge> parents = getParentEdges(source);
        ArrayList<Edge> children = getNeighborEdges(source);
        ArrayList<Node> path = new ArrayList<Node>();
        int count = 0;
        int check = 0;
        path.add(source);
        for (Edge parent : parents) {
            for (Edge child : children) {
                if (child.getEnd().equals(parent.getStart())) {
                    path.add(getNode(child.getEnd()));
                    path.add(source);
                    ArrayList<String> stringPath = new ArrayList<String>();
                    for (Node node : path) {
                        stringPath.add(node.getSymbol());
                    }
//                    check = getPathWeight(stringPath);

                    count += Math.floor(limit / (getPathWeight(stringPath)));
                    paths.add(path);
                    paths.add(path);
                    path = new ArrayList<Node>();
                    path.add(source);
                }
                ArrayList<Edge> grandChildren = getNeighborEdges(getNode(child.getEnd()));
                for (Edge grandChild : grandChildren) {
                    if (grandChild.getEnd().equals(parent.getStart())) {
                        path.add(getNodeFromEdge(child, false));
                        path.add(getNodeFromEdge(grandChild, false));
                        path.add(getNodeFromEdge(parent, false));
                        ArrayList<String> stringPath = new ArrayList<String>();
                        for (Node node : path) {
                            stringPath.add(node.getSymbol());
                        }
//                        check = getPathWeight(stringPath);
                        count += (int) (limit / getPathWeight(stringPath));
                        paths.add(path);
                        path = new ArrayList<Node>();
                        path.add(source);
                    }
                }
            }
        }
        return count;
    }
}
