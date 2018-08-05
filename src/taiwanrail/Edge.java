
package taiwanrail;

import java.util.HashMap;

/**
 *
 * @author Todd
 */
public class Edge {
   private int weight;
   private String start;
   private String end;
   
   public Edge(String start, String end, int weight){
        this.weight = weight;
        this.start = start;
        this.end = end;
   }
   
   public int getWeight(){
       return this.weight;
   }
   
   public String getStart() {
       return this.start;
   }

   public String getEnd() {
       return this.end;
   }
   
   @Override
   public String toString(){
       return "an Edge from" + start + " to " + end + " of weight: " + weight;
   } 
    
}
