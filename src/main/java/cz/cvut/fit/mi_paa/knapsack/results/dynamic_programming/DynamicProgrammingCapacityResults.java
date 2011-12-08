package cz.cvut.fit.mi_paa.knapsack.results.dynamic_programming;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.dynamic_programming.DynamicProgrammingCapacityResult;
import cz.cvut.fit.mi_paa.knapsack.results.AbstractCountableResults;

public class DynamicProgrammingCapacityResults extends AbstractCountableResults<DynamicProgrammingCapacityResult> {

	public DynamicProgrammingCapacityResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new DynamicProgrammingCapacityResult[knapsacks.size()], knapsacks);
	}
}
