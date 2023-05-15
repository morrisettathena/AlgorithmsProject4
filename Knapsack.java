//Author: DJ Trevens

public class Knapsack {
    public static int n = 4;
    public static int numbest = 0;
    public static int maxprofit = 0;
    public static int nodesVisited = 0;
    public static boolean[] bestset = new boolean[n + 1];
    public static boolean[] include = new boolean[n + 1];

    public static void main(String[] args){
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


        knapsack(0, set1, set1[0][0], set1[1][0], set1W);
        System.out.println("Max profit for set 1: " + maxprofit);
        System.out.println("Items for set 1: ");
        for (int j = 0; j < bestset.length; j++){
            if (bestset[j]){
                System.out.println("item " + (j + 1) + ",");
            }
        }
        System.out.println("There were " + nodesVisited + " nodes visited.");
        System.out.println();

        resetVariables();

        knapsack(0, set2, set2[0][0], set2[1][0], set2W);
        System.out.println("Max profit for set 2: " + maxprofit);
        System.out.println("Items for set 2: ");
        for (int j = 0; j < bestset.length; j++){
            if (bestset[j]){
                System.out.println("item " + (j + 1) + ",");
            }
        }
        System.out.println("There were " + nodesVisited + " nodes visited.");
        System.out.println();

        resetVariables();

        knapsack(0, set3, set3[0][0], set3[1][0], set3W);
        System.out.println("Max profit for set 3: " + maxprofit);
        System.out.println("Items for set 3: ");
        for (int j = 0; j < bestset.length; j++){
            if (bestset[j]){
                System.out.println("item " + (j) + ",");
            }
        }
        System.out.println("There were " + nodesVisited + " nodes visited.");
        System.out.println();

        resetVariables();

        knapsack(0, set4, set4[0][0], set4[1][0], set4W);
        System.out.println("Max profit for set 4: " + maxprofit);
        System.out.println("Items for set 4: ");
        for (int j = 0; j < bestset.length; j++){
            if (bestset[j]){
                System.out.println("item " + (j) + ",");
            }
        }
        System.out.println("There were " + nodesVisited + " nodes visited.");
        System.out.println();

        resetVariables();

        knapsack(0, set5, set5[0][0], set5[1][0], set5W);
        System.out.println("Max profit for set 5: " + maxprofit);
        System.out.println("Items for set 5: ");
        for (int j = 0; j < bestset.length; j++){
            if (bestset[j]){
                System.out.println("item " + (j) + ",");
            }
        }
        System.out.println("There were " + nodesVisited + " nodes visited.");
        System.out.println();
        
        resetVariables();
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