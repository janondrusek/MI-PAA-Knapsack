package cz.cvut.fit.mi_paa.knapsack.comparator;

import java.util.Comparator;

import cz.cvut.fit.mi_paa.knapsack.Item;

public class ValueComparator implements Comparator<Item> {

	@Override
	public int compare(Item left, Item right) {
		return left.getValue() - right.getValue();
	}

}
