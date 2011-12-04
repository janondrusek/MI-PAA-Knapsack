package cz.cvut.fit.mi_paa.knapsack.resolver.fptas;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.dynamic_programming.DynamicProgrammingPriceResolver;
import cz.cvut.fit.mi_paa.knapsack.result.FPTASPriceResult;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.results.FPTASPriceResults;
import cz.cvut.fit.mi_paa.knapsack.results.Results;

public class FPTASPriceResolver extends AbstractFPTASResolver {

	public FPTASPriceResolver(int numOfLsbToShift) {
		super(numOfLsbToShift);
	}

	@Override
	public Results getResults(int numOfRepeats, List<Knapsack> knapsacks) {
		return new FPTASPriceResults(numOfRepeats, this, knapsacks);
	}

	@Override
	public AbstractResult solve(Knapsack knapsack) {
		Knapsack clone = knapsack.clone();
		for (int i = 0; i < clone.getNumOfItems(); i++) {
			Item item = clone.getItems()[i];
			clone.getItems()[i] = new Item(item.getIndex(), item.getWeight(), item.getValue() >> getNumOfLsbToShift());
		}

		Resolver resolver = new DynamicProgrammingPriceResolver();
		return new FPTASPriceResult(resolver.solve(clone));
	}
}
