package cz.cvut.fit.mi_paa.knapsack;

import org.apache.commons.lang3.math.NumberUtils;

public class Knapsack {

	private int id;

	private int maxWeight;

	private int numOfItems;

	private Item[] items;

	public Knapsack(String[] chunks) {
		id = NumberUtils.toInt(chunks[0]);
		numOfItems = NumberUtils.toInt(chunks[1]);
		maxWeight = NumberUtils.toInt(chunks[2]);
		items = getItems(chunks);
	}

	private Item[] getItems(String[] chunks) {
		Item[] items = new Item[getNumOfItems()];
		for (int i = 0; i < getNumOfItems(); i++) {
			items[i] = new Item(i, NumberUtils.toInt(chunks[3 + 2 * i]), NumberUtils.toInt(chunks[4 + 2 * i]));
		}
		return items;
	}

	public int getFirstUnusedItemIndex() {
		for (int i = 0; i < getItems().length; i++) {
			if (!getItems()[i].isTestedInHeuristic()) {
				return i;
			}
		}
		return -1;
	}

	public int getId() {
		return id;
	}

	public Item[] getItems() {
		return items;
	}

	public long getStackSize() {
		return (long) Math.pow(2, getNumOfItems());
	}

	public int getNumOfItems() {
		return numOfItems;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	@Override
	public String toString() {
		return String.format("ID: %d", getId());
	}

}