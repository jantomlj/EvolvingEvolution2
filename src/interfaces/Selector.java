package interfaces;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

// usage:  start -> select, deselect, selectSndBest -> getBestLast, getNumberOfFitnessEvaluations
public class Selector {

	private double bestLast;

	private int evaluationsDone;

	private Integer[] indexes;

	// resets the best value to the worst possible
	public void start(boolean findingMinimum, int[] indexes, Object[] population, FitnessEvaluator<Object> evaluator,
			Map<Integer, Double> hash) {
		bestLast = findingMinimum ? Double.MAX_VALUE : Double.MIN_VALUE;
		evaluationsDone = 0;
		sort(indexes, population, evaluator, findingMinimum, hash);
	}

	private void sort(int[] indexes2, Object[] population, FitnessEvaluator<Object> evaluator, boolean findingMinimum,
			Map<Integer, Double> hash) {
		indexes = new Integer[indexes2.length];
		for (int i = 0; i < indexes2.length; i++) {
			indexes[i] = indexes2[i];
		}
		Arrays.sort(indexes, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				Double fit1 = hash.get(o1);
				if (fit1 == null) {
					fit1 = evaluator.evaluate(population[o1]);
					hash.put(o1, fit1);
					evaluationsDone++;
				}
				Double fit2 = hash.get(o2);
				if (fit2 == null) {
					fit2 = evaluator.evaluate(population[o2]);
					hash.put(o2, fit2);
					evaluationsDone++;
				}
				
				adjustBestLast(findingMinimum, fit1);
				adjustBestLast(findingMinimum, fit2);
				return findingMinimum ? fit1.compareTo(fit2) : fit2.compareTo(fit1);
			}
		});
	}

	// the best
	public int select() {
		return indexes[0];
	}

	// the worst
	public int deselect() {
		return indexes[indexes.length - 1];
	}

	// second best
	public int selectSnd() {
		return indexes[1];
	}

	// best Value got during computation
	public double getBestLast() {
		return bestLast;
	}

	public int getNumberOfFitnessEvaluations() {
		return evaluationsDone;
	}

	private void adjustBestLast(boolean findingMinimum, double fit) {
		if (findingMinimum && fit < bestLast || !findingMinimum && fit > bestLast) {
			bestLast = fit;
		}
	}

}
