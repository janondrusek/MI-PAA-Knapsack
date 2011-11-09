package cz.cvut.fit.mi_paa.knapsack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.ClassUtils;

import cz.cvut.fit.mi_paa.knapsack.resolver.FPTASPriceResolver;
import cz.cvut.fit.mi_paa.knapsack.resolver.Resolver;
import cz.cvut.fit.mi_paa.knapsack.result.Result;

public class KnapsackRunner {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("sk"));
		long startCpu = getCpuTime();
		long startTimestamp = System.currentTimeMillis();
		try {
			String[] files = { "src/main/resources/knap_4.inst.dat", "src/main/resources/knap_10.inst.dat",
					"src/main/resources/knap_15.inst.dat", "src/main/resources/knap_20.inst.dat",
					"src/main/resources/knap_22.inst.dat", "src/main/resources/knap_25.inst.dat",
					"src/main/resources/knap_27.inst.dat", "src/main/resources/knap_30.inst.dat",
					"src/main/resources/knap_32.inst.dat", "src/main/resources/knap_35.inst.dat",
					"src/main/resources/knap_37.inst.dat", "src/main/resources/knap_40.inst.dat" };
			for (String file : files) {
				System.out.println(file);
				KnapsackReader kr = getKnapsackReader(file);
				solveKnapsack(kr, Integer.parseInt(args[1]));
			}
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

		Resolver[] resolvers = new Resolver[] { new FPTASPriceResolver(3) };
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
		// printWeightRelativeDeviations(knapsacks);
		// printValueRelativeDeviations(knapsacks);
	}

	private static void printWeightRelativeDeviations(List<Knapsack> knapsacks) {
		System.out.println("Weight:");
		double[] deviationSums = new double[3];
		for (Knapsack knapsack : knapsacks) {
			for (int i = 0; i < deviationSums.length; i++) {
				deviationSums[i] += knapsack.getNumOfItems() * Math.pow(2, i + 1) / knapsack.getMaxWeight();
			}
		}
		printRelativeDeviations(deviationSums, knapsacks.size());
	}

	private static void printValueRelativeDeviations(List<Knapsack> knapsacks) {
		System.out.println("Value:");
		double[] deviationSums = new double[3];
		for (Knapsack knapsack : knapsacks) {
			for (int i = 0; i < deviationSums.length; i++) {
				deviationSums[i] += knapsack.getNumOfItems() * Math.pow(2, i + 1) / knapsack.getMaxValue();
			}
		}
		printRelativeDeviations(deviationSums, knapsacks.size());
	}

	private static void printRelativeDeviations(double[] deviationSums, int size) {
		for (int i = 0; i < deviationSums.length; i++) {
			System.out.printf("%.2f%n", deviationSums[i] / size);
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
