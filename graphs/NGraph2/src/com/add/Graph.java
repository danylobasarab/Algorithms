
package com.add;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class Graph {
    public static String fileinp = "govern.in";       // Файл сумыжності графу
    public static String fileout = "govern.out";      // Вихідний фыйл
    public static ArrayList<Node> pool = new ArrayList<>();    
    
    // Функція пошугу вузлу
    public static Node find(String value) {
        for (Node n : pool) {
            if (n.value.equals(value)) return n;
        }
        Node n = new Node(value);
        pool.add(n);
        return n;
    }
    
    // Функція завантаження графу
    public static void load() {
        try {
            List <String> st = Files.readAllLines(new File(fileinp).toPath());
            for (String s : st) if (!s.isEmpty()) {
                String[] sp = s.split(" ");
                if (sp.length == 2) {
                    Node n1 = find(sp[0]);
                    Node n2 = find(sp[1]);
                    n1.addOut(n2);
                }                
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }        
    }
    
    public static void printEmpty() {
        String st = "";
        load();
        while (pool.size() > 0) {            
            for (Node n : pool) {
                // Визначення вузлу без вихідних зв'язків
                if (n.outs.isEmpty()) {
                    // Видалення зв'язків на даний вузол
                    for (Node m : pool) {
                        m.outs.remove(n);
                    }
                    st += n.value +"\r\n";
                    pool.remove(n);
                    break;
                }
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
