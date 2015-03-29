import java.math.BigDecimal;
import java.util.ArrayList;
/**
 * Iterative methods for Ax = b.
 *
 * @author Katherine Cabezas
 * @version 1.0
 */
public class IterativeMethods {

    /**
     * Method for Jacobi iteration.
     * @param a Matrix n x n
     * @param y Vector n x 1
     * @param x Initial guess vector
     * @param tol Error tolerance number
     * @return number of iterations required to reach tolerance
     */
    public static int jacobi(Matrix a, Vector y, Vector x, double tol) {
        int iterations = 0;
        int difference = tol + 1;
        ArrayList<Vector> xVectors;

        /*
        x.i = (1/a.ii)(y.i - a.i2*x.2 - ... - a.ii-1*x.i-1)
        Steps:
        1) iterate through y and add each element to new xVector
           x.i = y.i
        2) iterate through each element in
           x.i = y.i - a.i2*x.2 - ... - a.ii-1*x.i-1)
           x.i = (1/a.ii) * x.i
        */

        int index = 0;
        Vector xv;

        while(!(difference < tol)) {
            xv = new Vector(y.rows())
            xVectors.add(xv);
            for (int i = 0; i < )
        }

        return iterations;
    }

    /**
     * Method for Gauss-Seidel iteration.
     * @param a Matrix n x n
     * @param y Vector n x 1
     * @param x Initial guess vector
     * @param tol Error tolerance number
     * @return number of iterations required to reach tolerance
     */
    public static int gauss_seidel(Matrix a, Vector y, Vector x, double tol) {
        int iterations = 0;

        return iterations;
    }

}
