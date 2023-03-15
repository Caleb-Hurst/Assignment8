package com.coderscampus;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Assignment8App {
	public static void main(String[] args) {
		Assignment8 assignment8 = new Assignment8();
		ExecutorService pool = Executors.newFixedThreadPool(14);
		CompletableFuture<List<Integer>> task = CompletableFuture.supplyAsync(() -> assignment8.getNumbers(), pool);
	}
}
