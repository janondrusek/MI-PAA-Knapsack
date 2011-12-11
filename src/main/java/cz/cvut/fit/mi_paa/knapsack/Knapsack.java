package cz.cvut.fit.mi_paa.knapsack;

import org.apache.commons.lang3.math.NumberUtils;

public final class Knapsack implements Cloneable {

	private int id;

	private int limit;

	private int maxValue;

	private int maxWeight;

	private int sumValues;

	private int sumWeights;

	private int numOfItems;

	private Item[] items;

	private Knapsack(Knapsack knapsack, Item[] items) {
		this(knapsack.getId(), knapsack.getNumOfItems(), knapsack.getMaxWeight(), knapsack.getLimit(), items);
	}

	public Knapsack(String[] chunks) {
		this(NumberUtils.toInt(chunks[0]), NumberUtils.toInt(chunks[1]), NumberUtils.toInt(chunks[2]), -1, chunks);
	}

	private Knapsack(int id, int numOfItems, int maxWeight, int limit, String[] chunks) {
		this(id, numOfItems, maxWeight, limit, getItems(chunks, numOfItems));
	}

	private Knapsack(int id, int numOfItems, int maxWeight, int limit, Item[] items) {
		this.id = id;
		this.numOfItems = numOfItems;
		this.maxWeight = maxWeight;
		this.limit = limit;
		this.items = items;
	}

	private static Item[] getItems(String[] chunks, int numOfItems) {
		Item[] items = new Item[numOfItems];
		for (int i = 0; i < numOfItems; i++) {
			items[i] = new Item(i, NumberUtils.toInt(chunks[3 + 2 * i]), NumberUtils.toInt(chunks[4 + 2 * i]));
		}
		return items;
	}

	public int getId() {
		return id;
	}

	public void useItem(int index) {
		getItems()[index].setUsed(true);
	}

	public int getValue() {
		int value = 0;
		for (Item item : getItems()) {
			if (item.isUsed()) {
				value += item.getValue();
			}
		}
		return value;
	}

	public int getWeight() {
		int weight = 0;
		for (Item item : getItems()) {
			if (item.isUsed()) {
				weight += item.getWeight();
			}
		}
		return weight;
	}

	public int getMaxValue() {
		if (maxValue == 0) {
			for (Item item : getItems()) {
				if (item.getValue() > maxValue) {
					maxValue = item.getValue();
				}
			}
		}
		return maxValue;
	}

	public int getSumValues() {
		if (sumValues == 0) {
			for (Item item : getItems()) {
				sumValues += item.getValue();
			}
		}
		return sumValues;
	}

	public int getSumWeights() {
		if (sumWeights == 0) {
			for (Item item : getItems()) {
				sumWeights += item.getWeight();
			}
		}
		return sumWeights;
	}

	public Item[] getItems() {
		return items;
	}

	public int getNumOfItems() {
		return numOfItems;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Knapsack) obj).getIdentifier().equals(getIdentifier());
	}

	@Override
	public String toString() {
		return String.format("ID: %d", getId());
	}

	@Override
	public Knapsack clone() {
		Item[] items = new Item[getNumOfItems()];
		for (int i = 0; i < getNumOfItems(); i++) {
			items[i] = getItems()[i].clone();
		}
		return new Knapsack(this, items);
	}

	public Long getIdentifier() {
		long identifier = 0;
		for (int i = 0; i < getNumOfItems(); i++) {
			identifier += i * 10 * (getItems()[i].isUsed() ? 1 : 0);
		}
		return new Long(identifier);
	}

	public void removeItem(int index) {
		getItems()[index].setUsed(false);
	}

	public int getNumOfUsedItems() {
		int numOfUsed = 0;
		for (Item item : getItems()) {
			if (item.isUsed()) {
				numOfUsed++;
			}
		}
		return numOfUsed;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}