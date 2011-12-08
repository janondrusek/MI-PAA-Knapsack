package cz.cvut.fit.mi_paa.knapsack.resolver.fptas;

import cz.cvut.fit.mi_paa.knapsack.Item;
import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.resolver.AbstractResolver;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;
import cz.cvut.fit.mi_paa.knapsack.result.fptas.FPTASPriceResult;

public abstract class AbstractFPTASResolver extends AbstractResolver {

	private int numOfLsbToShift;

	public AbstractFPTASResolver(int numOfLsbToShift) {
		this.numOfLsbToShift = numOfLsbToShift;
	}

	protected int getNumOfLsbToShift() {
		return numOfLsbToShift;
	}
	
	protected AbstractResult solve(Knapsack knapsack, AbstractFPTASResolver resolver) {
		Knapsack clone = knapsack.clone();
		for (int i = 0; i < clone.getNumOfItems(); i++) {
			Item item = clone.getItems()[i];
			clone.getItems()[i] = new Item(item.getIndex(), item.getWeight(), item.getValue() >> getNumOfLsbToShift());
		}

		return new FPTASPriceResult(resolver.solve(clone));
	}

}
