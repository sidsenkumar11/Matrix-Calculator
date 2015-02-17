/**
 * MatrixIndexOutOfBoundsException
 *
 * @author Siddarth Senthilkumar
 * @version 1.3
 */
public class MatrixIndexOutOfBoundsException extends IndexOutOfBoundsException {

    /**
     * Constructs an MatrixIndexOutOfBoundsException with
     * null as its error message string.
     */
    public MatrixIndexOutOfBoundsException() { }

    /**
     * Constructs a MatrixIndexOutOfBoundsException,
     * saving a reference to the error message
     * string s for later retrieval by the getMessage method.
     * @param message The message to be displayed by the getMessage() method
     */
    public MatrixIndexOutOfBoundsException(String message) {
        super(message);
    }
}