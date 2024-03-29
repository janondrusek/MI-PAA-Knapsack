package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

abstract public class AbstractCountableResult extends AbstractResult {

	private int numOfChecks = 0;

	protected AbstractCountableResult(Knapsack knapsack) {
		super(knapsack);
	}

	public void incrementNumOfChecks() {
		numOfChecks++;
	}

	public void setNumOfChecks(int numOfChecks) {
		this.numOfChecks = numOfChecks;
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
