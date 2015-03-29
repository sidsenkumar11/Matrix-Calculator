import java.math.BigDecimal;
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
        int difference = 1;
        Matrix aXEqualsY = new Matrix(a.rows(), a.columns() + 1);

        for (int i = 0; i < aXEqualsY.rows(); i++) {
            for (int j = 0; j < aXEqualsY.columns() - 1; j++) {
                aXEqualsY.set(i, j, a.get(i, j));
            }
        }
        for (int i = 0; i < aXEqualsY.rows(); i++) {
            aXEqualsY.set(i, aXEqualsY.columns() - 1, y.get(i));
        }

        // while(!(difference < tol)) {
        //
        // }

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
