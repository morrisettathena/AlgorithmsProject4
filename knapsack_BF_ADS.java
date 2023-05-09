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


