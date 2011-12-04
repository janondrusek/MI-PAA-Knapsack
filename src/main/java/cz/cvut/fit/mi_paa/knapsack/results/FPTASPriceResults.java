package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.FPTASPriceResult;

public class FPTASPriceResults extends AbstractCountableResults<FPTASPriceResult> {

	public FPTASPriceResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new FPTASPriceResult[knapsacks.size()], knapsacks);
	}
}
