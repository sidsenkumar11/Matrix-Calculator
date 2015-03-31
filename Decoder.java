/**
 * Program to decode convolutional code word for
 * binary data streams.
 * @author Katherine Cabezas
 * @version 1.0
 */
public class Decoder {

    private static final int MAX_ITER = 100;

    // public static Vector[] splitY(String[] code) {
    //
    // }

    /**
     * Method for Jacobi iteration. Returns the x vector reached
     * when || x.n - x.n-1 || < tolerance.
     * @param a Matrix n x n
     * @param y Vector n x 1
     * @param x Initial guess vector
     * @param tol Error tolerance number
     * @return x vector
     * @throws RuntimeException if iterations > MAX_ITER
     */
    public static Vector j_decode(Matrix a, Vector y, Vector x, double tol) {
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

        return xVector;
    }

    /**
     * Method for Gauss-Seidel iteration. Returns the x vector reached
     * when || x.n - x.n-1 || < tolerance.
     * @param a Matrix n x n
     * @param y Vector n x 1
     * @param x Initial guess vector
     * @param tol Error tolerance number
     * @return x vector
     * @throws RuntimeException if iterations > MAX_ITER
     */
    public static Vector gs_decode(Matrix a, Vector y, Vector x, double tol) {
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

        return xVector;
    }

}
