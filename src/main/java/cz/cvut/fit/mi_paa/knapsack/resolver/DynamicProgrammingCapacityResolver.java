package cz.cvut.fit.mi_paa.knapsack.resolver;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.DynamicProgrammingCapacityResult;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class DynamicProgrammingCapacityResolver extends AbstractResolver {

	private DynamicProgrammingCapacityResult result;

	private int[] weights;
	private int[] values;
	private boolean[] used;

	@Override
	public Result solve(Knapsack knapsack) {
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
		int[] bestVal = new int[getOriginal().getMaxWeight() + 1];
		int wt, k;

		for (wt = 1; wt <= getOriginal().getMaxWeight(); wt++) {
			int bestK = 0, testWt;

			bestVal[wt] = bestVal[wt - 1];
			for (k = 1; k <= getOriginal().getNumOfItems(); k++) {
				testWt = wt - weights[k];
				if (testWt >= 0 && !trial[testWt][k])
					if (bestVal[wt] < values[k] + bestVal[testWt]) {
						bestK = k;
						bestVal[wt] = values[k] + bestVal[testWt];
					}
			}
			if (bestK > 0) {
				testWt = wt - weights[bestK];
				System.arraycopy(trial[testWt], 0, trial[wt], 0, getOriginal().getNumOfItems() + 1);
				trial[wt][bestK] = true;
			} else
				System.arraycopy(trial[wt - 1], 0, trial[wt], 0, getOriginal().getNumOfItems() + 1);

		}
		System.arraycopy(trial[getOriginal().getMaxWeight()], 0, used, 0, getOriginal().getNumOfItems() + 1);
		return used;
	}

}
