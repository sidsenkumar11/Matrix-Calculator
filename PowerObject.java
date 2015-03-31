/**
 * A representation of a given power_method call.
 * 
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class PowerObject {

	private double approximatedEigenvalue;
	private Vector approximatedEigenvector;
	private int numIterations = 0;
	private boolean converges = true;

	/**
	 * Constructs a power method object.
	 */
	public PowerObject(double approximatedEigenvalue, Vector approximatedEigenvector) {
		this.approximatedEigenvalue = approximatedEigenvalue;
		this.approximatedEigenvector = approximatedEigenvector;
	}

	public boolean converges() {
		return converges;
	}

	public double getEigenvalue() {
		if (converges) {
			return approximatedEigenvalue;
		} else {
			return 0;
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

	public void setEigenvalue(double value) {
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
			String originalEigenvalueToString = "" + approximatedEigenvalue;
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