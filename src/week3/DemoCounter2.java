package week3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoCounter2 {
	static Counter2 counter = new Counter2(); //sharing object
	
	public static void main(String[] args) {
		Runnable task = () -> {
			counter.increase();
		};
		
		ExecutorService service = Executors.newFixedThreadPool(4);
//		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 1000; i++) {
			service.submit(task);
		}
		
		service.shutdown();
		
		while(!service.isTerminated()) {} //wait
		
		System.out.println("Count: " + counter.getCount()); //1000
	}
}
