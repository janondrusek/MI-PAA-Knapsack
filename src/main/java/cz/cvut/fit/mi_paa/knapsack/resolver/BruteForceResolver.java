package cz.cvut.fit.mi_paa.knapsack.resolver;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.result.BruteForceResult;
import cz.cvut.fit.mi_paa.knapsack.results.BruteForceResults;
import cz.cvut.fit.mi_paa.knapsack.results.Results;

public class BruteForceResolver extends AbstractResolver {

	@Override
	public Results getResults(int numOfRepeats, List<Knapsack> knapsacks) {
		return new BruteForceResults(numOfRepeats, this, knapsacks);
	}

	@Override
	public AbstractResult solve(Knapsack knapsack) {
		BruteForceResult bruteForceResult = new BruteForceResult(knapsack);
		long stackSize = getStackSize();
		for (int i = 0; i < stackSize; i++) {
			int sumValues = 0;
			int sumWeights = 0;
			int shift = i;

			for (int j = 0; j < knapsack.getNumOfItems(); j++) {
				int mask = shift & 1;
				sumValues += knapsack.getItems()[j].getValue() * mask;
				sumWeights += knapsack.getItems()[j].getWeight() * mask;

				shift = shift >> 1;
			}
			if (sumValues > bruteForceResult.getValue() && sumWeights <= knapsack.getMaxWeight()) {
				bruteForceResult.setBestSack(i);
				bruteForceResult.setValue(sumValues);
			}
		}
		return bruteForceResult;
	}

	public long getStackSize() {
		return (long) Math.pow(2, getOriginal().getNumOfItems());
	}

}
