package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.AverageRelativeDeviationProvider;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.SimulatedAnnealingResolver;
import cz.cvut.fit.mi_paa.knapsack.result.SimulatedAnnealingResult;

public class SimulatedAnnealingResults extends AbstractCountableResults<SimulatedAnnealingResult> {

	public SimulatedAnnealingResults(int numOfRepeats, SimulatedAnnealingResolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new SimulatedAnnealingResult[knapsacks.size()], knapsacks);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());
		sb.append("[Average relative deviation: ");
		sb.append(getAverageRelativeDeviation());
		sb.append(", Average accepted states: ");
		sb.append(getAverageAcceptedStates());
		sb.append("]\n");
		for (SimulatedAnnealingResult result : getResults()) {
			sb.append(result);
			sb.append("\n");
		}
		return sb.toString();
	}

	private int getAverageAcceptedStates() {
		int sum = 0;
		for (SimulatedAnnealingResult result : getResults()) {
			sum += result.getAcceptedStates();
		}
		return sum / size();
	}

	private double getAverageRelativeDeviation() {
		AverageRelativeDeviationProvider relativeDeviationProvider = new AverageRelativeDeviationProvider(this);
		return relativeDeviationProvider.getAverageRelativeDeviation();
	}
}
