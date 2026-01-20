package week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoList2 {

//	HashMap<K, V> // not
//	Hashtable<K, V> //safe

//	static List<Integer> list = new ArrayList<Integer>(); // not thread-safe
	static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>()); // not thread-safe
//	static List<Integer> list = new Vector<Integer>(); // thread-safe

	public static void main(String[] args) {
		Random rd = new Random();

		Runnable task = () -> {
			int n = rd.nextInt(10, 100);
			list.add(n);
		};

		ExecutorService service = Executors.newFixedThreadPool(4);
//		ExecutorService service = Executors.newCachedThreadPool();

		for (int i = 0; i < 1000; i++) {
			service.submit(task);
		}

		service.shutdown();

		while (!service.isTerminated()) {
		} // wait

		System.out.println("Size: " + list.size()); // 1000
	}
}
