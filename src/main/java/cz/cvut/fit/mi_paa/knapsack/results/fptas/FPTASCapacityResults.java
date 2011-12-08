package cz.cvut.fit.mi_paa.knapsack.results.fptas;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.fptas.FPTASCapacityResult;
import cz.cvut.fit.mi_paa.knapsack.results.AbstractCountableResults;

public class FPTASCapacityResults extends AbstractCountableResults<FPTASCapacityResult> {

	public FPTASCapacityResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new FPTASCapacityResult[knapsacks.size()], knapsacks);
	}
}
