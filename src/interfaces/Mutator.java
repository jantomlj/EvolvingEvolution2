package interfaces;

/**
 * This interface provides methods for mutation in an evolutionary algorithm.
 */
public interface Mutator {

	/**
	 * This method mutates the given object and return the result. The given object must
	 * not change, the resulting object must be a new one.
	 * 
	 * @param unit
	 *            object that will be mutated
	 * @return new mutated object
	 */
	Object mutate(Object unit);
}