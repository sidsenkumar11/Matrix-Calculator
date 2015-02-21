/**
 * VectorIndexOutOfBoundsException
 *
 * @author Siddarth Senthilkumar
 * @version 1.3
 */
public class VectorIndexOutOfBoundsException extends IndexOutOfBoundsException {

    /**
     * Constructs an VectorIndexOutOfBoundsException with
     * null as its error message string.
     */
    public VectorIndexOutOfBoundsException() { }

    /**
     * Constructs a VectorIndexOutOfBoundsException,
     * saving a reference to the error message
     * string s for later retrieval by the getMessage method.
     * @param message The message to be displayed by the getMessage() method
     */
    public VectorIndexOutOfBoundsException(String message) {
        super(message);
    }
}