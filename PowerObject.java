import java.math.BigDecimal;

/**
 * A representation of a given power_method call.
 * 
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class PowerObject {
	private BigDecimal approximatedEigenvalue;
	private Vector approximatedEigenvector;
	private int numIterations;
	private boolean converges;

	/**
	 * Constructs a power method object.
	 */
	public PowerObject(BigDecimal approximatedEigenvalue, Vector approximatedEigenvector, int numIterations, boolean converges) {
		this.approximatedEigenvalue = approximatedEigenvalue;
		this.approximatedEigenvector = approximatedEigenvector;
		this.numIterations = numIterations;
		this.converges = converges;
	}

	public boolean converges() {
		return converges;
	}

	public BigDecimal getEigenvalue() {
		return approximatedEigenvalue;
	}

	public Vector getEigenvector() {
		return approximatedEigenvector;
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