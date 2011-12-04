package cz.cvut.fit.mi_paa.knapsack.results;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.BranchAndBoundResult;

public class BranchAndBoundResults extends AbstractCountableResults<BranchAndBoundResult> {

	public BranchAndBoundResults(int numOfRepeats, Resolver resolver, List<Knapsack> knapsacks) {
		super(numOfRepeats, resolver, new BranchAndBoundResult[knapsacks.size()], knapsacks);
	}

}
