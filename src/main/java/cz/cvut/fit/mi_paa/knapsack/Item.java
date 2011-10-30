package cz.cvut.fit.mi_paa.knapsack;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Item implements Cloneable {

	private int index;

	private int value;

	private int weight;

	private boolean used;

	public Item(int index, int weight, int value) {
		this.index = index;
		this.weight = weight;
		this.value = value;
	}

	private Item(Item item) {
		this(item.getIndex(), item.getWeight(), item.getValue());
		setUsed(item.isUsed());
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

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	protected Item clone() {
		return new Item(this);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
