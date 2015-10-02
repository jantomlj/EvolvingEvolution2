package interfaces;

import java.util.Map;

public interface Instruction {

	/**
	 * Executes the instruction, either mutation or crossocer on the given population, using
	 * the given evaluator for evaluating fitness. Returns the best found fitness while
	 * executing the instruction.
	 * 
	 * @param population
	 *            population that will be used for mutation/crossover
	 * @param evaluator
	 *            fitness evaluator
	 * @param hash
	 *            hashMap that is used to store already calculated fitnesses
	 * @return best found fitness
	 */
	double execute(Object[] population, FitnessEvaluator<Object> evaluator, Map<Integer, Double> hash);

	/**
	 * Copies the instruction.
	 */
	Instruction copy();

	/**
	 * Gets the number of fitness evaluations that have been done during the last execution of
	 * the instruction.
	 */
	int getNumberOfFitnessEvaluations();
}
