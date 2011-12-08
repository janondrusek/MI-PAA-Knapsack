package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.StringUtils;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.RatioHeuristicResolver;

public class RatioHeuristicResult extends AbstractResult {

	private RatioHeuristicResolver resolver;

	public RatioHeuristicResult(Knapsack knapsack, RatioHeuristicResolver resolver) {
		super(knapsack);
		this.resolver = resolver;
	}

	@Override
	public String getItems() {
		String[] indices = new String[getKnapsack().getNumOfItems()];
		for (Item item : getKnapsack().getItems()) {
			indices[item.getIndex()] = resolver.isUsed(item) ? "1" : "0";
		}
		return StringUtils.join(indices);
	}

}
