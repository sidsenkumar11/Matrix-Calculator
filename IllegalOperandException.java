/**
 * IllegalOperandException
 *
 * @author Siddarth Senthilkumar
 * @version 1.3
 */
public class IllegalOperandException extends Exception {

    /**
     * Constructs an IllegalOperandException with null
     * as its error message string.
     */
    public IllegalOperandException() { }

    /**
     * Constructs an IllegalOperandException,
     * saving a reference to the error message
     * string s for later retrieval by the getMessage method.
     * @param message The message to be displayed by the getMessage() method
     */
    public IllegalOperandException(String message) {
        super(message);
    }
}