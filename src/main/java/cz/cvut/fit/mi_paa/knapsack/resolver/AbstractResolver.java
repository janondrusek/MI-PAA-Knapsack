package cz.cvut.fit.mi_paa.knapsack.resolver;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

public abstract class AbstractResolver implements Resolver {
	
	private Knapsack original;

	protected Knapsack getOriginal() {
		return original;
	}

	protected void setOriginal(Knapsack original) {
		this.original = original;
	}

}
