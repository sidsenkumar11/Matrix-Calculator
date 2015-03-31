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

	private LinkedList<Matrix> matrices;
	private Scanner scan;

	public MCWindow() {
		this.matrices = new LinkedList<Matrix>();
		this.scan = new Scanner(System.in);
	}

	/**
	 * Read in all Matrix .dat files into matrices
	 */
	public void readMatrices(LinkedList<String> fileNames) {

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

				// Create Matrix
				Matrix matrix = new Matrix(rows);
				matrices.add(matrix);

			} catch(IOException exception) {
				System.out.println("Error processing file: " + exception);
			}
		}

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
		System.out.println("--------------------------------------------------------------------------------");
		String input = "";
		int n = -1;
		while (n == -1) {
			System.out.print("Input: ");
			input = scan.next();
			try {
				n = Integer.parseInt(input);
				while (n < 0 || n > 2) {
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Please enter a valid integer greater than or equal to 0 and less than three.");
					System.out.println("--------------------------------------------------------------------------------");
					n = -1;
					break;
				}
			} catch (Exception e) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter a valid integer greater than or equal to 0 and less than three.");
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

	public int leslieInput() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("What would you like to do?");
		System.out.println("(0) Quit");
		System.out.println("(1) View current population vector");
		System.out.println("(2) View Leslie Matrix");
		System.out.println("(3) Pass one iteration and view population vector");
		System.out.println("(4) Use power method to find dominant eigenvalue");
		System.out.println("--------------------------------------------------------------------------------");
		String input = "";
		int n = -1;
		while (n == -1) {
			System.out.print("Input: ");
			input = scan.next();
			try {
				n = Integer.parseInt(input);
				while (n < 0 || n > 4) {
					System.out.println("--------------------------------------------------------------------------------");
					System.out.println("Please enter a valid integer greater than or equal to 0 and less than five.");
					System.out.println("--------------------------------------------------------------------------------");
					n = -1;
					break;
				}
			} catch (Exception e) {
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Please enter a valid integer greater than or equal to 0 and less than five.");
				System.out.println("--------------------------------------------------------------------------------");
			}
		}
		return n;
	}

	public void runHilbertMatrix() {
		int n = -1;
		while (n != 0) {
			n = hilbertInput();
			if (n != 0) {
				if (n == 1) {
					n = hilbertInputIndividual();
					Hilbert x = new Hilbert(n);
					x.solveAllMethods();
				} else if (n == 2) {
					for (int i = 2; i <= 20; i++) {
						Hilbert x = new Hilbert(i);
						x.solveAllMethods();
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
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("***NOTE*** - If the formatting of the numbers or matrices seems off, resize your shell/terminal so that the screen is wider and so that pixels are smaller until everything fits properly as intended. As a guesstimate, this whole block of text constituting the NOTE should all appear on a single line.");
		MCWindow runner = new MCWindow();
		runner.run();
	}
}