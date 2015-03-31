/**
 * Class for the Jacobi iteration method.
 *
 * @author Katherine Cabezas
 * @version 2.0
 */
public class jacobi {

    private static final int MAX_ITER = 100;

    /**
     * Method for Jacobi iteration. Returns the number of iterations
     * made after || x.n - x.n-1 || < tolerance.
     * @param a Matrix n x n
     * @param y Vector n x 1
     * @param x Initial guess vector
     * @param tol Error tolerance number
     * @return number of iterations required to reach tolerance
     * @throws RuntimeException if iterations > MAX_ITER
     */
    public static int jacobi(Matrix a, Vector y, Vector x, double tol) {
        int iterations = 0;
        double difference = tol + 1;
        Vector xVector = new Vector(x.rows());
        Vector oldXVector = new Vector(x.rows());
        double sum;

        for (int i = 0; i < oldXVector.rows(); i++) {
            oldXVector.set(i, x.get(i));
        }

        while(!(difference < tol)) {

            iterations++;

            if (iterations > MAX_ITER) {
                throw new RuntimeException("Doesn't converge after 100 iterations.");
            }

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

}
