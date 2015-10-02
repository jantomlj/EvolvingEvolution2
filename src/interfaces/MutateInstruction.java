package interfaces;

import java.util.Arrays;
import java.util.Map;

/**
 * Intruction that is doing the mutation on the population that is given. It does:<br>
 * <code>
 * population[deselect(...)] = mutate[select(..)];
 * </code> <br>
 * where deselect gets the index of the worst member among given, and select gets the index of
 * the best member among given.
 */
public class MutateInstruction extends AbstractInstruction {

	private static Selector selector = new Selector();

	private Mutator mutator;

	private double bestLast;

	/**
	 * Creates a new instruction with given indexes for deselecting, and selecting, mutator
	 * that knows how to do the mutation and information about whether minimum or maximum is
	 * wanted.
	 * 
	 * @param indexes
	 *            indexes in the population that will be used for selection and deselection
	 * @param mutator
	 *            object that know how to do the mutation on a single object
	 * @param findingMinimum
	 *            if minimal value is optimal then true, otherwise false
	 */
	public MutateInstruction(int[] indexes, Mutator mutator, boolean findingMinimum) {
		if (indexes == null || mutator == null) {
			throw new IllegalArgumentException("Something is null");
		}
		this.findingMinimum = findingMinimum;
		this.indexes = indexes;
		this.mutator = mutator;
		bestLast = findingMinimum ? Double.MAX_VALUE : Double.MIN_VALUE;
	}


	@Override
	public double execute(Object[] population, FitnessEvaluator<Object> evaluator, Map<Integer, Double> hash) {
		selector.start(findingMinimum, indexes, population, evaluator, hash);
		int position = selector.deselect();
		population[position] = mutator.mutate(population[selector.select()]);
		bestLast = selector.getBestLast();
		evaluationsDone = selector.getNumberOfFitnessEvaluations();
		hash.put(position, null);
		return bestLast;
	}

	@Override
	public Instruction copy() {
		return new MutateInstruction(Arrays.copyOf(indexes, getIndexesNumber()), mutator, findingMinimum);

	}

}
