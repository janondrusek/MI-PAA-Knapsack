package cz.cvut.fit.mi_paa.knapsack.result;

import org.apache.commons.lang3.StringUtils;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

public class BruteForceResult extends Result {

	private int bestSack;

	public BruteForceResult(Knapsack knapsack) {
		super(knapsack);
	}

	public int getBestSack() {
		return bestSack;
	}

	public void setBestSack(int bestSack) {
		this.bestSack = bestSack;
	}

	@Override
	public String getItems() {
		return StringUtils.rightPad(StringUtils.reverse(Integer.toBinaryString(getBestSack())), getKnapsack()
				.getNumOfItems(), "0");
	}

}
