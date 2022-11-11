

public class NodeQueue extends Queue {
    private LinkedList list; // We'll use a LinkedList to enqueue/dequeue nodes to/from

    public NodeQueue() {
        this.list = new LinkedList();
    }

    @Override
    public int getSize() {

        return this.list.getSize(); // temporary return value so the IDE doesn't complain
    }

    @Override
    public void enqueue(World w) {
        this.list.addLast(w);
    }

    @Override
    public World dequeue() {

        return this.list.removeFirst(); // temporary return value so the IDE doesn't complain
    }

    @Override
    public World peek() {

        return this.list.getHead(); // temporary return value so the IDE doesn't complain
    }

    @Override
    public String toString() {
        return "NodeQueue{" +
                "list=" + list +
                '}';
    }
    @Override
    public int getIndice(World w){
        return list.checkAll(w);

    }

    @Override
    public World check(int i){
        return list.check(i);
    }


}
