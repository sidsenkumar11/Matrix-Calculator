import java.util.Random;

public class Encoder {

    private double[] xStream;

    /**
     * Constructor for Encoder.
     */
    public Encoder(int length) {
        xStream = new double[length];

        int randNum = 0;
        Random randNumList = new Random();

        for (int i = 0; i < length; i++) {
            xStream[i] = randNumList.nextInt(2);
        }
    }

    /**
     * Returns x binary data stream.
     * @return xStream
     */
    public double[] getXStream() {
        return xStream;
    }

    /**
     * Method to create convolutional code word y obtained by the
     * following equation: 
     * @return convolutional code word y
     */
    public double[] encode() {
        double[] yStream = new double[xStream.length];
    }

    //for testing purposes
    public static void main(String[] args) {
        Encoder encoder = new Encoder(10);
        double[] stream = encoder.getXStream();

        for (double d : stream) {
            System.out.println(d);
        }
    }
}
