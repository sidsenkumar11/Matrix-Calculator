/**
 * A representation of a given Leslie Matrix
 * 
 * @author Siddarth Senthilkumar
 * @version 1.0
 */
public class Leslie {
	private Matrix a;
	private Vector population;

	/**
	 * Constructs the given Leslie matrix
	 * and the intitial population vector
	 * from the PDF specifications.
	 */
	public Leslie() {
		double[][] aMatrix = {{0, 1.2, 1.1, .9, .1, 0, 0, 0, 0},
							  {.7, 0, 0, 0, 0, 0, 0, 0, 0},
							  {0, .85, 0, 0, 0, 0, 0, 0, 0},
							  {0, 0, .9, 0, 0, 0, 0, 0, 0},
							  {0, 0, 0, .9, 0, 0, 0, 0, 0},
							  {0, 0, 0, 0, .88, 0, 0, 0, 0},
							  {0, 0, 0, 0, 0, .8, 0, 0, 0},
							  {0, 0, 0, 0, 0, 0, .77, 0, 0},
							  {0, 0, 0, 0, 0, 0, 0, .4, 0}};
		this.a = new Matrix(aMatrix);

		double[] populationVector = {2.1, 1.9, 1.8, 2.1, 2.0, 1.7, 1.2, 0.9, 0.5};
		for (int i = 0; i < populationVector.length; i++) {
			populationVector[i] = populationVector[i] * 100000;
		}
		this.population = new Vector(populationVector);
	}

	public double getTotalPopulation() {
		double total = 0;
		for (int i = 1; i < population.numElements(); i++) {
			total += population.get(i);
		}
		return total;
	}
	/*
		What will the population distribution be in 2010? 2020? 2030? 2040? 2050? Calculate
		also the total population in those years, and by what fraction the total population
		changed each year.
	*/
	public void runFive() {
		System.out.println("\n\n");
		System.out.println("Initial population: " + population);
		double populationBefore = getTotalPopulation();
		System.out.println("Total population: " + populationBefore);
		passOneIteration();
		double currentPopulation = getTotalPopulation();
		double fractionalChange = (currentPopulation - populationBefore) / populationBefore * 100;
		System.out.println("\n\n");
		System.out.println("After iteration 1: " + population);
		System.out.println("Total population: " + currentPopulation);
		System.out.println("Fractional Change %: " + fractionalChange);
		populationBefore = currentPopulation;
		passOneIteration();
		currentPopulation = getTotalPopulation();
		fractionalChange = (currentPopulation - populationBefore) / populationBefore * 100;
		System.out.println("\n\n");
		System.out.println("After iteration 2: " + population);
		System.out.println("Total population: " + currentPopulation);
		System.out.println("Fractional Change %: " + fractionalChange);
		populationBefore = currentPopulation;
		passOneIteration();
		currentPopulation = getTotalPopulation();
		fractionalChange = (currentPopulation - populationBefore) / populationBefore * 100;
		System.out.println("\n\n");
		System.out.println("After iteration 3: " + population);
		System.out.println("Total population: " + currentPopulation);
		System.out.println("Fractional Change %: " + fractionalChange);
		populationBefore = currentPopulation;
		passOneIteration();
		currentPopulation = getTotalPopulation();
		fractionalChange = (currentPopulation - populationBefore) / populationBefore * 100;
		System.out.println("\n\n");
		System.out.println("After iteration 4: " + population);
		System.out.println("Total population: " + currentPopulation);
		System.out.println("Fractional Change %: " + fractionalChange);
		populationBefore = currentPopulation;
		passOneIteration();
		currentPopulation = getTotalPopulation();
		fractionalChange = (currentPopulation - populationBefore) / populationBefore * 100;
		System.out.println("\n\n");
		System.out.println("After iteration 5: " + population);
		System.out.println("Total population: " + currentPopulation);
		System.out.println("Fractional Change %: " + fractionalChange);
	}

	/**
	 * Applies the power method
	 */
	public void passOneIteration() {
		this.population = MatrixCalculator.multiply(a, population);
	}

	public void setLeslie(Matrix matrix) {
		this.a = matrix;
	}

	public void setPopulation(Vector vector) {
		this.population = vector;
	}

	public Matrix getLeslieMatrix() {
		return a;
	}

	public Vector getPopulationVector() {
		return population;
	}

	public PowerObject getDominantEigenvalue() {
		return MatrixCalculator.power_method(a, .00000001, population);
	}

	public static void main(String[] args) {
		Leslie x = new Leslie();
		x.passOneIteration(); // 2000 - 2010
		x.passOneIteration(); // 2010 - 2020
		Matrix leslie = x.getLeslieMatrix();
		leslie.set(0, 1, leslie.get(0, 1) / 2);
		x.setLeslie(leslie);
		x.passOneIteration(); // 2020 - 2030
		System.out.println("2030: " + x.getPopulationVector());
		x.passOneIteration(); // 2030 - 2040
		System.out.println("2040: " + x.getPopulationVector());
		x.passOneIteration(); // 2040 - 2050
		System.out.println("2050: " + x.getPopulationVector());

		System.out.println(x.getDominantEigenvalue());
	}
}