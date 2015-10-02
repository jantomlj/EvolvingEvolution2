package interfaces;

import java.util.Arrays;

/**
 * Class that implements some methods used for both {@link CrossoverInstruction} and
 * {@link MutateInstruction}.
 */
public abstract class AbstractInstruction implements Instruction {

	int[] indexes;

	boolean findingMinimum;

	int evaluationsDone;

	/**
	 * @return number of indexes used.
	 */
	public int getIndexesNumber() {
		return indexes.length;
	}

	/**
	 * Gets the index at the specified position.
	 */
	public int getIndex(int pos) {
		return indexes[pos];
	}

	/**
	 * Sets the index for selection and deselection to the given number
	 * 
	 * @param pos
	 *            position of the index that will be set
	 * @param number
	 *            that will be set as the new index value
	 */
	public void setIndex(int pos, int newIndex) {
		indexes[pos] = newIndex;
	}

	/**
	 * Make a copy of the indexes
	 * 
	 * @return new array, same as indexes
	 */
	public int[] getIndexesCopy() {
		return Arrays.copyOf(indexes, getIndexesNumber());
	}


	@Override
	public int getNumberOfFitnessEvaluations() {
		return evaluationsDone;
	}

}
