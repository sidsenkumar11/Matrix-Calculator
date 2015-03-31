import java.util.Random;
/**
 * Program to create convolutional code word for
 * binary data streams.
 * @author Katherine Cabezas
 * @version 1.0
 */
public class ConvolCode {

    private int[] x;
    private Matrix matrixA0;
    private Matrix matrixA1;

    /**
     * Constructor for Encoder.
     */
    public ConvolCode(int length) {
        matrixA0 = new Matrix(length, 3);
        matrixA1 = new Matrix(length, 3);
        x = new int[length];
        Random randNumList = new Random();

        for (int i = 0; i < length; i++) {
            x[i] = randNumList.nextInt(2);
        }
    }

    /**
     * Returns x binary data stream.
     * @return x
     */
    public int[] getX() {
        return x;
    }

    /**
     * Calculates the ouput stream y0.
     * @param stream Binary data stream to use for calculations
     * @return output stream y0
     */
    public int[] calculateY0(int[] stream) {
        int[] y0 = new int[stream.length];
        for (int i = 0; i < y0.length; i++) {
            if ((i - 2) < 0) {
                y0[i] = stream[i];
                matrixA0.set(i, 0, stream[i]);
                matrixA0.set(i, 1, 0);
                matrixA0.set(i, 2, 0);
            } else if ((i - 3) < 0) {
                y0[i] = stream[i] + stream[i - 2];
                matrixA0.set(i, 0, stream[i]);
                matrixA0.set(i, 1, stream[i - 2]);
                matrixA0.set(i, 2, 0);
            } else {
                y0[i] = stream[i] + stream[i - 2] + stream[i - 3];
                matrixA0.set(i, 0, stream[i]);
                matrixA0.set(i, 1, stream[i - 2]);
                matrixA0.set(i, 2, stream[i - 3]);
            }
            y0[i] = y0[i] % 2;
        }
        return y0;
    }

    /**
     * Calculates the output stream y1.
     * @param stream Binary data stream to use for calculations
     * @return output stream y1
     */
    public int[] calculateY1(int[] stream) {
        int[] y1 = new int[stream.length];
        for (int i = 0; i < y1.length; i++) {
            if ((i - 1) < 0) {
                y1[i] = stream[i];
                matrixA1.set(i, 0, stream[i]);
                matrixA1.set(i, 1, 0);
                matrixA1.set(i, 2, 0);
            } else if ((i - 3) < 0) {
                y1[i] = stream[i] + stream[i - 1];
                matrixA1.set(i, 0, stream[i]);
                matrixA1.set(i, 1, stream[i - 1]);
                matrixA1.set(i, 2, 0);
            } else {
                y1[i] = stream[i] + stream[i - 1] + stream[i - 3];
                matrixA1.set(i, 0, stream[i]);
                matrixA1.set(i, 1, stream[i - 1]);
                matrixA1.set(i, 2, stream[i - 3]);
            }
            y1[i] = y1[i] % 2;
        }
        return y1;
    }

    public Matrix getMatrixA0() {
        return matrixA0;
    }

    public Matrix getMatrixA1() {
        return matrixA1;
    }

    /**
     * Method to create convolutional code word y obtained.
     * @param stream Binary data stream
     * @return convolutional code word y
     */
    public String[] encode(int[] stream) {
        int[] y0 = calculateY0(stream);
        int[] y1 = calculateY1(stream);
        String[] y = new String[stream.length];

        for (int i = 0; i < stream.length; i++) {
            if (y0[i] == 1 && y1[i] == 1) {
                y[i] = "11";
            } else if (y0[i] == 0 && y1[i] == 1) {
                y[i] = "01";
            } else if (y0[i] == 1 && y1[i] == 0) {
                y[i] = "10";
            } else {
                y[i] = "00";
            }
        }

        return y;
    }

    //for testing purposes
    public static void main(String[] args) {
        ConvolCode coder = new ConvolCode(8);
        int[] stream = coder.getX();

        for (int i : stream) {
            System.out.print(i+ " ");
        }

        System.out.println();

        int[] y0 = coder.calculateY0(stream);
        for (int i: y0) {
            System.out.print(i + " ");
        }

        System.out.println();

        int[] y1 = coder.calculateY1(stream);
        for (int i: y1) {
            System.out.print(i + " ");
        }

        System.out.println();

        String[] y = coder.encode(stream);
        for (String s: y) {
            System.out.print(s + " ");
        }

        System.out.println();
    }
}
