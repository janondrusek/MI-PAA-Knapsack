package cz.cvut.fit.mi_paa.knapsack.result;

public abstract class FPTASResult extends Result {

	public FPTASResult(Result result) {
		super(result.getKnapsack());
		setValue(result.getValue());
	}

}
