/////////////////////////////////////////////////////////////////////////////////

// Author: Alden
// Due: 05/08/2023
// Class Name: Analysis of Algorithms
// Program Name: knapsack_BF_ADS.java
// Program Description: 

/*Let n items be given, where each item has a weight and a profit. The
weights and profits are positive integers. Furthermore, let a positive integer W be
given. Determine a set of items with maximum total profit, under the constraint
that the sum of their weights cannot exceed W */

///////////////////////////////////////////////////////////////////////////////
/*

PSEUDOCODE

Inputs: positive integers n and W , arrays of positive integers w and p, each
indexed from 1 to n, and each of which is sorted in nonincreasing order
according to the values of p [i] /w [i].
Outputs: an integer maxprofit that is the sum of the profits in an optimal set.


*/


import java.util.Collections;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class knapsack_BF_ADS {

public static int[] knapsack2(int[] p, int[] w, int W) {

    LinkedList<Node> PQ = new LinkedList<Node>();
    Node v = new Node();

    v.level = 0;
    v.profit = 0;
    v.weight = 0;
    v.included = newBool(w.length);
    boolean[] currentpath = {};
    int maxprofit = 0;
    int nodesVisited = 0;

    PQ.add(v);
    while(!PQ.isEmpty()) {
        nodesVisited++;
        v = PQ.pop();
        Node u = new Node();
        u.level = v.level +1;
        u.weight = v.weight + w[u.level];
        u.profit = v.profit + p[u.level];
        u.included = copyBool(v.included);
        u.included[u.level] = true;

        if(u.weight<= W && u.profit > maxprofit) {
            maxprofit = u.profit;
            currentpath = u.included;
        }

        if (bound(u, p, w,W) > maxprofit) {
            PQ.add(u);
        }

        u = new Node();
        u.level = v.level +1; 
        u.weight = v.weight;
        u.profit = v.profit;
        u.included = copyBool(v.included);
        u.included[u.level] = false;
        if(bound(u, p, w,W) > maxprofit) {
            PQ.add(u);
        }
    }

    /*System.out.println("There was a total profit of " + maxprofit);
    printPath(currentpath);
    System.out.println("The number of nodes visited was " + nodesVisited);
    System.out.println();*/
    return new int[] {maxprofit, nodesVisited};

}



public static float bound(Node u, int[] p, int[] w, int W) {
    int j, k;
    int totweight;
    int n = w.length-1;
    float result;

    if (u.weight >= W)
        return 0;
    else {
        result = u.profit;
        j = u.level + 1;
        totweight = u.weight;
        while (j <= n && totweight + w[j] <= W) {
            totweight = totweight + w[j];
            result = result + p[j];
            j++;
        }
        k = j;
        if (k <= n)
            result = result + (W-totweight) * p[k] / w[k];
        return result;
    }
}

public static boolean[] newBool(int i) {
    boolean[] path = new boolean[i];
    for (int j = 0; j < i; j++) {
        path[j] = false;
    }
    return path;
}

public static boolean[] copyBool(boolean[] b) {
    boolean[] newSet = newBool(b.length);
    for (int i = 1; i < b.length; i++) {
        newSet[i] = b[i];
    }
    return newSet;

}

public static void printPath(boolean[] currentpath) {
    String s = "";
    for (int i = 1; i < currentpath.length; i++) {
        if (currentpath[i])
            s += i + " ";
    }
    System.out.println("The items chosen were " + s);
}

public static void main(String[] args) throws FileNotFoundException {
    System.out.println("GETTING DATA FOR 6.1");

    File folder = new File("data");
    File[] FILES = folder.listFiles();
    Arrays.sort(FILES, (a, b) -> Integer.compare(
        Integer.parseInt(a.getName().substring(0, a.getName().length()-4)), 
        Integer.parseInt(b.getName().substring(0, b.getName().length()-4))
    ));

    int[] weights = {0, 2, Integer.MAX_VALUE};
    //int[] weights = {Integer.MAX_VALUE};
    String nf = "";
    String sf = "";
    String af = "";

    for (int i = 0; i < FILES.length; i++) {
        int[][] set = readIn.readData(FILES[i].getPath());
        int n = set[0].length-1;

        for (int j = 0; j < weights.length; j++) {
            int weight = 0;

            String typeString = "";
            String shortString = "";
            if (weights[j] == 0){
                typeString = " where no items fit";
                shortString = "nf";
                weight = weights[j];
            }
            else if (weights[j] == Integer.MAX_VALUE) {
                typeString = " where all items fit";
                shortString = "af";
                weight = weights[j];
            } else {
                typeString = " where some items fit";
                shortString = "sf";
                weight = n*weights[j];
            }
            long start = System.nanoTime();
            int[] data = knapsack2(set[0], set[1], weight);
            long end = System.nanoTime();

            if (weights[j] == 0) {
                nf += String.format("%d %d %d\n", end-start, data[0], data[1]);
            } else if (weights[j] == Integer.MAX_VALUE) {
                af += String.format("%d %d %d\n", end-start, data[0], data[1]);
            } else {
                sf += String.format("%d %d %d\n", end-start, data[0], data[1]);
            }

            /*System.out.println(FILES[i].getName() + ":" + shortString);
            System.out.print(end-start + " ");
            System.out.print(data[0] + " ");
            System.out.println(data[1]);*/
        }
        System.out.println(FILES[i] + " completed");
    }

    System.out.println("no fit: \n" + nf);
    System.out.println("some fit: \n" + sf);
    System.out.println("all fit: \n" + af);

    }

}
 
