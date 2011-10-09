package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.StringUtils;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;

public class RatioHeuristicResult extends Result {

	public RatioHeuristicResult(Knapsack knapsack) {
		super(knapsack);
	}

	@Override
	public String getItems() {
		String[] indices = new String[getKnapsack().getNumOfItems()];
		for (Item item : getKnapsack().getItems()) {
			indices[item.getIndex()] = item.isUsedInHeuristic() ? "1" : "0";
		}
		return StringUtils.join(indices);
	}

}
