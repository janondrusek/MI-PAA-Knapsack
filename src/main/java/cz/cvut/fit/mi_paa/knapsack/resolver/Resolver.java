package cz.cvut.fit.mi_paa.knapsack.resolver;

import java.util.List;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.results.Results;

public interface Resolver {

	public AbstractResult solve(Knapsack knapsack);

	public Results getResults(int numOfRepeats, List<Knapsack> knapsacks);
}
