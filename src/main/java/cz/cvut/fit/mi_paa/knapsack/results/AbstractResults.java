package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;

abstract public class AbstractResults<T extends AbstractResult> implements Results {

	private T[] results;

	@SuppressWarnings("unchecked")
	public AbstractResults(int numOfRepeats, Resolver resolver, T[] results, List<Knapsack> knapsacks) {
		this.results = results;
		for (int i = 0; i < numOfRepeats; i++) {
			for (int j = 0; j < knapsacks.size(); j++) {
				this.results[j] = (T) resolver.solve(knapsacks.get(j));
			}
		}
	}

	protected T[] getResults() {
		return results;
	}

	@Override
	public String toString() {
		return ClassUtils.getShortClassName(getResults().getClass());
	}

}
