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
			printTimeInfo("Total operation", startCpu, startTimestamp);
		}
	}

	private static KnapsackReader getKnapsackReader(String path) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		return new KnapsackReader(br);
	}

	private static void solveKnapsack(KnapsackReader kr) {
		long startCpu = getCpuTime();
		long startTimestamp = System.currentTimeMillis();

		List<Knapsack> knapsacks = new ArrayList<>();
		while (kr.hasNext()) {
			Knapsack knapsack = kr.next();
			knapsack.solveBruteForce();

			knapsacks.add(knapsack);
		}
		printTimeInfo("Brute-force", startCpu, startTimestamp);

		startCpu = getCpuTime();
		startTimestamp = System.currentTimeMillis();

		for (Knapsack knapsack : knapsacks) {
			knapsack.solveRatioHeuristic();
			System.out.print(knapsack);
		}

		printTimeInfo("Heuristic", startCpu, startTimestamp);
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

	private static void printTimeInfo(String operationName, long startCpu, long startTimestamp) {
		System.out.printf("%s took %.2f CPU s, real: %.2f s%n", operationName, (getCpuTime() - startCpu) / 1000000000D,
				(System.currentTimeMillis() - startTimestamp) / 1000D);
	}
}
