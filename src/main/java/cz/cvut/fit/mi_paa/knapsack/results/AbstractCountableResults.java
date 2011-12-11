package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractCountableResult;

public abstract class AbstractCountableResults<T extends AbstractCountableResult> extends AbstractResults<T> {

	public AbstractCountableResults(int numOfRepeats, Resolver resolver, T[] results, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, results, knapsacks);
	}

	private int getAverageNumOfChecks() {
		int numOfChecks = 0;

		for (T result : getResults()) {
			numOfChecks += result.getNumOfChecks();
		}

		return numOfChecks / size();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(super.toString());
		sb.append(" [averageNumOfChecks: ");
		sb.append(getAverageNumOfChecks());
		sb.append("]\n");

		return sb.toString();
	}

}
