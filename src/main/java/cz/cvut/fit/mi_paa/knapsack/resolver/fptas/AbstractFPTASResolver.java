package cz.cvut.fit.mi_paa.knapsack.resolver.fptas;

import cz.cvut.fit.mi_paa.knapsack.resolver.AbstractResolver;

public abstract class AbstractFPTASResolver extends AbstractResolver {

	private int numOfLsbToShift;

	public AbstractFPTASResolver(int numOfLsbToShift) {
		this.numOfLsbToShift = numOfLsbToShift;
	}

	protected int getNumOfLsbToShift() {
		return numOfLsbToShift;
	}

}
