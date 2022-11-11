
package com.add;

import java.util.ArrayList;

public class Node {
    public String value;
    public Node inp;
    public ArrayList <Node> outs;

    public Node(String value) {
        this.value = value;
        this.outs = new ArrayList<>();
    }

    public void addOut(Node n2) {
        for (Node n : outs) {
            if (n == n2) return;
        }
        outs.add(n2);
        n2.inp = this;
    }
}
