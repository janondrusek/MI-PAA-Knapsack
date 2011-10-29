package cz.cvut.fit.mi_paa.knapsack.resolver;

import java.util.Arrays;
import java.util.Collections;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.RatioHeuristicResult;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class RatioHeuristicResolver extends AbstractResolver {

	@Override
	public Result solve(Knapsack knapsack) {
		RatioHeuristicResult ratioHeuristicResult = new RatioHeuristicResult(knapsack);
		Arrays.sort(knapsack.getItems(), Collections.reverseOrder());

		int sumWeights = 0;
		while (sumWeights <= knapsack.getMaxWeight()) {
			int index = knapsack.getFirstUnusedItemIndex();
			if (index < 0) {
				break;
			}

			knapsack.getItems()[index].setTestedInHeuristic(true);

			if (sumWeights + knapsack.getItems()[index].getWeight() <= knapsack.getMaxWeight()) {
				ratioHeuristicResult.setValue(ratioHeuristicResult.getValue() + knapsack.getItems()[index].getValue());
				sumWeights += knapsack.getItems()[index].getWeight();
				knapsack.getItems()[index].setUsedInHeuristic(true);
			}
		}
		return ratioHeuristicResult;
	}
}
