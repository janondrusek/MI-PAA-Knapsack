package cz.cvut.fit.mi_paa.knapsack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class KnapsackRunner {

	public static void main(String[] args) {
		long startCpu = getCpuTime();
		long startTimestamp = System.currentTimeMillis();
		try {
			KnapsackReader kr = getKnapsackReader(args[0]);
			solveKnapsack(kr);
		} catch (Exception e) {
			help(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.printf("Operation took %.2f CPU s, real: %.2f s%n", (getCpuTime() - startCpu) / 1000000000D,
					(System.currentTimeMillis() - startTimestamp) / 1000D);
		}
	}

	private static KnapsackReader getKnapsackReader(String path) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		return new KnapsackReader(br);
	}

	private static void solveKnapsack(KnapsackReader kr) {
		List<Knapsack> knapsacks = new ArrayList<>();
		while (kr.hasNext()) {
			Knapsack knapsack = kr.next();
			knapsack.solveBruteForce();
			knapsack.solveRatioHeuristic();

			knapsacks.add(knapsack);
			System.out.print(knapsack);
		}
	}

	private static void help(String message) {
		System.out.println(message);
		System.out.println("Usage: java -jar "
				+ KnapsackRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath() + " datafile.txt");
	}

	@SuppressWarnings("restriction")
	private static long getCpuTime() {
		return ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getProcessCpuTime();
	}
}
