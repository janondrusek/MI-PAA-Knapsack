package cz.cvut.fit.mi_paa.knapsack.resolver;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.comparator.RatioComparator;
import cz.cvut.fit.mi_paa.knapsack.result.RatioHeuristicResult;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.results.RatioHeuristicResults;
import cz.cvut.fit.mi_paa.knapsack.results.Results;

public class RatioHeuristicResolver extends AbstractResolver {

	private boolean[] tested;

	private boolean[] used;

	@Override
	public Results getResults(int numOfRepeats, List<Knapsack> knapsacks) {
		return new RatioHeuristicResults(numOfRepeats, this, knapsacks);
	}

	@Override
	public AbstractResult solve(Knapsack knapsack) {
		tested = new boolean[knapsack.getNumOfItems()];
		used = new boolean[knapsack.getNumOfItems()];

		RatioHeuristicResult ratioHeuristicResult = new RatioHeuristicResult(knapsack, this);
		Arrays.sort(knapsack.getItems(), new RatioComparator());
		ArrayUtils.reverse(knapsack.getItems());

		int sumWeights = 0;
		while (sumWeights <= knapsack.getMaxWeight()) {
			int index = getFirstUnusedItemIndex(knapsack);
			if (index < 0) {
				break;
			}

			setTested(knapsack.getItems()[index]);

			if (sumWeights + knapsack.getItems()[index].getWeight() <= knapsack.getMaxWeight()) {
				ratioHeuristicResult.setValue(ratioHeuristicResult.getValue() + knapsack.getItems()[index].getValue());
				sumWeights += knapsack.getItems()[index].getWeight();
				setUsed(knapsack.getItems()[index]);
			}
		}
		return ratioHeuristicResult;
	}

	private int getFirstUnusedItemIndex(Knapsack knapsack) {
		for (int i = 0; i < knapsack.getItems().length; i++) {
			if (!isTested(knapsack.getItems()[i])) {
				return i;
			}
		}
		return -1;
	}

	private boolean isTested(Item item) {
		return tested[item.getIndex()];
	}

	private void setTested(Item item) {
		tested[item.getIndex()] = true;
	}

	public boolean isUsed(Item item) {
		return used[item.getIndex()];
	}

	private void setUsed(Item item) {
		used[item.getIndex()] = true;
	}
}
