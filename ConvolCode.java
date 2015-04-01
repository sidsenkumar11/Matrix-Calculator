import java.util.Random;
import java.util.Scanner;
/**
 * Program to create convolutional code word for
 * binary data streams.
 * @author Katherine Cabezas
 * @version 2.0
 */
public class ConvolCode {

    private double[] x;
    private Matrix matrixA0;
    private Matrix matrixA1;
    private Vector yZero;
    private Vector yOne;

    /**
     * Constructor for Encoder. Randomly generates
     * binary stream x.
     * @param length Desired length for binary stream x
     */

    public ConvolCode(int length) {
        matrixA0 = new Matrix(length, 3);
        matrixA1 = new Matrix(length, 3);
        yZero = new Vector(length);
        yOne = new Vector(length);
        x = new double[length];
        Random randNumList = new Random();

        for (int i = 0; i < length; i++) {
            x[i] = randNumList.nextInt(2);
        }
    }

    /**
     * Constructor for Encoder.
     * @param stream Assigns this array of integers to x
     */
    public ConvolCode(double[] stream) {
        matrixA0 = new Matrix(3, 3);
        matrixA1 = new Matrix(3, 3);
        yZero = new Vector(stream.length);
        yOne = new Vector(stream.length);
        x = stream;
    }

    /**
     * Returns x binary data stream.
     * @return x
     */
    public double[] getX() {
        return x;
    }

    /**
     * Calculates the ouput stream y0.
     * @return output stream y0
     */
    public double[] calculateY0() {
        double[] y0 = new double[x.length];
        for (int i = 0; i < y0.length; i++) {
            if ((i - 2) < 0) {
                y0[i] = x[i];
                matrixA0.set(i, 0, x[i]);
                matrixA0.set(i, 1, 0);
                matrixA0.set(i, 2, 0);
            } else if ((i - 3) < 0) {
                y0[i] = x[i] + x[i - 2];
                matrixA0.set(i, 0, x[i]);
                matrixA0.set(i, 1, x[i - 2]);
                matrixA0.set(i, 2, 0);
            } else {
                y0[i] = x[i] + x[i - 2] + x[i - 3];
                matrixA0.set(i, 0, x[i]);
                matrixA0.set(i, 1, x[i - 2]);
                matrixA0.set(i, 2, x[i - 3]);
            }
            y0[i] = y0[i] % 2;
        }
        yZero = new Vector(y0);
        return y0;
    }

    /**
     * Calculates the output stream y1.
     * @return output stream y1
     */
    public double[] calculateY1() {
        double[] y1 = new double[x.length];
        for (int i = 0; i < y1.length; i++) {
            if ((i - 1) < 0) {
                y1[i] = x[i];
                matrixA1.set(i, 0, x[i]);
                matrixA1.set(i, 1, 0);
                matrixA1.set(i, 2, 0);
            } else if ((i - 3) < 0) {
                y1[i] = x[i] + x[i - 1];
                matrixA1.set(i, 0, x[i]);
                matrixA1.set(i, 1, x[i - 1]);
                matrixA1.set(i, 2, 0);
            } else {
                y1[i] = x[i] + x[i - 1] + x[i - 3];
                matrixA1.set(i, 0, x[i]);
                matrixA1.set(i, 1, x[i - 1]);
                matrixA1.set(i, 2, x[i - 3]);
            }
            y1[i] = y1[i] % 2;
        }
        yOne = new Vector(y1);
        return y1;
    }

    public Vector getY0() {
        return yZero;
    }

    public Vector getY1() {
        return yOne;
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
    public String[] encode() {
        double[] y0 = calculateY0();
        double[] y1 = calculateY1();
        String[] y = new String[x.length];

        for (int i = 0; i < x.length; i++) {
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

    public static void main(String[] args) {
        System.out.println("------------------------------------------------");
        System.out.println("Welcome to the Convolutional Code program!");
        System.out.println("What would you like to do?");
        System.out.println("------------------------------------------------");
        System.out.println("(0) Exit the program");
        System.out.println("(1) Encode");
        System.out.println("(2) Iterative Methods");
        Scanner scan = new Scanner(System.in);
        System.out.print("Input: ");
        int input = -1;
        while (input != 0) {
            input = scan.nextInt();
            if (input == 1) {
                System.out.print("Please enter the desired length n of the stream: ");
                int length = scan.nextInt();
                ConvolCode x = new ConvolCode(length);
                String[] array = x.encode();
                String word = "";
                for (String a : array) {
                    word += a + " ";
                }
                System.out.println("Ecnoded: " + word);
            } else if (input == 2) {

            } else if (input != 0) {
                System.out.println("Please enter an integer between 0 and 2 inclusive");
            }
        }
        System.out.println("Thank you for using our program");
    }
}
