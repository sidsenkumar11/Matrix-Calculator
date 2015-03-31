import java.util.ArrayList;
/**
 * Iterative methods for Ax = b.
 *
 * @author Katherine Cabezas
 * @version 1.0
 */
public class IterativeMethods {

    private static final int MAX_ITER = 50;

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
        double difference = tol + 1;
        ArrayList<Vector> xVectors = new ArrayList<Vector>();
        Vector xVector = new Vector(x.rows());
        Vector oldXVector = new Vector(x.rows());
        double sum;

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

        for (int i = 0; i < oldXVector.rows(); i++) {
            oldXVector.set(i, x.get(i));
        }

        while(!(difference < tol)) {
            if (iterations > MAX_ITER) {
                throw new IllegalArgumentException();
            }

            iterations++;

            for (int i = 0; i < oldXVector.rows(); i++) {
                oldXVector.set(i, xVector.get(i));
            }

            for (int i = 0; i < a.rows(); i++) {
                sum = y.get(i);
                for (int j = 0; j < a.columns(); j++) {
                    if (i != j) {
                        sum -= a.get(i, j) * oldXVector.get(j);
                    }
                }
                sum = sum / a.get(i, i);

                xVector.set(i, sum);
            }

            if (iterations > 1) {
                difference = xVector.get(0) - oldXVector.get(0);
                difference = Math.abs(difference);
            }

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
        double difference = tol + 1;
        ArrayList<Vector> xVectors = new ArrayList<Vector>();
        Vector xVector = new Vector(x.rows());
        Vector oldXVector = new Vector(x.rows());
        double sum;

        /*
        xVector.i = (1/a.ii)(y.i - a.i2*x.2 - ... - a.ii-1*x.i-1)
        Steps:
        1) iterate through y and add each element to new xVector
           xVector.i = y.i
        2) iterate through each element in A. Each time you iterate,
           use the new found x.i from the last iteration.
           xVector.i = y.i - a.i2*x.2 - ... - a.ii-1*x.i-1)
           xVector.i = (1/a.ii) * x.i
        3) add xVector to ArrayList of xVectors
        4) see if xVector.get(i + 1) - xVector.get(i) is less than tol
           **if not, do it again
        */

        for (int i = 0; i < oldXVector.rows(); i++) {
            oldXVector.set(i, x.get(i));
        }

        while(!(difference < tol)) {
            if (iterations > MAX_ITER) {
                throw new IllegalArgumentException();
            }

            iterations++;

            for (int i = 0; i < oldXVector.rows(); i++) {
                oldXVector.set(i, xVector.get(i));
            }

            for (int i = 0; i < a.rows(); i++) {
                sum = y.get(i);
                for (int j = 0; j < a.columns(); j++) {
                    if (i != j) {
                        sum -= a.get(i, j) * x.get(j);
                    }
                }
                sum = sum / a.get(i, i);

                xVector.set(i, sum);
                x.set(i, sum);
            }

            if (iterations > 1) {
                difference = xVector.get(0) - oldXVector.get(0);
                difference = Math.abs(difference);
            }

            for (int i = 0; i < x.rows(); i++) {
                x.set(i, xVector.get(i));
            }
        }

        return iterations;
    }

    //for testing purposes
    public static void main(String[] args) {
        double[][] aMatrix = {
            { 5, -2, 3 },
            { -3, 9, 1 },
            { 2, -1, -7 }
        };
        double[] yValues = { -1, 2, 3 };
        double[] xValues = { 0, 0, 0 };

        Matrix a = new Matrix(3, 3);
        Vector y = new Vector(3);
        Vector x = new Vector(3);
        double tolerance = Math.pow(10, -4);

        for (int i = 0; i < a.rows(); i++) {
            for (int j = 0; j < a.columns(); j++) {
                a.set(i, j, aMatrix[i][j]);
            }
        }

        for (int i = 0; i < y.rows(); i++) {
            y.set(i, yValues[i]);
        }

        for (int i = 0; i < x.rows(); i++) {
            x.set(i, xValues[i]);
        }

        System.out.println("Jacobi:");
        int iterations = jacobi(a, y, x, tolerance);
        System.out.println("Number of iterations: " + iterations);

        System.out.println("\nGauss-Seidel:");
        int iterations2 = gauss_seidel(a, y, x, tolerance);
        System.out.println("Number of iterations: " + iterations2);

    }

}
