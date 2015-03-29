import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * A representation of a given power_method call.
 * 
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class PowerObject {

	private BigDecimal approximatedEigenvalue;
	private Vector approximatedEigenvector;
	private int numIterations = 0;
	private boolean converges = true;
	public static final int MAX_ITERATIONS = 100;

	/**
	 * Implements power method to find approximation
	 * of largest eigenvalue and corresponding eigenvector
	 * @param a The nxn matrix
	 * @param tol The error tolerance
	 * @param u An initial approximation vector
	 * @return The approximated eigenvalue, eigenvector,
	 *		   and number of iterations for desired tolerance
	 */
	public static PowerObject power_method(Matrix a, BigDecimal tol, Vector u) {

		// Get largest value from u - initial eigenvalue guess
		BigDecimal largest = u.get(0);
		for (int i = 1; i < u.numElements(); i++) {
			if (u.get(i).abs().compareTo(largest.abs()) > 0) {
				largest = u.get(i);
			}
		}

		BigDecimal previous = largest;

		// Factor out that value from u
		u = MatrixCalculator.multiply(u, BigDecimal.ONE.divide(largest, 20, RoundingMode.HALF_UP));
		PowerObject powerInfo = new PowerObject(largest, u);

		// Do one iteration - necessary for recursive method to do check on previous eigenvalue

		// Get Au
		Vector au = MatrixCalculator.multiply(a, u);

		// Get largest in Au
		largest = au.get(0);
		for (int i = 1; i < au.numElements(); i++) {
			if (au.get(i).abs().compareTo(largest.abs()) > 0) {
				largest = au.get(i);
			}
		}

		// u = Au / ||largest in Au||
		u = MatrixCalculator.multiply(au, BigDecimal.ONE.divide(largest, 20, RoundingMode.HALF_UP));

		// Update values in powerInfo
		powerInfo.setEigenvalue(largest);
		powerInfo.setEigenvector(u);
		powerInfo.incrementIterations();

		// Keep updating powerInfo and u until tolerance is fine
		u = power_method(a, tol, u, previous, powerInfo);
		return powerInfo;
	}

	/**
	 * Recursively calculates new eigenvector with power method
	 * @param a The nxn matrix
	 * @param tol The error tolerance
	 * @param u An initial approximation vector
	 * @param prev The previous eigenvalue
	 * @param powerInfo The PowerObject containing return data
	 * @return The approximated eigenvalue, eigenvector,
	 *		   and number of iterations for desired tolerance
	 */
	private static Vector power_method(Matrix a, BigDecimal tol, Vector u, BigDecimal prev, PowerObject powerInfo) {

		if (powerInfo.getIterations() >= MAX_ITERATIONS) {
			// If after MAX_ITERATIONS iterations, the error has not been reduced to tol or less,
			// this matrix does not converge.
			powerInfo.setConverges(false);
			return u;
		}

		BigDecimal thisPrevious = powerInfo.getEigenvalue();
		if (thisPrevious.abs().subtract(prev.abs()).abs().compareTo(tol) <= 0) {
			// Difference between current largest eigenvalue and previous eigenvalue is less than or equal to tolerance
			// We are done iterating.
			return u;
		}

		// Tolerance not enough. need another iteration
		Vector au = MatrixCalculator.multiply(a, u);

		// Get au's largest
		BigDecimal largest = au.get(0);
		for (int i = 1; i < au.numElements(); i++) {
			if (au.get(i).abs().compareTo(largest.abs()) > 0) {
				largest = au.get(i);
			}
		}

		// Divide au by its largest and set it to u
		u = MatrixCalculator.multiply(au, BigDecimal.ONE.divide(largest, 20, RoundingMode.HALF_UP));

		// Update powerInfo
		powerInfo.setEigenvalue(largest);
		powerInfo.setEigenvector(u);
		powerInfo.incrementIterations();

		// Check again if tolerance is fine
		return power_method(a, tol, u, thisPrevious, powerInfo);
	}

	/**
	 * Constructs a power method object.
	 */
	public PowerObject(BigDecimal approximatedEigenvalue, Vector approximatedEigenvector) {
		this.approximatedEigenvalue = approximatedEigenvalue;
		this.approximatedEigenvector = approximatedEigenvector;
	}

	public boolean converges() {
		return converges;
	}

	public BigDecimal getEigenvalue() {
		if (converges) {
			return approximatedEigenvalue;
		} else {
			return null;
		}
	}

	public Vector getEigenvector() {
		if (converges) {
			return approximatedEigenvector;
		} else {
			return null;
		}
	}

	public int getIterations() {
		return numIterations;
	}

	public void setConverges(boolean converges) {
		this.converges = converges;
	}

	public void setEigenvalue(BigDecimal value) {
		this.approximatedEigenvalue = value;
	}

	public void setEigenvector(Vector vector) {
		this.approximatedEigenvector = vector;
	}

	public void setIterations(int iterations) {
		this.numIterations = iterations;
	}

	public void incrementIterations() {
		this.numIterations++;
	}

	@Override
	public String toString() {
		if (converges) {
			// At most keep 20 characters worth of eigenvalue
			String originalEigenvalueToString = approximatedEigenvalue.stripTrailingZeros().toString();
			String desiredPortionEigenvalueToString = "";
			for (int i = 0; i < originalEigenvalueToString.length() && i <= 20; i++) {
				desiredPortionEigenvalueToString += originalEigenvalueToString.substring(i, i+1);
			}

			String returnString = "";
			returnString += "Eigenvalue:  " + desiredPortionEigenvalueToString + "\n";
			returnString += "Eigenvector: " + approximatedEigenvector.toString() + "\n";
			returnString += "Iterations:  " + numIterations;
			return returnString;
		}
		return "This matrix does not converge.";
	}
}