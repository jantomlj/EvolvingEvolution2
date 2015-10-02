package eAlgorithms.fOptimization;

import interfaces.Crosser;

/**
 * Crosser class that knows how to do a crossover over two members of the population
 */
public class CrosserImpl implements Crosser {

	@Override
	public Object crossover(Object one, Object two) {
		Vector first = (Vector) one;
		Vector second = (Vector) two;
		Vector v1 = new Vector(first.getSize());
		for (int k = 0; k < first.getSize(); k++) {
			v1.set(k, first.get(k) + second.get(k) / 2.0);
		}
		return v1;
	}

}
