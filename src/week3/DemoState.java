package week3;

public class DemoState {
	public static void main(String[] args) throws InterruptedException {
		Object lock = new Object();

		Runnable task1 = () -> {
			try {
				Thread.sleep(100); // TIMED_WAITING
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock) { // acquire
				try {
					lock.wait(); // WAITING
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} // release
		};
		Runnable task2 = () -> {
			synchronized (lock) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable task3 = () -> {
			synchronized (lock) {

			}
		};
		Runnable task4 = () -> {
		};

		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);

		System.out.println("1-state: " + thread1.getState()); // NEW
		thread1.start();
		System.out.println("2-state: " + thread1.getState()); // RUNNABLE
		Thread.sleep(20);
		System.out.println("3-state: " + thread1.getState()); // TIMED-WAITING
		Thread.sleep(150);

		System.out.println("4-state: " + thread1.getState()); // WAITING
		thread2.start();
		Thread.sleep(20);

		thread3.start();
		Thread.sleep(20);
		System.out.println("5-state: " + thread3.getState()); // BLOCKED

		thread2.join();
		System.out.println("6-state: " + thread2.getState()); // TERMINATED

	}
}
