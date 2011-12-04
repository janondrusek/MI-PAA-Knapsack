package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.DynamicProgrammingCapacityResult;

public class DynamicProgrammingCapacityResults extends AbstractCountableResults<DynamicProgrammingCapacityResult> {

	public DynamicProgrammingCapacityResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new DynamicProgrammingCapacityResult[knapsacks.size()], knapsacks);
	}
}
