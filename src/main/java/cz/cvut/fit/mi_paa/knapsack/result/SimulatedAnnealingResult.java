package cz.cvut.fit.mi_paa.knapsack.result;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

public class SimulatedAnnealingResult extends AbstractCountableResult {

	private int acceptedStates;

	public SimulatedAnnealingResult(Knapsack knapsack) {
		super(knapsack);
	}

	public int getAcceptedStates() {
		return acceptedStates;
	}

	public void setAcceptedStates(int acceptedStates) {
		this.acceptedStates = acceptedStates;
	}

}
