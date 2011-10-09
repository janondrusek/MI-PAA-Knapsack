package cz.cvut.fit.mi_paa.knapsack;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.math.NumberUtils;

import cz.cvut.fit.mi_paa.knapsack.result.BruteForceResult;
import cz.cvut.fit.mi_paa.knapsack.result.RatioHeuristicResult;

public class Knapsack {

	private int id;

	private int maxWeight;

	private int numOfItems;

	private BruteForceResult bruteForceResult;

	private RatioHeuristicResult ratioHeuristicResult;

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
		bruteForceResult = new BruteForceResult(this);
		for (int i = 0; i < getStackSize(); i++) {
			getBruteForceResult().start();
			int sumValues = 0;
			int sumWeights = 0;
			int shift = i;

			for (int j = 0; j < getNumOfItems(); j++) {
				int mask = shift & 1;
				sumValues += getItems()[j].getValue() * mask;
				sumWeights += getItems()[j].getWeight() * mask;

				shift = shift >> 1;
			}
			if (sumValues > getBruteForceResult().getValue() && sumWeights <= getMaxWeight()) {
				getBruteForceResult().setBestSack(i);
				getBruteForceResult().setValue(sumValues);
			}
			getBruteForceResult().end();
		}
	}

	public void solveRatioHeuristic() {
		ratioHeuristicResult = new RatioHeuristicResult(this);
		Arrays.sort(items, Collections.reverseOrder());

		int sumWeights = 0;
		while (sumWeights <= getMaxWeight()) {
			int index = getFirstUnusedItemIndex();
			if (index < 0) {
				break;
			}

			getItems()[index].setTestedInHeuristic(true);

			if (sumWeights + getItems()[index].getWeight() <= getMaxWeight()) {
				getRatioHeuristicResult().setValue(getRatioHeuristicResult().getValue() + getItems()[index].getValue());
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

	public Item[] getItems() {
		return items;
	}

	private long getStackSize() {
		return (long) Math.pow(2, getNumOfItems());
	}

	public int getNumOfItems() {
		return numOfItems;
	}

	private int getMaxWeight() {
		return maxWeight;
	}

	@Override
	public String toString() {
		return String
				.format("ID: %d, best brute-force: %s, brute-force sum: %d, best heuristic: %s, heuristic sum: %d, relative deviation: %.2f%%%n",
						getId(), getBruteForceResult().getItems(), getBruteForceResult().getValue(),
						getRatioHeuristicResult().getItems(), getRatioHeuristicResult().getValue(),
						getRelativeDeviation() * 100);
	}

	public BruteForceResult getBruteForceResult() {
		return bruteForceResult;
	}

	public RatioHeuristicResult getRatioHeuristicResult() {
		return ratioHeuristicResult;
	}

	public double getRelativeDeviation() {
		return (double) (getBruteForceResult().getValue() - getRatioHeuristicResult().getValue())
				/ (double) getBruteForceResult().getValue();
	}

}