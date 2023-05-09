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

class Node{  
    int key;                                    // defines a node
    int level;                                     // info in node
    int profit;
    int weight;
    Node next;                                   // link to next node

    public Node(int key){                        // allows ability to create new nodes
        this.key = key;
        this.next = null;
    }
}                                               // end of node class

class Queue {
    Node front,rear;                                   // creates nodes for LL

    public Queue() {this.front = this.rear = null; }

    void enqueue(int key){
        
        Node temp = new Node(key);  // creates new node

        if(this.rear ==null ){      // if queue is empty then new node is front+rear
            this.front = this.rear = temp;
            return;
        }

        this.rear.next = temp;
        this.rear = temp;

    }

    void dequeue() {
        if(this.front == null) {    // if queue is empty return 
            return;
        }

        Node temp = this.front;     // store previous front
        this.front = this.front.next;   // shift queue foward since a queue is FIFO

        if(this.front == null) {    // if when a node is removed and the next front is null the queue is empty and rear = null
            this.rear = null;
        }
    }   

}   // end of queue class

void knapsack2(int n, int p[],int w[], int W, int maxprofit){

    Queue Q = new Queue();  // creates instance of queue
    Node u,v;

    Q = null;   // initializes Queue
    v.level = 0;    // initializes v as root since all variables are 0
    v.profit = 0;
    v.weight = 0;

    maxprofit = 0;
    Q.enqueue(v);
    while(Q == null){
        Q.dequeue(v);
        u.level = = v.level+1;
        u.weight = v.weight + w[u.level];
        u.profit = v.profit + p[u.level];

        if(u.weight <= W && u.profit > maxprofit) {
            maxprofit = u.profit;
        }
        if(bound(u) > maxprofit) {
            Q.enqueue(u);
        }
        u.weight = v.weight;
        u.profit = v.profit;
        if(bound(u)>maxprofit) {
            Q.enqueue(u);
        }

    }

}

float bound(Node u) {
    int j,k;
    int totweight;
    float result;
    if(u.weight >= W) {
        return 0;
    }
    else {
        result = u.profit;
        j = u.level + 1;
        totweight = u.weight;
        while(j <= n && totweight + w[j] <= W) {
            totweight = totweight + w[j];
            result = result + p[j];
            j++;
        }
        k = j;
        if(k <= n) {
            result = result + (W - totweight) + p[k]
        }

        return result;

    }   // end of while
}       // end of bound class



