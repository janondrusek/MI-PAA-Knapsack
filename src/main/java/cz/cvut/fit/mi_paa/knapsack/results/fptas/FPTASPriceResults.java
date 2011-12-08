package cz.cvut.fit.mi_paa.knapsack.results.fptas;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.fptas.FPTASPriceResult;
import cz.cvut.fit.mi_paa.knapsack.results.AbstractCountableResults;

public class FPTASPriceResults extends AbstractCountableResults<FPTASPriceResult> {

	public FPTASPriceResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new FPTASPriceResult[knapsacks.size()], knapsacks);
	}
}
