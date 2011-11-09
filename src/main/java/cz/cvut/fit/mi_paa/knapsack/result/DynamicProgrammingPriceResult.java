package cz.cvut.fit.mi_paa.knapsack.result;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;

public class DynamicProgrammingPriceResult extends Result {

	public DynamicProgrammingPriceResult(Knapsack knapsack) {
		super(knapsack);
	}

	@Override
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
}
