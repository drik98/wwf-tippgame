
package Exceptions;

/**
 *
 * @author hendr
 */
public class InvalidTippException extends Exception {

    // Parameterless Constructor
    public InvalidTippException() {
    }

    // Constructor that accepts a message
    public InvalidTippException(String message) {
        super(message);
    }
    

}
