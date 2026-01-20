package week3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoParkingGarage {
	static ParkingGarage garage = new ParkingGarage(10);

	public static void main(String[] args) {
		Runnable enterTask = () -> {
			garage.enter();
		};
		Runnable leaveTask = () -> {
			garage.leave();
		};

		ExecutorService service = Executors.newCachedThreadPool();
//		ExecutorService service = Executors.newFixedThreadPool(4);

		for (int i = 0; i < 10; i++) {
			service.submit(enterTask);
		}

		service.submit(enterTask);
		service.submit(enterTask);
		service.submit(enterTask);
		service.submit(enterTask);
		service.submit(enterTask);

		service.submit(leaveTask);

		service.shutdown();
		while (!service.isTerminated()) {}
		
		System.out.println("Available sports: " + garage.getAvailableSpots());
	}
}
