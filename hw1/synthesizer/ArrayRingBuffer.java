package synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for peek. */
    private int first;           
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    public T[] rb;

    private int size;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        super(0, capacity);
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(fillCount < capacity) {
            rb[last] = x;
            //Housekeeping
            fillCount++;
            last += 1;
            last = last % capacity;
        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
        size += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
        if(!isEmpty()) {
            T firstItem = rb[first];
            rb[first] = null;

            //houseKeeping
            fillCount--;
            first += 1;
            first = first % capacity;
            size -= 1;
            return firstItem;
        }
        else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    private class DequeArray implements Iterator<T> {
        private int position;
        public DequeArray() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return (position < size);
        }

        @Override
        public T next() {
            T currentThing = rb[position];
            position += 1;
            return currentThing;
        }
    }
    @Override
    // TODO: When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator() {
        return new DequeArray();
    }
}
