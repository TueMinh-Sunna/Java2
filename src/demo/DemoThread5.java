package demo;

public class DemoThread5 {
	public static void main(String[] args) throws InterruptedException {
		Runnable task = () -> {
			try {
				Thread.sleep(500); // TIMED WAITING
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		Thread.currentThread().setPriority(7);
		Thread thread1 = new Thread(task); //priority also 7
		System.out.println("1-State: " + thread1.getState()); //NEW
		
		thread1.start();
		System.out.println("2-State: " + thread1.getState()); //RUNNABLE
		
		Thread.sleep(495);
		System.out.println("3-State: " + thread1.getState()); //TIMED WAITING
		
		thread1.join();
		System.out.println("4-State: " + thread1.getState()); //TERMINATED
		
//		Thread.currentThread().setPriority(7);
//		System.out.println("5-Priority: " + thread1.getPriority()); //5
//		System.out.println("6-Priority: " + Thread.currentThread().getPriority()); //5
		

		System.out.println("7-Priority: " + thread1.getPriority()); //5
		System.out.println("8-Priority: " + Thread.currentThread().getPriority()); //7
		
		thread1.setPriority(3);
		System.out.println("9-Priority: " + thread1.getPriority()); //3
	}
}
