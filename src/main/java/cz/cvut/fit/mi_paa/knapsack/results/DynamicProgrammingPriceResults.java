package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.DynamicProgrammingPriceResult;

public class DynamicProgrammingPriceResults extends AbstractCountableResults<DynamicProgrammingPriceResult> {

	public DynamicProgrammingPriceResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new DynamicProgrammingPriceResult[knapsacks.size()], knapsacks);
	}
}
