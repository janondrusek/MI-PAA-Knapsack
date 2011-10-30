package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

public class BranchAndBoundResult extends Result {

	private int numOfChecks = 0;

	public BranchAndBoundResult(Knapsack knapsack) {
		super(knapsack);
	}

	@Override
	public String getItems() {
		return null;
	}

	public void incrementNumOfChecks() {
		numOfChecks++;
	}

	public int getNumOfChecks() {
		return numOfChecks;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(getKnapsack());
		sb.append(", ");
		sb.append(ClassUtils.getShortClassName(getClass()));
		sb.append("[");
		sb.append("value: ");
		sb.append(getValue());
		sb.append(", numOfChecks: ");
		sb.append(getNumOfChecks());
		sb.append("]]");
		return sb.toString();
	}

}
