// Author: Sai Malkireddy
public class InvalidNotationFormatException extends Exception {
    public InvalidNotationFormatException() {
        this("Invalid notation format"); // call superclass constructor
    }

    public InvalidNotationFormatException(String message) { 
        super(message); // call superclass constructor
    }
}
