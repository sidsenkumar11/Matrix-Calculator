import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

/**
 * Creates the interface for displaying.
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class MCWindow {

	private Scanner scan;

	public MCWindow() {
		this.scan = new Scanner(System.in);
	}

	/**
	 * Read in all Matrix .dat files into matrices
	 */
	public LinkedList[] readMatrices(String[] fileNames) {

		LinkedList<Matrix> matrices = new LinkedList<Matrix>();
		LinkedList<Vector> vectors = new LinkedList<Vector>();

		for (String fileName : fileNames) {
			try {

				FileReader reader = new FileReader(fileName);
				Scanner in = new Scanner(reader);
				LinkedList<String[]> rows = new LinkedList<String[]>();

				// Read in one row at a time into a LinkedList of arrays
				while(in.hasNextLine()) {
					String[] row = in.nextLine().split(" ");
					rows.add(row);
				}

				// Create Matrix or vector
				if (rows.get(0).length == 1) {
					vectors.add(new Vector(rows));
				} else {
					matrices.add(new Matrix(rows));
				}

			} catch(IOException exception) {
				System.out.println("Error processing file: " + exception);
			}
		}

		LinkedList[] returnData;
		
		if (vectors.size() == 0) {
			returnData = new LinkedList[1];
			returnData[0] = matrices;
		} else {
			returnData = new LinkedList[2];
			returnData[0] = matrices;
			returnData[1] = vectors;
		}
		return returnData;
	}

	public void run() {
		int program = -1;
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Welcome to the Matrix Calculator!");
		while (program != 4) {
			program = chooseProgram();
			if (program == 1) {
				runHilbertMatrix();
			} else if (program == 2) {
				runConvolutionCodes();
			} else if (program == 3) {
				runUrbanPopulationDynamics();
			} else if (program != 4) {
				System.out.println("Fatal error");
			}
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Thank you for using our Matrix Calculator!");
	}

	public int chooseProgram() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Which program would you like to run?");
		System.out.println("(1) The Hilbert Matrix");
		System.out.println("(2) Convolution Codes");
		System.out.println("(3) Urban Population Dynamics");
		System.out.println("(4) End program");

		String input = "";
		int program = -1;
		while (program == -1) {
			System.out.print("Input: ");
			input = scan.next();
			try {
				program = Integer.parseInt(input);
				while (program > 4 || program < 1) {
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Please enter a valid integer between 1 and 4");
					System.out.println("--------------------------------------------------------------------------------");
					program = -1;
					break;
				}
			} catch (Exception e) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter a valid integer between 1 and 4");
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		return program;
	}

	public int hilbertInput() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("(0) Quit");
		System.out.println("(1) View and solve Hilbert Matrix for a particular n");
		System.out.println("(2) View and solve Hilbert Matrix for n = 2 to 20");
		System.out.println("(3) Solve Ax = b for some matrix A and some matrix b");
		System.out.println("--------------------------------------------------------------------------------");
		String input = "";
		int n = -1;
		while (n == -1) {
			System.out.print("Input: ");
			input = scan.next();
			try {
				n = Integer.parseInt(input);
				while (n < 0 || n > 3) {
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Please enter a valid integer greater than or equal to 0 and less than four.");
					System.out.println("--------------------------------------------------------------------------------");
					n = -1;
					break;
				}
			} catch (Exception e) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter a valid integer greater than or equal to 0 and less than four.");
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		return n;
	}

	public int hilbertInputIndividual() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Enter the n value for the desired Hilbert Matrix, or 0 to quit");
		System.out.println("--------------------------------------------------------------------------------");
		String input = "";
		int n = -1;
		while (n == -1) {
			System.out.print("Input: ");
			input = scan.next();
			try {
				n = Integer.parseInt(input);
				while (n < 0) {
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Please enter a valid integer greater than or equal to 0.");
					System.out.println("--------------------------------------------------------------------------------");
					n = -1;
					break;
				}
			} catch (Exception e) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter a valid integer greater than or equal to 0.");
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		return n;
	}

	public String[] solveAXBInput() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Enter the name of the file containing the matrix A");
		System.out.println("followed by the name of the file containing the vector B, or 0 to quit.");
		System.out.println("Ex. - Input: a.dat b.dat");
		System.out.println("If you wish to enter an augmented matrix, simply enter one file name and 0");
		System.out.println("Ex. - Input: a.dat 0");
		System.out.println("--------------------------------------------------------------------------------");
		String[] files = null;
		boolean successfullyRead = false;
		while (!successfullyRead) {
			System.out.print("Input: ");
			String inputOne = scan.next();
			String inputTwo = scan.next();
			String input = "";
			if (inputTwo.equals("0")) {
				input = inputOne;
			} else {
				input = inputOne + " " + inputTwo;
			}
			files = input.split(" ");
			if (files.length == 0) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter valid file name(s) or 0.");
				System.out.println("--------------------------------------------------------------------------------");
			} else if (!files[0].equals("0")) {
				successfullyRead = true;
				for (int i = 0; i < 2 && i < files.length && successfullyRead; i++) {
					try {
						FileReader reader = new FileReader(files[i]);
					} catch (Exception e) {
						successfullyRead = false;
						System.out.println("--------------------------------------------------------------------------------");
						System.out.println("Please enter valid file name(s) or 0.");
						System.out.println("--------------------------------------------------------------------------------");
					}
				}
			} else {
				successfullyRead = true;
			}
		}
		return files;
	}

	public void solveAXB(String[] files) {
		LinkedList[] matricesAndVectors = readMatrices(files);
		if (matricesAndVectors.length == 1) {
			// Augmented matrix
			Matrix augmented = (Matrix) matricesAndVectors[0].get(0);
			System.out.println("\n\n");
			System.out.println("--------------------------");
			System.out.println("INITIAL AUGMENTED MATRIX");
			System.out.println("--------------------------");
			System.out.println(augmented);
			Matrix a = augmented.getMatrix(0, augmented.rows() - 1, 0, augmented.columns() - 2);
			Vector b = augmented.getSubVector(0, augmented.rows() - 1, augmented.columns() - 1);
			Hilbert.solveSystem(a, b);
		} else if (matricesAndVectors.length == 2) {
			// Matrix and Vector
			Matrix a = (Matrix) matricesAndVectors[0].get(0);
			Vector b = (Vector) matricesAndVectors[1].get(0);
			Hilbert.solveSystem(a, b);
		} else {
			System.out.println("Fatal error");
		}
	}

	public int leslieInput() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("(0) Quit");
		System.out.println("(1) View current population vector");
		System.out.println("(2) View Leslie Matrix");
		System.out.println("(3) Pass one iteration and view population vector");
		System.out.println("(4) Use power method to find dominant eigenvalue");
		System.out.println("(5) Input Leslie Matrix and initial population vector");
		System.out.println("(6) Test power method on any matrix A with initial approximation u0");
		System.out.println("--------------------------------------------------------------------------------");
		String input = "";
		int n = -1;
		while (n == -1) {
			System.out.print("Input: ");
			input = scan.next();
			try {
				n = Integer.parseInt(input);
				while (n < 0 || n > 6) {
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Please enter a valid integer greater than or equal to 0 and less than seven.");
					System.out.println("--------------------------------------------------------------------------------");
					n = -1;
					break;
				}
			} catch (Exception e) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter a valid integer greater than or equal to 0 and less than seven.");
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		return n;
	}

	public void changeLeslieInfo(String[] files, Leslie leslie) {
		LinkedList[] matricesAndVectors = readMatrices(files);
		if (matricesAndVectors.length == 1) {
			// Augmented matrix
			Matrix augmented = (Matrix) matricesAndVectors[0].get(0);
			System.out.println("\n\n");
			System.out.println("--------------------------");
			System.out.println("INPUTTED AUGMENTED MATRIX");
			System.out.println("--------------------------");
			System.out.println(augmented);
			Matrix a = augmented.getMatrix(0, augmented.rows() - 1, 0, augmented.columns() - 2);
			Vector b = augmented.getSubVector(0, augmented.rows() - 1, augmented.columns() - 1);
			leslie.setLeslie(a);
			leslie.setPopulation(b);
		} else if (matricesAndVectors.length == 2) {
			// Matrix and Vector
			Matrix a = (Matrix) matricesAndVectors[0].get(0);
			Vector b = (Vector) matricesAndVectors[1].get(0);
			leslie.setLeslie(a);
			leslie.setPopulation(b);
		} else {
			System.out.println("Fatal error");
		}
	}

	public void runHilbertMatrix() {
		int n = -1;
		while (n != 0) {
			n = hilbertInput();
			if (n != 0) {
				if (n == 1) {
					n = hilbertInputIndividual();
					if (n != 0) {
						Hilbert x = new Hilbert(n);
						x.solveAllMethods();
					}
				} else if (n == 2) {
					for (int i = 2; i <= 20; i++) {
						Hilbert x = new Hilbert(i);
						x.solveAllMethods();
					}
				} else if (n == 3) {
					String[] files = solveAXBInput();
					if (!files[0].equals("0")) {
						solveAXB(files);
					}
				}
			}
		}
	}

	public void runConvolutionCodes() {
		System.out.println("Came to convolution");
	}

	public void runUrbanPopulationDynamics() {
		int n = -1;
		Leslie leslie = new Leslie();
		while (n != 0) {
			n = leslieInput();
			if (n != 0) {
				if (n == 3) {
					System.out.print("Before iteration: ");
					System.out.println(leslie.getPopulationVector());
					leslie.passOneIteration();
					System.out.print("After iteration: ");
					System.out.println(leslie.getPopulationVector());
				} else if (n == 2) {
					System.out.println("Leslie Matrix");
					System.out.println(leslie.getLeslieMatrix());
				} else if (n == 1) {
					System.out.println(leslie.getPopulationVector());
				} else if (n == 4) {
					PowerObject power = leslie.getDominantEigenvalue();
					System.out.println(power);
				} else if (n == 5) {
					String[] fileNames = solveAXBInput();
					changeLeslieInfo(fileNames, leslie);
					System.out.println("Leslie Matrix");
					System.out.println(leslie.getLeslieMatrix());
					System.out.println("Population Vector");
					System.out.println(leslie.getPopulationVector());
				} else if (n == 6) {
					String[] fileNames = solveAXBInput();
					System.out.print("Please enter a tolerance: ");
					double tol = scan.nextDouble();
					powerMethodOnUserInput(fileNames, tol);
				}
			}
		}
	}

	public void powerMethodOnUserInput(String[] files, double tol) {
		LinkedList[] matricesAndVectors = readMatrices(files);
		if (matricesAndVectors.length == 1) {
			// Augmented matrix
			Matrix augmented = (Matrix) matricesAndVectors[0].get(0);
			System.out.println("\n\n");
			System.out.println("--------------------------");
			System.out.println("INPUTTED AUGMENTED MATRIX");
			System.out.println("--------------------------");
			System.out.println(augmented);
			Matrix a = augmented.getMatrix(0, augmented.rows() - 1, 0, augmented.columns() - 2);
			Vector b = augmented.getSubVector(0, augmented.rows() - 1, augmented.columns() - 1);
			System.out.println(power_method.power_method(a, tol, b));
		} else if (matricesAndVectors.length == 2) {
			// Matrix and Vector
			Matrix a = (Matrix) matricesAndVectors[0].get(0);
			Vector b = (Vector) matricesAndVectors[1].get(0);
			System.out.println(power_method.power_method(a, tol, b));
		} else {
			System.out.println("Fatal error");
		}
	}

	public static void main(String[] args) {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("***NOTE*** - If the formatting of the numbers or matrices seems off, resize your shell/terminal so that the screen is wider and so that pixels are smaller until everything fits properly as intended. As a guesstimate, this whole block of text constituting the NOTE should all appear on a single line.");
		MCWindow runner = new MCWindow();
		runner.run();
	}
}