package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoPool {
//	public static List<Integer> list = new ArrayList<Integer>();
	public static List<Integer> list = new Vector<Integer>(); //wowza
	public static Random rd = new Random();
	
	public static void main(String[] args) {
		Runnable task = () -> {
			int n = rd.nextInt(1,1000);
			list.add(n);
			System.out.println(Thread.currentThread().getName());
		};
		
//		ExecutorService pool = Executors.newFixedThreadPool(4);
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 1000; i++) {
			pool.submit(task);
		}
		
		pool.shutdown();
		
		while (!pool.isTerminated()) {} //wait
		
		System.out.println(list.size());
	}
}
