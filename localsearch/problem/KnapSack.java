package localsearch.problem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KnapSack {

	private final String	fileName;	// ks_1000.dat
	private Integer			nbObject;
	private String[]		profits;
	private String[]		weights;
	private Integer			weightMax;
	private int[]			solution;

	public KnapSack(String filename) throws IOException {
		// keep the sack in a text file
		this.fileName = filename;
		this.nbObject = new Integer(Files.readAllLines(Paths.get(fileName)).get(0));
		this.profits = Files.readAllLines(Paths.get(fileName)).get(1).split(" ");
		this.weights = Files.readAllLines(Paths.get(fileName)).get(2).split(" ");
		this.weightMax = new Integer(Files.readAllLines(Paths.get(fileName)).get(3));

		// Generate a random solution for this sack
		this.solution = generateRandomSolution();
	}

	/**
	 * Evaluate the KnapSack problem
	 */
	public float eval() {
		int w = w();
		int z = z();

		if (w <= weightMax)
			return z;
		else
			return (float) z - penalityFunction() * (w - weightMax);
	}

	private float penalityFunction() {
		float max = 0;

		for (int i = 0; i < nbObject; i++) {
			float temp = (float) new Integer(profits[i]) / new Integer(weights[i]);
			if (temp > max) {
				max = temp;
			}
		}
		return max;
	}

	/**
	 * @return int value : the profit of the actual solution
	 */
	private int z() {
		int res = 0;
		for (int i = 0; i < profits.length; i++) {
			res += new Integer(profits[i]) * solution[i];
		}
		// System.out.println("Debug : z="+res);
		return res;
	}

	/**
	 * @return int value : the weight of the actual solution
	 */
	private int w() {
		int res = 0;
		for (int i = 0; i < weights.length; i++) {
			res += new Integer(weights[i]) * solution[i];
		}

		return res;
	}

	/**
	 * @return a random solution for the knapSack problem
	 */
	public int[] generateRandomSolution() {
		int[] bag = new int[this.nbObject];
		for (int i = 0; i < this.nbObject; i++) {
			bag[i] = ((int) Math.round(Math.random()));
		}

		return bag;
	}

	/*
	 * Getters / Setters
	 */
	public String getFileName() {
		return fileName;
	}

	public int[] getSolution() {
		return solution;
	}

	public void setSolutionByReference(int[] solution) {
		this.solution = solution;
	}
	public void setSolutionByCopy(int[] solution) {
		for(int i=0; i<solution.length; i++){
			this.solution[i] = solution[i];
		}
	}

	public void setSolution(int indice, int value) {
		this.solution[indice] = value;
	}

}
