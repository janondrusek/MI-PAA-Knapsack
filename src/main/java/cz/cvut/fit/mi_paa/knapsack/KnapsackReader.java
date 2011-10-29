package cz.cvut.fit.mi_paa.knapsack;

import java.io.BufferedReader;
import java.util.Iterator;

public class KnapsackReader implements Iterator<Knapsack> {

	private BufferedReader br;

	private String line;

	public KnapsackReader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public boolean hasNext() {
		boolean next = true;
		try {
			line = br.readLine();
			if (line.trim().length() <= 0) {
				next = false;
			}
		} catch (Exception e) {
			next = false;
		}
		return next;
	}

	@Override
	public Knapsack next() {
		return new Knapsack(line.split(" "));
	}

	@Override
	public void remove() {

	}

}
