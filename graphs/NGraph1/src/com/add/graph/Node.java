
package com.add.graph;

import java.util.ArrayList;

/**
 *
 * @author Администратор
 */
public class Node {
    public int value;             // Значення вузлу
    public ArrayList <Node> outs; // Вузли виходу
    
    // Створення нового вузлу
    public Node(int value) {
        this.value = value;
        this.outs = new ArrayList<>();
    }
    
    // Додавання вузлу нижче
    public void addOut(Node n2) {
        for (Node n : outs) {
            if (n.value == n2.value) return;
        }
        outs.add(n2);
    }
}
