package interfaces;

/**
 * This interface provides methods for crossover in an evolutionary algorithm.
 */
public interface Crosser {

	/**
	 * Method does crossover on the two given objects and returns the result. Given objects are
	 * not changed.
	 * 
	 * @param one
	 *            first object in the crossover
	 * @param two
	 *            second object in the crossover
	 * @return result of the crossover
	 */
	Object crossover(Object one, Object two);

}
