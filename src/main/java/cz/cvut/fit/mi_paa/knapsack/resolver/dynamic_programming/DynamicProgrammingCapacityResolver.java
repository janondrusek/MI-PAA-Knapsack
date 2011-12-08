package cz.cvut.fit.mi_paa.knapsack.resolver.dynamic_programming;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.AbstractResolver;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.result.dynamic_programming.DynamicProgrammingCapacityResult;
import cz.cvut.fit.mi_paa.knapsack.results.Results;
import cz.cvut.fit.mi_paa.knapsack.results.dynamic_programming.DynamicProgrammingCapacityResults;

public class DynamicProgrammingCapacityResolver extends AbstractResolver {

	private DynamicProgrammingCapacityResult result;

	private int[] weights;
	private int[] values;
	private boolean[] used;

	@Override
	public Results getResults(int numOfRepeats, List<Knapsack> knapsacks) {
		return new DynamicProgrammingCapacityResults(numOfRepeats, this, knapsacks);
	}

	@Override
	public AbstractResult solve(Knapsack knapsack) {
		setOriginal(knapsack);
		init();

		boolean[] used = knap();
		for (int i = 1; i < used.length; i++) {
			getOriginal().getItems()[i - 1].setUsed(used[i]);
		}
		result.setValue(getOriginal().getValue());

		return result;
	}

	private void init() {
		result = new DynamicProgrammingCapacityResult(getOriginal());
		values = new int[getOriginal().getNumOfItems() + 1];
		weights = new int[getOriginal().getNumOfItems() + 1];
		used = new boolean[getOriginal().getNumOfItems() + 1];

		for (int i = 1; i <= getOriginal().getNumOfItems(); i++) {
			values[i] = getOriginal().getItems()[i - 1].getValue();
			weights[i] = getOriginal().getItems()[i - 1].getWeight();
		}
	}

	private boolean[] knap() {
		boolean[][] trial = new boolean[getOriginal().getMaxWeight() + 1][getOriginal().getNumOfItems() + 1];
		int[] bestValues = new int[getOriginal().getMaxWeight() + 1];
		int weight, item;

		for (weight = 1; weight <= getOriginal().getMaxWeight(); weight++) {
			int bestItem = 0, testWeight;

			bestValues[weight] = bestValues[weight - 1];
			for (item = 1; item <= getOriginal().getNumOfItems(); item++) {

				testWeight = weight - weights[item];
				if (testWeight >= 0 && !trial[testWeight][item]) {
					if (bestValues[weight] < values[item] + bestValues[testWeight]) {
						bestItem = item;
						bestValues[weight] = values[item] + bestValues[testWeight];
					}
				}
			}
			result.incrementNumOfChecks();
			if (bestItem > 0) {
				testWeight = weight - weights[bestItem];
				System.arraycopy(trial[testWeight], 0, trial[weight], 0, getOriginal().getNumOfItems() + 1);
				trial[weight][bestItem] = true;
			} else
				System.arraycopy(trial[weight - 1], 0, trial[weight], 0, getOriginal().getNumOfItems() + 1);

		}
		System.arraycopy(trial[getOriginal().getMaxWeight()], 0, used, 0, getOriginal().getNumOfItems() + 1);
		return used;
	}

}
