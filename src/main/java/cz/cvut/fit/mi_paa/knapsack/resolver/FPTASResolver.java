package cz.cvut.fit.mi_paa.knapsack.resolver;

public abstract class FPTASResolver extends AbstractResolver {

	private int numOfLsbToShift;

	public FPTASResolver(int numOfLsbToShift) {
		this.numOfLsbToShift = numOfLsbToShift;
	}

	protected int getNumOfLsbToShift() {
		return numOfLsbToShift;
	}

}
