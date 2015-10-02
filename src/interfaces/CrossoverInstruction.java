package interfaces;

import java.util.Arrays;
import java.util.Map;

/**
 * Intruction that is doing the crossover on the population that is given. It does:<br>
 * <code>
 * population[deselect(...)] = crossover[select(..), select(..)];
 * </code> <br>
 * where deselect gets the index of the worst member among given, and select gets the index of
 * the best member among given.
 */
public class CrossoverInstruction extends AbstractInstruction {

	private Selector selector;

	private Crosser crosser;

	private double bestLast;


	/**
	 * Creates a new instruction with given indexes for deselecting, and selecting, crosser
	 * that knows how to do the crossover and information about whether minimum or maximum is
	 * wanted.
	 * 
	 * @param indexes
	 *            indexes in the population among which selection and deselection will be done.
	 * @param crosser
	 *            object that know how to do the crossover of two objects
	 * @param findingMinimum
	 *            if minimal value is optimal then true, otherwise false
	 */
	public CrossoverInstruction(int[] indexes, Crosser crosser, boolean findingMinimum) {
		if (indexes == null || crosser == null) {
			throw new IllegalArgumentException("Something is null");
		}
		this.findingMinimum = findingMinimum;
		this.indexes = indexes;
		this.crosser = crosser;
		bestLast = findingMinimum ? Double.MAX_VALUE : Double.MIN_VALUE;
		this.selector = new Selector();
	}

	@Override
	public double execute(Object[] population, FitnessEvaluator<Object> evaluator, Map<Integer, Double> hash) {
		selector.start(findingMinimum, indexes, population, evaluator, hash);
		int position = selector.deselect();
		population[position] = crosser.crossover(population[selector.select()],
				population[selector.selectSnd()]);
		bestLast = selector.getBestLast();
		evaluationsDone = selector.getNumberOfFitnessEvaluations();
		hash.put(position, null);
		return bestLast;
	}

	@Override
	public Instruction copy() {
		return new CrossoverInstruction(Arrays.copyOf(indexes, getIndexesNumber()), crosser, findingMinimum);
	}

}
