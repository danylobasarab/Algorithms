
package com.add;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static String fileinp = "wchain.in";
    public static String fileout = "wchain.out";
    public static ArrayList<Node> pool = new ArrayList<>();    

    public static Node find(String value) {
        for (Node n : pool) {
            if (n.value.equals(value)) return n;
        }
        Node n = new Node(value);
        pool.add(n);
        return n;
    }

    public static void load() {
        try {
            List <String> st = Files.readAllLines(new File(fileinp).toPath());
            int n = Integer.parseInt(st.get(0));
            for (int i=0;i<n;i++) {
                String s = st.get(i+1);
                if (!s.isEmpty()) {
                    pool.add(new Node(s));
                }                
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }        
    }

    public static void sort() {
        for (int i=0;i<pool.size();i++) {
            Node a = pool.get(i);
            for (int j=i+1;j<pool.size();j++) {
                Node b = pool.get(j);
                if (a.value.length() < b.value.length()) {
                    pool.set(i, b);
                    pool.set(j, a);
                    a = b;
                }
            }
        }
    }

    public static void link() {
        for (int i=0;i<pool.size();i++) {
            Node a = pool.get(i);
            for (int z=0;z<a.value.length();z++) {
                String v = ((z > 0)?a.value.substring(0,z):"") + ((z < a.value.length()-1)?a.value.substring(z+1):"");
                for (int j=i+1;j<pool.size();j++) {
                    Node b = pool.get(j);
                    if (v.length() == b.value.length()) {
                       if (v.equals(b.value)) a.addOut(b);
                    }
                    else {
                        break;
                    }
                }
            }
        }        
    }

    public static int ways() {
        int max = 0;
        for (int i=pool.size()-1;i>=0;i--) {
            Node a = pool.get(i);
            if (a.outs.isEmpty()) {
                int m = 0;
                Node prev = a;
                do {
                    m++; //System.err.print(prev.value+" ");
                } while ((prev = prev.inp) != null);
                //System.err.println();
                if (m > max) {
                    max = m;
                }
            }
        }
        return max;
    }
    
    public static void work() {
        load();
        sort();
        link();
        try {
            Files.write(new File(fileout).toPath(), (""+ways()).getBytes(), StandardOpenOption.CREATE);
        }
        catch (Exception ex) {     
            System.out.println(ex);
        }
    }
}
