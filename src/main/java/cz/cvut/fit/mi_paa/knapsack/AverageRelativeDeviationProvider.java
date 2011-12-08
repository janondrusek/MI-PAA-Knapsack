package cz.cvut.fit.mi_paa.knapsack;

import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.dynamic_programming.DynamicProgrammingCapacityResolver;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.results.AbstractResults;

public class AverageRelativeDeviationProvider {

	private AbstractResults<?> results;

	public AverageRelativeDeviationProvider(AbstractResults<?> results) {
		this.results = results;
	}

	public double getAverageRelativeDeviation() {
		double relativeDeviationSum = 0;
		for (AbstractResult result : results) {
			relativeDeviationSum += getRelativeDeviation(result);
		}
		return relativeDeviationSum / results.size();
	}

	private double getOptimalResult(AbstractResult result) {
		Resolver resolver = new DynamicProgrammingCapacityResolver();
		return resolver.solve(result.getKnapsack()).getValue();
	}

	private double getRelativeDeviation(AbstractResult result) {
		double optimalResult = getOptimalResult(result);
		return (double) (optimalResult - result.getValue()) / (double) optimalResult;
	}

}
