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
    public static int jacobi(Matrix a, Vector y, Vector x, BigDecimal tol) {
        int iterations = 0;
        BigDecimal difference = tol.add(BigDecimal.ONE);
        ArrayList<Vector> xVectors = new ArrayList<Vector>();
        Vector xVector;
        int oldIndex = 0;
        int newIndex = 1;

        /*
        xVector.i = (1/a.ii)(y.i - a.i2*x.2 - ... - a.ii-1*x.i-1)
        Steps:
        1) iterate through y and add each element to new xVector
           xVector.i = y.i
        2) iterate through each element in a
           xVector.i = y.i - a.i2*x.2 - ... - a.ii-1*x.i-1)
           xVector.i = (1/a.ii) * x.i
        3) add xVector to ArrayList of xVectors
        4) see if xVector.get(i + 1) - xVector.get(i) is less than tol
           **if not, do it again
        */

        while(!(difference.compareTo(tol) < 0)) {
            xVector = new Vector(y.rows());
            for (int i = 0; i < y.rows(); i++) {
                xVector.set(i, y.get(i));
            }

            BigDecimal sum;
            for (int i = 0; i < a.rows(); i++) {
                sum = xVector.get(i);
                for (int j = 0; j < a.columns(); j++) {
                    if (i == j) {
                        j++;
                    }
                    sum.add(a.get(i, j).multiply(x.get(i)));
                }
                sum.multiply(BigDecimal.ONE.divide(a.get(i, i)));

                xVector.set(i, sum);
                x.set(i, sum);
                xVectors.add(xVector);
            }

            if (xVectors.size() > 1) {
                difference = xVectors.get(oldIndex).get(0).subtract(xVectors.get(newIndex).get(0));
                difference = difference.abs();
            }

            iterations++;
            oldIndex++;
            newIndex++;
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
        BigDecimal difference = tol.add(BigDecimal.ONE);
        ArrayList<Vector> xVectors = new ArrayList<Vector>();
        Vector xVector;
        int oldIndex = 0;
        int newIndex = 1;

        //still need to change this from being for jacobi to gauss-seidel
        while(!(difference.compareTo(tol) < 0)) {
            xVector = new Vector(y.rows());
            for (int i = 0; i < y.rows(); i++) {
                xVector.set(i, y.get(i));
            }

            BigDecimal sum;
            for (int i = 0; i < a.rows(); i++) {
                sum = xVector.get(i);
                for (int j = 0; j < a.columns(); j++) {
                    if (i == j) {
                        j++;
                    }
                    sum.add(a.get(i, j).multiply(x.get(i)));
                }
                sum.multiply(BigDecimal.ONE.divide(a.get(i, i)));

                xVector.set(i, sum);
                x.set(i, sum);
                xVectors.add(xVector);
            }

            if (xVectors.size() > 1) {
                difference = xVectors.get(oldIndex).get(0).subtract(xVectors.get(newIndex).get(0));
                difference = difference.abs();
            }

            iterations++;
            oldIndex++;
            newIndex++;
        }

        return iterations;
    }

}
