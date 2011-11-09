package cz.cvut.fit.mi_paa.knapsack.resolver;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.FPTASCapacityResult;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class FPTASCapacityResolver extends FPTASResolver {

	public FPTASCapacityResolver(int numOfLsbToShift) {
		super(numOfLsbToShift);
	}

	@Override
	public Result solve(Knapsack knapsack) {
		Knapsack clone = knapsack.clone();
		for (int i = 0; i < clone.getNumOfItems(); i++) {
			Item item = clone.getItems()[i];
			clone.getItems()[i] = new Item(item.getIndex(), item.getWeight() >> getNumOfLsbToShift(), item.getValue());
		}

		Resolver resolver = new DynamicProgrammingCapacityResolver();
		return new FPTASCapacityResult(resolver.solve(clone));
	}
}