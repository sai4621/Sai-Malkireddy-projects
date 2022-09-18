//Author: Sai Malkireddy

public class QueueUnderflowException extends Exception {
    public QueueUnderflowException() {
        this("Queue is empty"); // call superclass constructor
    }

    public QueueUnderflowException(String message) {
        super(message); // call superclass constructor
    }
}
