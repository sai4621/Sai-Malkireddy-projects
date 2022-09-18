//Author: Sai Malkireddy
import java.util.ArrayList;
import java.util.Arrays;

public class NotationStack<T> implements StackInterface<T> {
    private T[] stack;
    private int top;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 50;

    /**
     * Provide two constructors
     * 1. takes in an int as the size of the stack
     * 2. default constructor - uses default as the size of the stack
     */

    public NotationStack() {
        this(DEFAULT_CAPACITY); // call the other constructor
    }

    public NotationStack(int size) {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[size]; // create a new array of the specified size
        stack = tempStack; // set the stack to the new array
        top = -1;
        capacity = size; // set the capacity to the size of the stack
    }

    /**
     * Determines if Stack is empty
     *
     * @return true if Stack is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return top < 0; // if the top is less than zero, then the stack is empty
    }

    /**
     * Determines if Stack is full
     *
     * @return true if Stack is full, false if not
     */
    @Override
    public boolean isFull() {
        return top == capacity - 1; // if the top is one less than the capacity, then the stack is full
    }

    /**
     * Deletes and returns the element at the top of the Stack
     *
     * @return the element at the top of the Stack
     */
    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) { 
            throw new StackUnderflowException(); // if the stack is empty, throw an exception
        } else {
            T temp = stack[top]; // set the temp to the top of the stack
            stack[top] = null; // set the top of the stack to null
            top--; // decrement the top
            return temp;
        }
    }

    /**
     * Returns the element at the top of the Stack, does not pop it off the Stack
     *
     * @return the element at the top of the Stack
     */
    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException(); // if the stack is empty, throw an exception
        } else {
            return stack[top]; // return the top of the stack
        }
    }

    /**
     * Number of elements in the Stack
     *
     * @return the number of elements in the Stack
     */
    @Override
    public int size() {
        return top + 1; // return the top plus one
    }

    /**
     * Adds an element to the top of the Stack
     *
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException();
        } else {
            top++; // increment the top
            stack[top] = e; // set the top of the stack to the element
            return true;
        }
    }

    public String toString() {
        String finalStr = "";
        for (int i = 0; i < size(); i++) {
            finalStr += stack[i].toString(); // add the element to the string as a string
        }
        return finalStr; // return the string
    }

    /**
     * Returns the string representation of the elements in the Stack, the beginning of the
     * string is the bottom of the stack
     * Place the delimiter between all elements of the Stack
     *
     * @param delimiter
     * @return string representation of the Stack from bottom to top with elements
     * separated with the delimiter
     */
    @Override
    public String toString(String delimiter) {
        String finalStr = "";
        for (int i = 0; i < size(); i++) {
            finalStr += stack[i].toString(); // add the element to the string as a string
            if (i != size() - 1) {
                finalStr += delimiter; // add the delimiter if it is not the last element
            }
        }
        return finalStr; // return the string
    }

    /**
     * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
     * is the first bottom element of the Stack
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
     * list reference within your Stack, you will be allowing direct access to the data of
     * your Stack causing a possible security breech.
     *
     * @param list elements to be added to the Stack from bottom to top
     */
    @Override
    public void fill(ArrayList<T> list) {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[list.size()]; // create a new array of the size of the list
        for (int i = 0; i < list.size(); i++) {
            tempStack[i] = list.get(i); // set the element to the list element
        }
        stack = tempStack; // set the stack to the new array
        top = list.size() - 1; // set the top to the size of the list minus one
    }

    private void ensureCapacity(){
        if (isFull()) stack = Arrays.copyOf(stack, capacity * 2); // if the stack is full, double the capacity
    }
}
