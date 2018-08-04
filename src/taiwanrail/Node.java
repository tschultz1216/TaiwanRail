/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanrail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Todd
 */
public class Node {
    
    private String symbol;
    private HashMap<Integer, Edge> edges = new HashMap<Integer, Edge>();
    
    public Node(String symbol){
        this.symbol = symbol;
    }
    
    public String getSymbol(){
        return this.symbol;
    }

    public HashMap<Integer, Edge> getEdges(){
        return this.edges;
    }
    
   @Override
   public String toString(){
       return "This is node " + symbol;
   } 
    
}
