package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

abstract public class Result {

	private Knapsack knapsack;

	private int value;

	protected Result(Knapsack knapsack) {
		super();
		this.knapsack = knapsack;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	protected Knapsack getKnapsack() {
		return knapsack;
	}

	abstract public String getItems();

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
		sb.append(", ");
		sb.append(getItems());
		sb.append("]]");
		return sb.toString();
	}

}
