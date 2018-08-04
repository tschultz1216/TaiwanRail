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
public class Graph {
    HashMap<Integer, Node> nodes;
    HashMap<Integer, Edge> edges;
    
    public Graph(HashMap<Integer, Node> nodes,HashMap<Integer, Edge> edges){
        this.nodes = nodes;
        this.edges = edges;
    }
}
