package cz.cvut.fit.mi_paa.knapsack.result.fptas;

import cz.cvut.fit.mi_paa.knapsack.result.AbstractCountableResult;
import cz.cvut.fit.mi_paa.knapsack.result.AbstractResult;

public abstract class FPTASResult extends AbstractCountableResult {

	public FPTASResult(AbstractResult result) {
		super(result.getKnapsack());
		setValue(result.getValue());
	}

}
