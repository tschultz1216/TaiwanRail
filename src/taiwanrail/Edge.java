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
public class Edge {
   int weight;
   String start;
   String end;
   
   public Edge(int weight, String start, String end){
        this.weight = weight;
        this.start = start;
        this.end = end;
   }
    
}
