package cz.cvut.fit.mi_paa.knapsack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.resolver.BranchAndBoundResolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.BruteForceResolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.DynamicProgrammingCapacityResolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.FPTASResolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class KnapsackRunner {

	public static void main(String[] args) {
		long startCpu = getCpuTime();
		long startTimestamp = System.currentTimeMillis();
		try {
			KnapsackReader kr = getKnapsackReader(args[0]);
			solveKnapsack(kr, Integer.parseInt(args[1]));
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

	private static void solveKnapsack(KnapsackReader kr, int numOfRepeats) {
		List<Knapsack> knapsacks = new ArrayList<>();
		while (kr.hasNext()) {
			Knapsack knapsack = kr.next();
			knapsacks.add(knapsack);
		}

		Resolver[] resolvers = new Resolver[] { new BruteForceResolver(), new BranchAndBoundResolver(),
				new DynamicProgrammingCapacityResolver(), new FPTASResolver() };
		for (Resolver resolver : resolvers) {
			long startCpu = getCpuTime();
			long startTimestamp = System.currentTimeMillis();
			for (int i = numOfRepeats; i > 0; i--) {
				for (Knapsack knapsack : knapsacks) {
					Result result = resolver.solve(knapsack);
					// System.out.println(result);
				}
			}
			printTimeInfo(ClassUtils.getShortClassName(resolver.getClass()), startCpu, startTimestamp, numOfRepeats);
		}

	}

	private static void help(String message) {
		System.out.println(message);
		System.out.println("Usage: java -jar "
				+ KnapsackRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath()
				+ " datafile.txt num_of_repeats");
	}

	private static long getCpuTime() {
		return ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean())
				.getProcessCpuTime();
	}

	private static void printTimeInfo(String operationName, long startCpu, long startTimestamp) {
		printTimeInfo(operationName, startCpu, startTimestamp, 1);
	}

	private static void printTimeInfo(String operationName, long startCpu, long startTimestamp, int numOfRepeats) {
		System.out.printf("%s took %.9f CPU s, real: %.9f s%n", operationName, (getCpuTime() - startCpu) / 1000000000D
				/ numOfRepeats, (System.currentTimeMillis() - startTimestamp) / 1000D / numOfRepeats);
	}
}
