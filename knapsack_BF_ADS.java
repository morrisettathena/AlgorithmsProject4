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

public class knapsack_BF_ADS {

public static void knapsack2(int[] p, int[] w, int W) {

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

    System.out.println("There was a total profit of " + maxprofit);
    printPath(currentpath);
    System.out.println("The number of nodes visited was " + nodesVisited);
    System.out.println();

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

public static void main(String[] args) {

    int[][] set1 = {
        {0, 40, 30, 50, 10},
        {0, 2, 5, 10, 5}
    };
    int set1W = 16;

    int[][] set2 = {
        {0, 40, 30, 50, 10},
        {0, 2, 5, 10, 5}
    };
    int set2W = 18;

    int[][] set3 = {
        {0, 50, 55, 15, 50},
        {0, 2, 10, 5, 20}
    };
    int set3W = 25;

    int[][] set4 = {
        {0, 50, 55, 15, 50},
        {0, 2, 10, 5, 20}
    };
    int set4W = 40;

    int[][] set5 = {
        {0, 1, 1, 1, 1},
        {0, 2, 3, 4, 5}
    };
    int set5W = 1;

    knapsack2(set1[0], set1[1], set1W);
    knapsack2(set2[0], set2[1], set2W);
    knapsack2(set3[0], set3[1], set3W);
    knapsack2(set4[0], set4[1], set4W);
    knapsack2(set5[0], set5[1], set5W);

}

}
 
