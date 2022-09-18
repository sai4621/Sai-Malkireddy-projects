//Author: Sai Malkireddy
public class StackOverflowException extends Exception { 

    public StackOverflowException() {
        this("Stack is full"); // call superclass constructor
    }

    public StackOverflowException(String message) {
        super(message); // call superclass constructor
    }


}
