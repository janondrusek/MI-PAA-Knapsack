package cz.cvut.fit.mi_paa.knapsack.result;

public abstract class FPTASResult extends AbstractCountableResult {

	public FPTASResult(AbstractResult result) {
		super(result.getKnapsack());
		setValue(result.getValue());
	}

}
