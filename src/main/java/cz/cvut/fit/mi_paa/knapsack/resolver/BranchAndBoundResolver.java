package cz.cvut.fit.mi_paa.knapsack.resolver;

import java.util.LinkedList;
import java.util.Queue;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;
import cz.cvut.fit.mi_paa.knapsack.result.BranchAndBoundResult;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class BranchAndBoundResolver extends AbstractResolver {

	private BranchAndBoundResult result;

	private int[] weights;
	private int[] values;

	private Queue<State> queue;

	@Override
	public Result solve(Knapsack knapsack) {
		setOriginal(knapsack);
		queue = new LinkedList<>();

		result = new BranchAndBoundResult(knapsack);
		values = new int[knapsack.getNumOfItems()];
		weights = new int[knapsack.getNumOfItems()];

		for (int i = 0; i < knapsack.getNumOfItems(); i++) {
			values[i] = knapsack.getItems()[i].getValue();
			weights[i] = knapsack.getItems()[i].getWeight();
		}

		result.setValue(knapsack());
		return result;
	}

	void audition(State trial, State best) {
		if (trial.val > best.val) {
			int n = best.used.length;

			System.arraycopy(trial.used, 0, best.used, 0, n);
			best.val = trial.val;
			best.wt = trial.wt;
			best.lim = n - 1;
		}
	}

	State branchAndBound(int maxWeight, int[] weight, int[] value) {
		State currentBest;
		int n = weight.length;
		int[] valRemain = new int[n];

		currentBest = new State(n);
		queue.add(currentBest);

		sort(weight, value);

		for (int j = 0; j < n; j++)
			for (int k = j; k < n; k++)
				valRemain[j] += value[k];

		while (!queue.isEmpty()) {
			State eNode = queue.remove();

			result.incrementNumOfChecks();
			audition(eNode, currentBest);

			while (++eNode.lim < n) {
				int lim = eNode.lim;

				// static bound
				if (eNode.wt + weight[lim] <= maxWeight)
				// dynamic bound
				{
					if (eNode.val + valRemain[lim] >= currentBest.val) {
						eNode.wt += weight[lim]; // State USING this item
						eNode.val += value[lim];
						eNode.used[lim] = true;
						queue.add(new State(eNode));
						eNode.wt -= weight[lim]; // State NOT using this item
						eNode.val -= value[lim];
						eNode.used[lim] = false;
					}
				}
			}
		}
		return currentBest;
	}

	public int knapsack() {
		int k;
		int sumValue = 0;
		State goal;

		goal = branchAndBound(getOriginal().getMaxWeight(), weights, values);

		for (k = 0; k < getOriginal().getNumOfItems(); k++)
			if (goal.used[k]) {
				sumValue += values[k];
			}
		return sumValue;
	}

	void swap(int p, int q, int[] x) {
		int tmp = x[p];
		x[p] = x[q];
		x[q] = tmp;
	}

	void sort(int[] wt, int[] val) {
		for (int lim = wt.length - 1; lim > 0; lim--) {
			int min = 0;

			for (int k = 1; k <= lim; k++)
				// For THIS implementation, straight value ranking seems best.
				// if ( (double)val[k]/wt[k] < (double)val[min]/wt[min] )
				if (val[k] < val[min])
					min = k;
			swap(min, lim, wt);
			swap(min, lim, val);
		}
	}

}

class State {
	boolean[] used;
	int lim, wt, val;
	State next;

	State(int n) {
		used = new boolean[n];
		lim = -1;
		wt = val = 0;
		next = null;
	}

	State(State x) {
		used = new boolean[x.used.length];
		System.arraycopy(x.used, 0, used, 0, x.lim + 1);
		lim = x.lim;
		val = x.val;
		wt = x.wt;
		next = null;
	}

}
