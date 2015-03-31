/**
 * Class for the Gauss-Seidel iteration method.
 *
 * @author Katherine Cabezas
 * @version 2.0
 */
public class gauss_seidel {
    /**
     * Method for Gauss-Seidel iteration.
     * @param a Matrix n x n
     * @param y Vector n x 1
     * @param x Initial guess vector
     * @param tol Error tolerance number
     * @return x vector approximation
     * @throws RuntimeException if iterations > MAX_ITER
     */
    public static Vector gauss_seidel(Matrix a, Vector y, Vector x, double tol) {
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

        System.out.println(iterations + " iterations made.");
        return xVector;
    }
}
