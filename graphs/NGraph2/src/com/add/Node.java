
package com.add;

import java.util.ArrayList;


public class Node {
    public String value;
    public ArrayList <Node> outs;
    
    // Створення нового вузлу
    public Node(String value) {
        this.value = value;
        this.outs = new ArrayList<>();
    }
    
    // Додавання вузлу нижче
    public void addOut(Node n2) {
        for (Node n : outs) {
            if (n.value.equals(n2.value)) return;
        }
        outs.add(n2);
    }
}
