package cz.cvut.fit.mi_paa.knapsack.resolver;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.FPTASPriceResult;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class FPTASPriceResolver extends FPTASResolver {

	public FPTASPriceResolver(int numOfLsbToShift) {
		super(numOfLsbToShift);
	}

	@Override
	public Result solve(Knapsack knapsack) {
		Knapsack clone = knapsack.clone();
		for (int i = 0; i < clone.getNumOfItems(); i++) {
			Item item = clone.getItems()[i];
			clone.getItems()[i] = new Item(item.getIndex(), item.getWeight(), item.getValue() >> getNumOfLsbToShift());
		}

		Resolver resolver = new DynamicProgrammingPriceResolver();
		return new FPTASPriceResult(resolver.solve(clone));
	}
}
