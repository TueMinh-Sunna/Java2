package demo;

public class DemoThread {

	public static void main(String[] args) throws InterruptedException {
		Runnable task = () -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName() + " i = " + i);
				Thread.yield();
			}
		};
		Thread thread = new Thread(task, "Thread-0");
		
//		thread.start();
		thread.run(); //main
//		thread.join();
		while(thread.isAlive()) {}  //wait
		
		System.out.println(Thread.currentThread().getName() + " finished");
	}
}
