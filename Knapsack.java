//Author: DJ Trevens

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.File;

public class Knapsack {
    public static int n = 1000;
    public static int numbest = 0;
    public static int maxprofit = 0;
    public static int nodesVisited = 0;
    public static boolean[] bestset = new boolean[n + 1];
    public static boolean[] include = new boolean[n + 1];

    public static void main(String[] args) throws FileNotFoundException{

        System.out.println("GETTING DATA FOR BACKTRACK");


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
            n = set[0].length-1;


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

                bestset = new boolean[n+1];
                include = new boolean[n+1];

                long start = System.nanoTime();
                knapsack(0, set, set[0][0], set[1][0], weight);
                long end = System.nanoTime();

                if (weights[j] == 0) {
                    nf += String.format("%d %d %d\n", end-start, maxprofit, nodesVisited);
                } else if (weights[j] == Integer.MAX_VALUE) {
                    af += String.format("%d %d %d\n", end-start, maxprofit, nodesVisited);
                } else {
                    sf += String.format("%d %d %d\n", end-start, maxprofit, nodesVisited);
                }

                /*System.out.println(FILES[i].getName() + ":" + shortString);
                System.out.print(end-start + " ");
                System.out.print(maxprofit + " ");
                System.out.println(nodesVisited);*/

                /*System.out.println("For set " + FILES[i].getName() + typeString);
                System.out.println("Time elapsed: " + Long.toString(end-start) + " nanoseconds");
                System.out.println("Max profit for set 1: " + maxprofit);

                /*System.out.println("Items for set 1: ");
                for (int j = 0; j < bestset.length; j++){
                    if (bestset[j]){
                        System.out.println("item " + (j + 1) + ",");
                    }
                }
                System.out.println("There were " + nodesVisited + " nodes visited.");*/
                //System.out.println();
        
                resetVariables();
            }
        }

        System.out.println("no fit: \n" + nf);
        System.out.println("some fit: \n" + sf);
        System.out.println("all fit: \n" + af);
        
    }

    public static void knapsack(int i, int[][] set, int profit, int weight, int W){
        nodesVisited++;
        int[] p = set[0];
        int[] w = set[1];

        if (weight <= W && profit > maxprofit){
            maxprofit = profit;
            numbest = i;
            for (int x = 0; x < include.length; x++){
                bestset[x] = include[x];
            }
        }

        if(promising(i, set, profit, weight, W)){
            include[i + 1] = true;
            knapsack(i + 1, set, profit + p[i + 1], weight + w[i + 1], W);
            include[i + 1] = false;
            knapsack(i + 1, set, profit, weight, W);
        }
    }

    public static boolean promising(int i, int[][] set, int profit, int weight, int W){
        int[] p = set[0];
        int[] w = set[1];
        int j = 0;
        int k = 0;
        int totweight = 0;
        float bound = 0;

        if (weight >= W){
            return false;
        } else {
            j = i + 1;
            bound = profit;
            totweight = weight;
            while (j <= n && totweight + w[j] <= W){
                totweight = totweight + w[j];
                bound = bound + p[j];
                j++;
            }
            k = j;
            if (k <= n){
                bound = bound + (W - totweight) * p[k]/w[k];
            }
            
            return bound > maxprofit;
        }
    }

    public static void resetVariables(){
        numbest = 0;
        maxprofit = 0;
        nodesVisited = 0;
    }

    
}