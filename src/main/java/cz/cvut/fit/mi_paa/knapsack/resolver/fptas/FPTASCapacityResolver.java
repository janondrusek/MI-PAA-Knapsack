package cz.cvut.fit.mi_paa.knapsack.resolver.fptas;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.dynamic_programming.DynamicProgrammingCapacityResolver;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.result.fptas.FPTASCapacityResult;
import cz.cvut.fit.mi_paa.knapsack.results.Results;
import cz.cvut.fit.mi_paa.knapsack.results.fptas.FPTASCapacityResults;

public class FPTASCapacityResolver extends AbstractFPTASResolver {

	public FPTASCapacityResolver(int numOfLsbToShift) {
		super(numOfLsbToShift);
	}

	@Override
	public Results getResults(int numOfRepeats, List<Knapsack> knapsacks) {
		return new FPTASCapacityResults(numOfRepeats, this, knapsacks);
	}

	@Override
	public AbstractResult solve(Knapsack knapsack) {
		Knapsack clone = knapsack.clone();
		for (int i = 0; i < clone.getNumOfItems(); i++) {
			Item item = clone.getItems()[i];
			clone.getItems()[i] = new Item(item.getIndex(), item.getWeight() >> getNumOfLsbToShift(), item.getValue());
		}

		Resolver resolver = new DynamicProgrammingCapacityResolver();
		return new FPTASCapacityResult(resolver.solve(clone));
	}
}
