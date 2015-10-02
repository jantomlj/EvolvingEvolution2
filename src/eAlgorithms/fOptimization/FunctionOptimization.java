package eAlgorithms.fOptimization;

import interfaces.Crosser;
import interfaces.EvolutionaryAlgorithm;
import interfaces.Mutator;

import java.util.Random;

/**
 * Class that uses genetic algorithm to find an input for which a function reaches it's minimum
 * value.
 */
public class FunctionOptimization extends EvolutionaryAlgorithm<Vector> {

	/** population size */
	public static final int POP_SIZE = 20;
	/** size of the vector that is the input of the function */
	public static final int VECTOR_SIZE = 5;
	/** number of iterations through the algorithm instructions */
	public static final int EVALUATIONS_NUMBER = 200;

	public static final double SIGMA_VALUE = 0.75;
	
	public static final int SELECTION_MINIMUM = 3;
	public static final int SELECTION_MAXIMUM = 6;

	private Random rand;

	/**
	 * Creates an instance that can execute function optimizarion algorithm.
	 */
	public FunctionOptimization() {
		super(EVALUATIONS_NUMBER, new Evaluators.Auckley());
		rand = new Random();
	}
	
	@Override
	public void initializeEmpyPopulationArray() {
		population = new Vector[POP_SIZE];
	}

	/**
	 * Initializes the population as an array of vectors. Every vector is randomly generated.
	 */
	@Override
	public void initializePopulation() {
		for (int i = 0; i < population.length; i++) {
			population[i] = initializeRandomVector();
		}
	}

	/**
	 * Initializes a vector that consists of random values.
	 */
	private Vector initializeRandomVector() {
		Vector v = new Vector(VECTOR_SIZE);
		for (int i = 0; i < v.getSize(); i++) {
			v.set(i, generateDomainNumber());
		}
		return v;
	}

	@Override
	public boolean findingMinimum() {
		return true;
	}

	@Override
	public Crosser getCrosser() {
		return new CrosserImpl();
	}
	
	@Override
	public Mutator getMutator() {
		return new MutatorImpl(getMinDomainNumber(), getMaxDomainNumber(), SIGMA_VALUE);
	}
	
	
	@Override
	public EvolutionaryAlgorithm<Vector> newInstance() {
		return new FunctionOptimization();
	}
	
	
	
	///just a method for testing
	public void initializeAlgorithm() {
		int count = 0;
		for (int i = 0; i < POP_SIZE; i++) {
			putCrossoverInstruction(count++, rndIndexes());
		}
		for (int i = 0; i < POP_SIZE * 1.5; i++) {
			putMutateInstruction(count++, rndIndexes());
		}
	}

	private int[] rndIndexes() {
		int size = SELECTION_MINIMUM + generateRandomPosition(SELECTION_MAXIMUM - SELECTION_MINIMUM + 1);
		int[] indexes = new int[size];
		for (int i = 0; i < size; i++) {
			indexes[i] = generateRandomPosition(POP_SIZE);
		}
		return indexes;
	}
	
	private int generateRandomPosition(int size) {
		return Math.abs(rand.nextInt()) % size;
	}
}
