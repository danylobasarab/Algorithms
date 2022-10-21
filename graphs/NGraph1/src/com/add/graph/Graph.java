
package com.add.graph;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Администратор
 */
public class Graph {
    public static String fileinp = "input.txt";
    public static String fileout = "output.txt";
    public static ArrayList<Node> pool = new ArrayList<>();    
    
    // Функція пошуку вузлу
    public static Node find(int value) {
        for (Node n : pool) {
            if (n.value == value) return n;
        }
        Node n = new Node(value);
        pool.add(n);
        return n;
    }
    
    // Функція завантаження графу n1->n2
    public static void load() {
        try {
            List <String> st = Files.readAllLines(new File(fileinp).toPath());
            for (String s : st) if (!s.isEmpty()) {
                String[] sp = s.split(" ");
                if (sp.length == 2) {
                    Node n1 = find(Integer.parseInt(sp[0]));
                    Node n2 = find(Integer.parseInt(sp[1]));
                    n1.addOut(n2);
                }                
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }        
    }
    
    // Пошук шляху з вузлу n1 до n2
    public static boolean stepFind(Node n1, Node n2) {
        if (n1 == n2) {
            return true;
        }
        while (n1.outs.size() > 0) {
            Node n = n1.outs.get(0);
            n1.outs.remove(n);
            if (stepFind(n, n2)) return true;
        }
        return false;
    }
    
    // Фнкція пошуку усіх коренів графу
    public static void getRoots() {
        String st = "";
        load();
        for (int i=0;i<pool.size();i++) {
            boolean res = true;
            Node n1 = pool.get(i);
            for (int j=0;j<pool.size();j++) {
                if (i != j) {
                    if (!stepFind(n1, pool.get(j))) {
                        res = false;
                        break;
                    }
                    load();
                }
            }
            if (res) {
                st += "Root node: "+n1.value +"\r\n";
            }
        }
        System.out.println(st);
        try {
            Files.write(new File(fileout).toPath(), st.getBytes(), StandardOpenOption.CREATE);
        }
        catch (Exception ex) {     
            System.out.println(ex);
        }
    }
}
