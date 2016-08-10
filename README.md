# Matrix-Calculator
A program that applies common functions to matrices and vectors.<br />
Created by: Siddarth Senthilkumar, Ashika Ganesh, & Katherine Cabezas

## How to Use

1) Open terminal and cd into the directory containing the source files.<br />
2) Type `javac *.java` and press Enter.<br />
3) Type `java MCWindow` and press Enter to run the utility.

## Usage Modes
MCWindow provides 4 options -

(1) The Hilbert Matrix<br />
(2) Convolution Codes<br />
(3) Urban Population Dynamics<br />
(4) End program<br />

To run programs that are associated with a certain part of the project, simply enter the number.<br />
For example, if you want to do computations with power_method, since this was part of Urban Dynamics, type`3` and press enter.

### Hilbert Matrix
Hilbert Matrix has 4 options.

(0) Quit<br />
(1) View and solve Hilbert Matrix for a particular n<br />
(2) View and solve Hilbert Matrix for n = 2 to 20<br />
(3) Solve Ax = b for some matrix A and some matrix b.<br />

0 - Exits the Hilbert Matrix procedures and returns to the main menu.<br />
1 - Allows a user to enter an integer n for a Hilbert Matrix of dimensions n x n and then displays the LU/QR-Householder/QR-Givens factorizations, solutions and approximate error.<br />
2 - Displays all n = 2 to 20 Hilbert matrices, solutions, and errors for all factorizations.<br />
3 - Allows a user to input a matrix and/or vector, then solves the system using all three factorization methods, prints out the matrices, errors, and solution vectors.
######A user should input the matrix file followed by a space and then the vector file. If the matrix is an augmented matrix, the user simply needs to enter the file name, a space, and 0. For example, "a.dat 0".

### Convolution Codes
Convolution Codes has 3 options:

0 - Quits the program<br />
1 - Asks for an input n and prints out the resulting encoded word.<br />
2 - Runs iterative methods on given matrix A and y. Same input rules as Hilbert.

### Urban Population Dynamics
Urban Population Dynamics has 7 options:

0 - Quits the program<br />
1 - Prints the current population vector (default to the one in the PDF).<br />
2 - Prints the Leslie matrix used in the program.<br />
3 - Advances the population one generation and prints the population (before and after the advance).<br />
4 - Uses the power method to find the dominant eigenvalue. Accepts a tolerance of 0.000000001 by default. The only way to change this tolerance is to manually enter it in (6).<br />
5 - Allows the user to modify the Leslie matrix and initial population vector. The same rules apply for inputting file names as Hilbert's. Subsequent iterations on the Leslie matrix will be run on these new values so long as the user does not quit the urban population dynamics program, at which the default vector and matrix will be restored.<br />
6 - Allows the user to test the power method on a Matrix A and initial approximation vector u. The same input rules apply as Hilbert's. The program will then ask for a tolerance level. At this point, the user enters a decimal tolerance for the accuracy of the power method to be computed. If the matrix does not converge, the program will print so.
