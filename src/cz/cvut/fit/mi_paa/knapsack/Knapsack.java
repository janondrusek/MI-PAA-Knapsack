package cz.cvut.fit.mi_paa.knapsack;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class Knapsack {

	private int id;

	private int bestBestBruteSack;

	private int bestBestBruteSackValue;

	private int bestHeuristicSum;

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
			if (sumValues > getBestBruteForceSackValue() && sumWeights <= getMaxWeight()) {
				bestBestBruteSack = i;
				bestBestBruteSackValue = sumValues;
			}
		}
	}

	public void solveRatioHeuristic() {
		Arrays.sort(items, Collections.reverseOrder());

		int sumWeights = 0;
		while (sumWeights <= getMaxWeight()) {
			int index = getFirstUnusedItemIndex();
			if (index < 0) {
				break;
			}

			getItems()[index].setTestedInHeuristic(true);

			if (sumWeights + getItems()[index].getWeight() <= getMaxWeight()) {
				bestHeuristicSum += getItems()[index].getValue();
				sumWeights += getItems()[index].getWeight();
				getItems()[index].setUsedInHeuristic(true);
			}
		}

	}

	private int getFirstUnusedItemIndex() {
		for (int i = 0; i < getItems().length; i++) {
			if (!getItems()[i].isTestedInHeuristic()) {
				return i;
			}
		}
		return -1;
	}

	private int getId() {
		return id;
	}

	private int getBestBruteForceSack() {
		return bestBestBruteSack;
	}

	private int getBestBruteForceSackValue() {
		return bestBestBruteSackValue;
	}

	private int getBestHeuristicSum() {
		return bestHeuristicSum;
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

	private int getMaxWeight() {
		return maxWeight;
	}

	@Override
	public String toString() {
		return String.format(
				"ID: %d, best brute-force: %s, brute-force sum: %d, best heuristic: %s, heuristic sum: %d%n", getId(),
				StringUtils.rightPad(StringUtils.reverse(Integer.toBinaryString(getBestBruteForceSack())),
						getNumOfItems(), "0"), getBestBruteForceSackValue(), getBestHeuristic(), getBestHeuristicSum());
	}

	private String getBestHeuristic() {
		String[] indices = new String[getNumOfItems()];
		for (Item item : items) {
			indices[item.getIndex()] = item.isUsedInHeuristic() ? "1" : "0";
		}
		return StringUtils.join(indices);
	}

}