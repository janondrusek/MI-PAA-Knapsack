package cz.cvut.fit.mi_paa.knapsack;

import org.apache.commons.lang3.StringUtils;
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
				sumValues += getItems()[j].getValue() * mask;
				sumWeights += getItems()[j].getWeight() * mask;

				shift = shift >> 1;
			}
			if (sumValues > getBestSackValue() && sumWeights <= getCapacity()) {
				bestSack = i;
				bestSackValue = sumValues;
			}
		}
	}

	public void solveRatioHeuristic() {

	}

	private int getId() {
		return id;
	}

	private int getBestSack() {
		return bestSack;
	}

	private int getBestSackValue() {
		return bestSackValue;
	}

	private Item[] getItems() {
		return items;
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

	@Override
	public String toString() {
		return String.format("ID: %d, best: %s, sum: %d%n", getId(),
				StringUtils.rightPad(StringUtils.reverse(Integer.toBinaryString(getBestSack())), getNumOfItems(), "0"),
				getBestSackValue());
	}

}
