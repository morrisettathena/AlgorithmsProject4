import java.util.*;

public class Node implements Comparable<Node> {

    public int level;
    public int profit;
    public int weight;
    public double bound;
    public boolean[] included;

    public Node() {
        this.level = 0;
        this.profit = 0;
        this.weight = 0;
        this.bound = 0;
    }

    public Node(int level, int profit, int weight, double bound) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.bound = bound;
    }

    @Override
    public int compareTo(Node o) {
        if (Math.abs(this.bound - o.bound) < 0.000001)
        return 0;
        else if (this.bound > o.bound)
            return -1;
        else
            return 1;
        
    }

    @Override
    public String toString() {
        String v = String.format("%f", this.bound);
        return v;
    }
}