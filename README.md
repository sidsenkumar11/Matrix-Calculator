# Matrix-Calculator
A program that applies common functions to matrices and vectors.

Created by: Siddarth Senthilkumar, Ashika Ganesh, & Katherine Cabezas

To use the program:

Run MCWindow.java on the command line.
It is best to resize the command line and/or the font of the text to view the displayed matrices, but it is not necessary for the program to run.

MCWindow provides 4 options -

(1) The Hilbert Matrix
(2) Convolution Codes
(3) Urban Population Dynamics
(4) End program

To run programs that are associated with a certain part of the project, simply enter the number. For example, if you want to do computations with power_method, since this was part of Urban Dynamics, enter "3". Then press enter.

****IMPORTANT!*****
For part 2, Convolution Codes, run instead "ConvoCode.java". Pressing 2 in the program will instruct you to do the same.

Hilbert Matrix has 3 options.

(0) Quit
(1) View and solve Hilbert Matrix for a particular n
(2) View and solve Hilbert Matrix for n = 2 to 20
(3) Solve Ax = b for some matrix A and some matrix b.

0 - Exits the Hilbert Matrix procedures and returns to the main menu.
1 - Allows a user to enter an integer n for a Hilbert Matrix of dimensions nxn and then view the LU/QR-Householder/QR-Givens factorizations, solutions and errors.
2 - Displays all n=2 to 20 matrices, solutions, and errors for all factorizations.
3 - Allows a user to input a matrix and/or vector, then solves the system using all three factorization methods, prints out the matrices, errors, and solution vectors.

A user should input the matrix file followed by a space and then the vector file. If the matrix is an augmented matrix, the user simply needs to enter the file name, a space, and 0. For example, "a.dat 0".

Urban Population Dynamics has:

0 - Quits the program
1 - Prints the current population vector (default to the one in the PDF).
2 - Prints the Leslie matrix used in the program.
3 - Advances the population one generation and prints the population (before and after the advance).
4 - Uses the power method to find the dominant eigenvalue. Accepts a tolerance of 0.000000001 by default. The only way to change this tolerance is to manually enter it in (6).
5 - Allows the user to modify the Leslie matrix and initial population vector. The same rules apply for inputting file names as Hilbert's. Subsequent iterations on the Leslie matrix will be run on these new values so long as the user does not quit the urban population dynamics program, at which the default vector and matrix will be restored.
6 - Allows the user to test the power method on a Matrix A and initial approximation vector u. The same input rules apply as Hilbert's. The program will then ask for a tolerance level. At this point, the user enters a decimal tolerance for the accuracy of the power method to be computed. If the Matrix does not converge, the program will print so.