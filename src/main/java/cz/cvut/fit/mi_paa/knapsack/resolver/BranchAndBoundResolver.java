package cz.cvut.fit.mi_paa.knapsack.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.comparator.ValueComparator;
import cz.cvut.fit.mi_paa.knapsack.result.BranchAndBoundResult;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class BranchAndBoundResolver extends AbstractResolver {

	private Queue<Knapsack> queue;

	private BranchAndBoundResult result;

	private Knapsack original;

	private Knapsack best;

	int[] valRemain;

	private List<String> tested;
	private Map<String, Integer> limits;

	private void init(Knapsack knapsack) {
		tested = new ArrayList<>();
		limits = new HashMap<>();
		queue = new LinkedList<>();

		setBest(knapsack);
		setOriginal(knapsack);

		sort();
		result = new BranchAndBoundResult(knapsack);
		valRemain = new int[getOriginal().getNumOfItems()];
		// Generate the bounding vector: the value of ALL items from
		// the current position up to the top.
		for (int j = 0; j < getOriginal().getNumOfItems(); j++)
			for (int k = j; k < getOriginal().getNumOfItems(); k++)
				valRemain[j] += getOriginal().getItems()[k].getValue();

	}

	private void sort() {
		Arrays.sort(getOriginal().getItems(), new ValueComparator());
		ArrayUtils.reverse(getOriginal().getItems());
	}

	@Override
	public Result solve(Knapsack knapsack) {
		init(knapsack);
		branchAndBound();

		return result;
	}

	private void branchAndBound() {
		queue.add(getOriginal().clone());

		while (!queue.isEmpty()) {
			Knapsack queued = queue.remove();

			result.incrementNumOfChecks();
			audition(queued);

			while (getLimit(queued) + 1 < getOriginal().getNumOfItems()) {
				putLimit(queued, getLimit(queued) + 1);
				int lim = getLimit(queued);

				if (queued.getWeight() + getOriginal().getItems()[lim].getWeight() <= getOriginal().getMaxWeight()) {
					if (queued.getValue() + valRemain[lim] >= getBest().getValue()) {
						// State USING this item
						queued.useItem(lim);
						if (!tested.contains(queued.getIdentifier())) {
							tested.add(queued.getIdentifier());
							queue.add(queued.clone());
						}
						// State NOT using this item
						queued.removeItem(lim);
					}
				}
			}
		}

	}

	private Integer getLimit(Knapsack knapsack) {
		Integer limit = limits.get(knapsack.getIdentifier());
		return limit != null ? limit : new Integer(-1);
	}

	private void audition(Knapsack trial) {
		if (trial.getValue() > getBest().getValue()) {
			int n = getBest().getNumOfUsedItems();

			setBest(trial.clone());
			putLimit(getBest(), new Integer(n - 1));
			result.setValue(getBest().getValue());
		}
	}

	private void putLimit(Knapsack knapsack, Integer limit) {
		limits.put(knapsack.getIdentifier(), limit);
	}

	private Knapsack getBest() {
		return best;
	}

	private void setBest(Knapsack best) {
		this.best = best;
	}

	private Knapsack getOriginal() {
		return original;
	}

	private void setOriginal(Knapsack original) {
		this.original = original;
	}

}
