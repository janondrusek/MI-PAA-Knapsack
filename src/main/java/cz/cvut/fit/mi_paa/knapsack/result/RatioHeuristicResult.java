package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.StringUtils;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.RatioHeuristicResolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.dynamic_programming.DynamicProgrammingCapacityResolver;

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

	private double getOptimalResult() {
		Resolver resolver = new DynamicProgrammingCapacityResolver();
		return resolver.solve(getKnapsack()).getValue();
	}

	public double getRelativeDeviation() {
		double optimalResult = getOptimalResult();
		return (double) (optimalResult - getValue()) / (double) optimalResult;
	}

}
