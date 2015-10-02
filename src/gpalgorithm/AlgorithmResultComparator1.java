package gpalgorithm;

import interfaces.AlgorithmResultComparator;
import interfaces.EvolutionaryAlgorithm;

/**
 * Class that compares fitnesses of {@link EvolutionaryAlgorithm}s. Works only when results
 * given are positive numbers (> 0).<br>
 * This algorithm sums the ratio-s of the results. So if the results are <br>
 * <code> 2 2 3 <br>
 *  1 3 2 <br> </code>
 *  and minimal solution is considered better then<br>
 *  (2/1 + 3/2) is bigger then (3/2) so second result is better.
 */
public class AlgorithmResultComparator1 implements AlgorithmResultComparator {

	private boolean findingMinimum;


	/**
	 * Creates a new instance of the class with an assumtion that algorithms whose fitnesses
	 * can be compared are searching for a minimum value.
	 */
	public AlgorithmResultComparator1() {
		super();
		findingMinimum = true;
	}

	@Override
	public void setFindingMinimum(boolean findingMinimum) {
		this.findingMinimum = findingMinimum;
	}

	@Override
	public boolean isBetter(double[] result1, double[] result2) {
		if (result1.length != result2.length) {
			throw new IllegalArgumentException("Results of different size, impossible to compare");
		}
		double sum1 = 0;
		double sum2 = 0;
		for (int i = 0; i < result1.length; i++) {
			if (result1[i] > result2[i]) {
				sum1 += (result1[i] / result2[i]);
			} else if (result1[i] < result2[i]) {
				sum2 += (result2[i] / result1[i]);
			}
		}
		if (findingMinimum) {
			return sum1 < sum2;
		}
		return sum1 > sum2;
	}


}
