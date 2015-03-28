import java.util.Random;

public class Encoder {

    private int[] x;

    /**
     * Constructor for Encoder.
     */
    public Encoder(int length) {
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
            } else if ((i - 3) < 0) {
                y0[i] = stream[i] + stream[i - 2];
            } else {
                y0[i] = stream[i] + stream[i - 2] + stream[i - 3];
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
            } else if ((i - 3) < 0) {
                y1[i] = stream[i] + stream[i - 1];
            } else {
                y1[i] = stream[i] + stream[i - 1] + stream[i - 3];
            }
            y1[i] = y1[i] % 2;
        }
        return y1;
    }

    /**
     * Method to create convolutional code word y obtained.
     * @param stream Binary data stream
     * @return convolutional code word y
     */
    public int[] encode(int[] stream) {
        int[] y0 = calculateY0(stream);
        int[] y1 = calculateY1(stream);
        int[] y = new int[stream.length];

        for (int i = 0; i < stream.length; i++) {
            String yValue = Integer.toString(y0[i]) + Integer.toString(y1[i]);
            y[i] = Integer.parseInt(yValue);
        }

        return y;
    }

    //for testing purposes
    public static void main(String[] args) {
        Encoder encoder = new Encoder(8);
        int[] stream = encoder.getX();

        for (int i : stream) {
            System.out.print(i+ " ");
        }

        System.out.println();

        int[] y0 = encoder.calculateY0(stream);
        for (int i: y0) {
            System.out.print(i+ " ");
        }

        System.out.println();

        int[] y1 = encoder.calculateY1(stream);
        for (int i: y1) {
            System.out.print(i+ " ");
        }

        System.out.println();

        int[] y = encoder.encode(stream);
        for (int i: y) {
            System.out.print(i+ " ");
        }

        System.out.println();
    }
}
