package cz.cvut.fit.mi_paa.knapsack;

public class Item {

	private int weight;

	private int value;

	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}
}
