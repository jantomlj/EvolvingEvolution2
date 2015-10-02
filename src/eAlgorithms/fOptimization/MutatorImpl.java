package eAlgorithms.fOptimization;

import java.util.Random;

import interfaces.Mutator;

/**
 * Mutator class that knows how to mutate a member of the population.
 */
public class MutatorImpl implements Mutator {

	private double min;
	private double max;
	private double sigma;
	private Random rand;

	/**
	 * @param min
	 *            minimum value that a single vector element can have
	 * @param max
	 *            maximum value that a single vector element can have
	 * @param sigma
	 *            value used for gaussian mutation
	 */
	public MutatorImpl(double min, double max, double sigma) {
		rand = new Random();
		this.min = min;
		this.max = max;
		this.sigma = sigma;
	}

	@Override
	public Object mutate(Object unit) {
		Vector one = (Vector) unit;
		Vector newOne = new Vector(one.getSize());
		for (int i = 0; i < one.getSize(); i++) {
			double num = one.get(i) + sigma * rand.nextGaussian();
			if (num > max)
				num = max;
			if (num < min)
				num = min;
			newOne.set(i, num);
		}
		return newOne;
	}
}