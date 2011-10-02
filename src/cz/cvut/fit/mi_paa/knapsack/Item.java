package cz.cvut.fit.mi_paa.knapsack;

public class Item implements Comparable<Item> {

	private int index;

	private boolean testedInHeuristic;

	private boolean usedInHeuristic;

	private int value;

	private int weight;

	public Item(int index, int weight, int value) {
		this.index = index;
		this.weight = weight;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	public double getRatio() {
		if (getValue() <= 0) {
			return 0;
		}
		return getValue() / getWeight();
	}

	@Override
	public int compareTo(Item item) {
		double ratio = getRatio();
		double itemRatio = item.getRatio();
		if (ratio == itemRatio) {
			return 0;
		}
		if (ratio < itemRatio) {
			return -1;
		}
		return 1;
	}

	public void setUsedInHeuristic(boolean used) {
		this.usedInHeuristic = used;
	}

	public boolean isUsedInHeuristic() {
		return usedInHeuristic;
	}

	public void setTestedInHeuristic(boolean testedInHeuristic) {
		this.testedInHeuristic = testedInHeuristic;
	}

	public boolean isTestedInHeuristic() {
		return testedInHeuristic;
	}

}
