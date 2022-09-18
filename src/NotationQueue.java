// Author: Sai Malkireddy

import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {

    private T[] queue;
    private int frontIndex; // index of the front of the queue
    private int backIndex; // index of the back of the queue
    private static final int DEFAULT_CAPACITY = 50; // default capacity of the queue
    private static final int MAX_CAPACITY = 10000;

    /** provide two constructors
     * 1. takes an int as the size of the queue
     * 2. default constructor - uses a default as the size of the queue
     *
     */

    public NotationQueue() {
        this(DEFAULT_CAPACITY);
    } // end default constructor


    public NotationQueue(int size) {
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[size + 1]; // create a new array of the specified size or default size plus one
        queue = tempQueue;
        frontIndex = 0;
        backIndex = size; // backIndex is one less than the size of the queue
    }

    /**
     * Determines if Queue is empty
     *
     * @return true if Queue is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return frontIndex == ((backIndex + 1) % queue.length); // if the front index is one less than the back index, then the queue is empty
    }

    /**
     * Determines of the Queue is full
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return frontIndex == ((backIndex + 2) % queue.length); // if the front index is two less than the back index, then the queue is full
    }

    /**
     * Deletes and returns the element at the front of the Queue
     *
     * @return the element at the front of the Queue
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException(); // if the queue is empty, throw an exception
        } else {
            T front = queue[frontIndex]; // get the front element
            queue[frontIndex] = null; // set the front element to null
            frontIndex = (frontIndex + 1) % queue.length; // set the front index to one less than the front index
            return front; // return the front element
        }
    }

    /**
     * Number of elements in the Queue
     *
     * @return the number of elements in the Queue
     */
    @Override
    public int size() {
        return (backIndex - frontIndex + queue.length) % queue.length + 1; // return the number of elements in the queue
    }

    /**
     * Adds an element to the end of the Queue
     *
     * @param e the element to add to the end of the Queue
     * @return true if the add was successful, false if not
     */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException(); // if the queue is full, throw an exception
        } else {
            backIndex = (backIndex + 1) % queue.length; // set the back index to one less than the back index
            queue[backIndex] = e; // set the back element to the element
            return true;
        }
    }

    public String toString() {
        String finalStr = "";

        for (int i = frontIndex; i != backIndex; i = (i + 1) % queue.length) {
            finalStr += queue[i].toString(); // convert the element to a string and add it to the string
        }

        finalStr += queue[backIndex].toString(); // convert the back element to a string and add it to the string
        return finalStr;
    }

    /**
     * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
     * Place the delimiter between all elements of the Queue
     *
     * @param delimiter
     * @return string representation of the Queue with elements separated with the delimiter
     */
    @Override
    public String toString(String delimiter) {
        String finalStr = "";

        for (int i = frontIndex; i != backIndex; i = (i + 1) % queue.length) {
            finalStr += queue[i].toString() + delimiter; // convert the element to a string and add it to the string along with the delimiter
        }

        finalStr += queue[backIndex].toString(); // convert the back element to a string and add it to the string
        return finalStr;
    }

    /**
     * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
     * is the first element in the Queue
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
     * list reference within your Queue, you will be allowing direct access to the data of
     * your Queue causing a possible security breech.
     *
     * @param list elements to be added to the Queue
     */
    @Override
    public void fill(ArrayList<T> list) {
        ArrayList<T> tempList = new ArrayList<T>(list); // create a new ArrayList from the list passed in
        for (T e : tempList) {
            //ensureCapacity(); // ensure the capacity of the queue is large enough to add the element
            backIndex = (backIndex + 1) % queue.length; // set the back index to one less than the back index
            queue[backIndex] = e; // set the back element to the element
        }
    }

    private void ensureCapacity(){
        if(isFull()){
            T[] tempQueue = (T[]) new Object[queue.length * 2];
            for(int i = 0; i < queue.length; i++){
                tempQueue[i] = queue[i];
            }
            queue = tempQueue;
            frontIndex = 0;
            backIndex = queue.length - 1;
        }
    }
}
