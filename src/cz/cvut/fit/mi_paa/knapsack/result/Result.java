package cz.cvut.fit.mi_paa.knapsack.result;

import cz.cvut.fit.mi_paa.knapsack.Knapsack;

abstract public class Result {

	private long startTime;

	private long endTime;

	private Knapsack knapsack;

	private int value;

	protected Result(Knapsack knapsack) {
		super();
		this.knapsack = knapsack;
	}

	public void start() {
		startTime = getTimestamp();
	}

	public void end() {
		endTime = getTimestamp();
	}

	private long getTimestamp() {
		return System.currentTimeMillis();
	}

	public long getRunningTime() {
		return getEndTime() - getStartTime();
	}

	private long getStartTime() {
		return startTime;
	}

	private long getEndTime() {
		return endTime;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	protected Knapsack getKnapsack() {
		return knapsack;
	}

	abstract public String getItems();

}
