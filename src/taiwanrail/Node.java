/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taiwanrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Todd
 */
public class Node {

    private String symbol;

    public Node(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {

        return "This is node " + symbol;
    }

    public boolean checkForNode(HashMap<Integer, Node> nodes) {
        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            String value = entry.getValue().symbol;
            if (this.symbol.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
