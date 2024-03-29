package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.AverageRelativeDeviationProvider;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.RatioHeuristicResult;

public class RatioHeuristicResults extends AbstractResults<RatioHeuristicResult> {

	public RatioHeuristicResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new RatioHeuristicResult[knapsacks.size()], knapsacks);
	}

	private double getAverageRelativeDeviation() {
		AverageRelativeDeviationProvider relativeDeviationProvider = new AverageRelativeDeviationProvider(this);
		return relativeDeviationProvider.getAverageRelativeDeviation();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());
		sb.append(" [averageRelativeDeviation: ");
		sb.append(String.format("%.2f", getAverageRelativeDeviation() * 100));

		return sb.toString();
	}

}
