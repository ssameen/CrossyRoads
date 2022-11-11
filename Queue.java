/**
 * An abstract class that defines what it means to be a Queue.
 * Since this class is abstract, you'll need to use derived
 * subclasses if you actually want a real queue!
 */
public abstract class Queue {

    // A concrete method that will work for any derived queues
    public boolean isEmpty() { return getSize() == 0; }

    /**
     * Some abstract methods that will depend on the specific queue implementation.
     * Functionality for these four methods *must* be provided in subclasses.
     */
    public abstract int getSize();
    public abstract void enqueue(World w);
    public abstract World dequeue();
    public abstract Object peek();
    public abstract int getIndice(World w);

    public abstract World check(int i);
}
