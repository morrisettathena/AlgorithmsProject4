import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Arrays;

public class knapsack62 {

    public static void main(String args[]) throws FileNotFoundException{

        System.out.println("GETTING DATA FOR 6.2");

        File folder = new File("data");
        File[] FILES = folder.listFiles();
        Arrays.sort(FILES, (a, b) -> Integer.compare(
            Integer.parseInt(a.getName().substring(0, a.getName().length()-4)), 
            Integer.parseInt(b.getName().substring(0, b.getName().length()-4))
        ));

        int[] weights = {0, 2, Integer.MAX_VALUE};

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
                int[] data = knapsack(set[0], set[1], weight);
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
        }

        System.out.println("no fit: \n" + nf);
        System.out.println("some fit: \n" + sf);
        System.out.println("all fit: \n" + af);
    }

    public static int[] knapsack(int[] p, int[] w, int W) {
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

        return  new int[] {maxprofit, nodesVisited};
        /*System.out.println("There was a total profit of " + maxprofit);
        printPath(currentpath);
        System.out.println("The number of nodes visited was " + nodesVisited);
        System.out.println();*/
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

