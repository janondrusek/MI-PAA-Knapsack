package cz.cvut.fit.mi_paa.knapsack.comparator;

import java.util.Comparator;

import cz.cvut.fit.mi_paa.knapsack.Item;

public class RatioComparator implements Comparator<Item> {

	@Override
	public int compare(Item left, Item right) {
		double ratio = getRatio(left);
		double itemRatio = getRatio(right);
		if (ratio == itemRatio) {
			return 0;
		}
		if (ratio < itemRatio) {
			return -1;
		}
		return 1;
	}

	private double getRatio(Item item) {
		if (item.getValue() <= 0) {
			return 0;
		}
		return item.getValue() / item.getWeight();
	}

}
