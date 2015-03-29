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
        Matrix yMinusA = new Matrix(a.rows(), a.columns() + 1);
        ArrayList<Vector> xVectors;

        /*
        Still working on translating the concept to code.
        */
        for (int i = 0; i < yMinusA.rows(); i++) {
            aXEqualsY.set(i, 0, y.get(i));
        }
        for (int i = 0; i < yMinusA.rows(); i++) {
            for (int j = 1; j < yMinusA.columns(); j++) {
                yMinusA.set(i, j, a.get(i, j).multiply(-1));
            }
        }

        int index = 0;
        Vector xVec = new Vector(yMinusA.rows());
        while(!(difference < tol)) {

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
