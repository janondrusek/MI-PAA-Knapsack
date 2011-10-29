package cz.cvut.fit.mi_paa.knapsack.resolver;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.BruteForceResult;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class BruteForceResolver extends AbstractResolver {

	@Override
	public Result solve(Knapsack knapsack) {
		BruteForceResult bruteForceResult = new BruteForceResult(knapsack);
		for (int i = 0; i < knapsack.getStackSize(); i++) {
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

}
