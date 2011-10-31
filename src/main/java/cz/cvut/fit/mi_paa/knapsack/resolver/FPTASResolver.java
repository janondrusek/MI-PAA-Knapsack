package cz.cvut.fit.mi_paa.knapsack.resolver;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class FPTASResolver extends AbstractResolver {

	@Override
	public Result solve(Knapsack knapsack) {
		for (int i = 0; i < knapsack.getNumOfItems(); i++) {
			Item item = knapsack.getItems()[i];
			knapsack.getItems()[i] = new Item(item.getIndex(), item.getWeight() >> 1, item.getValue());
		}

		DynamicProgrammingCapacityResolver resolver = new DynamicProgrammingCapacityResolver();
		return resolver.solve(knapsack);
	}
}
