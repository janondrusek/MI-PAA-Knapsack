package cz.cvut.fit.mi_paa.knapsack;

import org.apache.commons.lang3.math.NumberUtils;

public class Knapsack {

	private int id;

	private int capacity;

	private Item[] items;

	public Knapsack(String[] chunks) {
		id = NumberUtils.toInt(chunks[0]);
		capacity = NumberUtils.toInt(chunks[1]);
		items = getItems(chunks);
	}

	private Item[] getItems(String[] chunks) {
		Item[] items = new Item[getCapacity()];
		for (int i = 0; i < getCapacity(); i++) {
			items[i] = new Item(NumberUtils.toInt(chunks[2 + i]), NumberUtils.toInt(chunks[3 + i]));
		}
		return items;
	}

	public void solveBruteForce() {
		for (long i = 0; i < getStackSize(); i++) {
			System.out.printf("Mod %d mod 1 = %d:", i, i & 1);
		}
	}

	public void solveRatioHeuristic() {

	}

	private long getStackSize() {
		return (long) Math.pow(2, getCapacity());
	}

	private int getCapacity() {
		return capacity;
	}
}
