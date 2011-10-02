package cz.cvut.fit.mi_paa.knapsack;

import org.apache.commons.lang3.math.NumberUtils;

public class Knapsack {

	private int id;

	private int bestSack;

	private int bestSackValue;

	private int capacity;

	private int numOfItems;

	private Item[] items;

	public Knapsack(String[] chunks) {
		id = NumberUtils.toInt(chunks[0]);
		numOfItems = NumberUtils.toInt(chunks[1]);
		capacity = NumberUtils.toInt(chunks[2]);
		items = getItems(chunks);
	}

	private Item[] getItems(String[] chunks) {
		Item[] items = new Item[getNumOfItems()];
		for (int i = 0; i < getNumOfItems(); i++) {
			items[i] = new Item(NumberUtils.toInt(chunks[3 + 2 * i]), NumberUtils.toInt(chunks[4 + 2 * i]));
		}
		return items;
	}

	public void solveBruteForce() {
		for (int i = 0; i < getStackSize(); i++) {
			int sumValues = 0;
			int sumWeights = 0;
			int shift = i;

			for (int j = 0; j < getNumOfItems(); j++) {
				int mask = shift & 1;
				sumValues += items[j].getValue() * mask;
				sumWeights += items[j].getWeight() * mask;

				shift = shift >> 1;
			}
			if (sumValues > bestSackValue && sumWeights <= getCapacity()) {
				bestSack = i;
				bestSackValue = sumValues;
			}
		}
		System.out.printf("ID: %d, best: %d, sum: %d%n", id, bestSack, bestSackValue);
	}

	public void solveRatioHeuristic() {

	}

	private long getStackSize() {
		return (long) Math.pow(2, getNumOfItems());
	}

	private int getNumOfItems() {
		return numOfItems;
	}

	private int getCapacity() {
		return capacity;
	}
}
