//Author: Sai Malkireddy
public class StackUnderflowException extends Exception {

    public StackUnderflowException() {
        this("Stack is empty"); // call superclass constructor
    }

    public StackUnderflowException(String message) {
        super(message); // call superclass constructor
    }
}
