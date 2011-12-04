package cz.cvut.fit.mi_paa.knapsack.result;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

public class SimulatedAnnealingResult extends AbstractResult {

	public SimulatedAnnealingResult(Knapsack knapsack) {
		super(knapsack);
	}

	@Override
	public int getValue() {
		return getKnapsack().getValue();
	}

}
