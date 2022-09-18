public class QueueOverflowException extends Exception {
    public QueueOverflowException() {
        this("Queue is full");
    }

    public QueueOverflowException(String message) {
        super(message);
    }
}
