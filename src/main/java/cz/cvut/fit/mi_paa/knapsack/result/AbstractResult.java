package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;

abstract public class AbstractResult {

	private Knapsack knapsack;

	private int value;

	protected AbstractResult(Knapsack knapsack) {
		this.knapsack = knapsack;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Knapsack getKnapsack() {
		return knapsack;
	}

	public String getItems() {
		StringBuffer sb = new StringBuffer();

		sb.append("[");
		for (Item item : getKnapsack().getItems()) {
			sb.append(item.isUsed() ? "1" : "0");
			sb.append(" ");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");

		return sb.toString();
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
		sb.append(", ");
		sb.append(getItems());
		sb.append("]]");
		return sb.toString();
	}

}
