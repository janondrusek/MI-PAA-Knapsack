package cz.cvut.fit.mi_paa.knapsack;

import java.io.BufferedReader;
import java.util.Iterator;

public class KnapsackReader implements Iterator<Knapsack> {

	private BufferedReader br;

	String line;

	public KnapsackReader(BufferedReader br) {
		this.br = br;
	}

	@Override
	public boolean hasNext() {
		try {
			line = br.readLine();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Knapsack next() {
		return new Knapsack(line.split(" "));
	}

	@Override
	public void remove() {

	}

}
