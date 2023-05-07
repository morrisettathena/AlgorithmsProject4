import java.util.Collections;
import java.util.PriorityQueue;


public class knapsack62 {

    public static void main(String args[]) {

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

        

        knapsack(set1[0], set1[1], set1W);
        knapsack(set2[0], set2[1], set2W);
        knapsack(set3[0], set3[1], set3W);
        knapsack(set4[0], set4[1], set4W);
        knapsack(set5[0], set5[1], set5W);
    }

    public static void knapsack(int[] p, int[] w, int W) {
        Node v = new Node();

        PriorityQueue<Node> PQ = new PriorityQueue<Node>();

        int maxprofit = 0;
        int nodesVisited = 0;
        boolean[] currentpath = {};

        v.bound = bound(v, p, w, W);
        v.included = newBool(w.length);
        PQ.add(v);

        while (!PQ.isEmpty()) {
            v = PQ.remove();
            nodesVisited++;
            if (v.bound > maxprofit) {
                Node u = new Node();
                u.level = v.level + 1;
                u.weight = v.weight + w[u.level];
                u.profit = v.profit + p[u.level];
                u.included = copyBool(v.included);
                u.included[u.level] = true;
                

                if (u.weight <= W && u.profit > maxprofit) {
                    currentpath = u.included;
                    maxprofit = u.profit;
                }

                u.bound = bound(u, p, w, W);
                if (u.bound > maxprofit)
                    PQ.add(u);
                
                Node y = new Node();
                y.level = v.level + 1;
                y.weight = v.weight;
                y.profit = v.profit;
                y.included = copyBool(v.included);
                y.included[y.level] = false;

                y.bound = bound(y, p, w, W);


                if (y.bound > maxprofit)
                    PQ.add(y);
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
}

