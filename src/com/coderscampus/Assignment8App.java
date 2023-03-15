package com.coderscampus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assignment8App {
	public static void main(String[] args) {
		Assignment8 assignment8 = new Assignment8();
		ExecutorService pool = Executors.newFixedThreadPool(14);
		List<CompletableFuture<List<Integer>>> tasks = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			CompletableFuture<List<Integer>> task = CompletableFuture.supplyAsync(() -> assignment8.getNumbers(), pool);
			tasks.add(task);
		}

		CompletableFuture<Void> allTasksCompleted = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));

		allTasksCompleted.join();

		pool.shutdown();

		Map<Integer, Integer> occurrences = new HashMap<>();
		for (CompletableFuture<List<Integer>> task : tasks) {
			try {
				List<Integer> numbers = task.get();
				for (Integer number : numbers) {
					occurrences.put(number, occurrences.getOrDefault(number, 0) + 1);
				}
			} catch (Exception e) {

			}
		}
		for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

	}

}
