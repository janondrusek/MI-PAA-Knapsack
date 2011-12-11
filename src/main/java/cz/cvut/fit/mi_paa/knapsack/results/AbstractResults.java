package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;

abstract public class AbstractResults<T extends AbstractResult> implements Results, Iterator<T>, Iterable<T> {

	private T[] results;

	private int current = 0;

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

	public int size() {
		return results.length;
	}

	@Override
	public String toString() {
		return ClassUtils.getShortClassName(getResults().getClass());
	}

	@Override
	public boolean hasNext() {
		return results.length > current;
	}

	@Override
	public T next() {
		return results[current++];
	}

	public void reset() {
		current = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
