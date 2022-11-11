/**
 * (Singly) linked list implementation for containing a sequence of ints
 */
public class LinkedList {
    /**
     * Inner Node class; nodes are linked together in a chain to form the list
     */
    private class Node {
        private Node next; // reference to the next node in the chain
        private World data; // the data stored in this node (int, in this case)
        public Node(World data, Node next) {
            this.next = next;
            this.data = data;
        }
        public World getData() { return data; }
        public Node getNext() { return next; }
        public void setNext(Node next) { this.next = next; }
    }

    private Node head; // reference to the beginning node of the chain
    private Node tail; // reference to the final node of the chain
    private int size; // counter for how many nodes are in the chain

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() { return size; }
    public boolean isEmpty() { return getSize() == 0; }
    public World getHead() { return head.getData(); }
    public World getTail() { return tail.getData(); }
    public void addFirst(World x) {
        Node newHead = new Node(x, head); // insert new node, pointing to the original head
        head = newHead; // update the head of the chain to be the new node
        // special case: for an empty list, the only node is also the tail
        if (isEmpty()) { tail = newHead; }
        size++;
    }
    public void addLast(World x) {
        Node newTail = new Node(x, null); // create a new node, pointing to nothing
        // special case: for an empty list, the only node is also the head
        if (isEmpty()) { head = newTail; }
        // otherwise, update the previous tail to point to our new node
        else { tail.setNext(newTail); }
        tail = newTail;
        size++;
    }
    public World removeFirst() {
        World data = head.getData(); // pull out the data from the head node
        head = head.getNext(); // update the head to point to the next node in the chain
        // special case: we removed the last node! it was both the head and the tail,
        //               so we need to update the tail reference as well as the head
        if (isEmpty()) { tail = null; }
        size--;
        return data;
    }

    public int checkAll(World w){
        int count = 0;
        Node og = head;
        while(size > 0) {
            World data = head.getData(); // pull out the data from the head node
            if(w==data){
                head = og;
                size+=count;
                return count;
            }
            head = head.getNext(); // update the head to point to the next node in the chain
            // special case: we removed the last node! it was both the head and the tail,
            //               so we need to update the tail reference as well as the head
            if (isEmpty()) {
                tail = null;
            }
            size--;
            count++;
        }
        head = og;
        size+=count;
        return count;
    }

    public World check(int i){
        Node og = head;
        World found;
        for(int t = 0; t<i; t++){
            head = head.getNext();

        }
        found = head.getData();
        head = og;
        return found;
    }

    /**
     * Override the toString() method from Object;
     * this is what System.out.println uses!
     */
    public String toString() {
        String s = "nodes:";
        Node curNode = head;
        // loop through the chain until you get to the end (null next node)
        while (curNode != null) {
            s += " ";
            s += curNode.getData();
            curNode = curNode.getNext();
        }
        return s;
    }

    /**
     * Let's hook up a play button here. We won't be testing our whole program from here,
     * but we can use this to check that just our LinkedList is working correctly(??)
     */

}
