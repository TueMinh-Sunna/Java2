package week3;

public class ParkingGarage {
	private int capacity;
	private int availableSpots;

	public ParkingGarage(int capacity) {
		super();
		this.capacity = capacity;
		this.availableSpots = capacity; //empty
	}

	public synchronized void enter() {
		while (availableSpots == 0) {
			System.out.println("full. Waiting..."); // 1 entry set, 1 empty set
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		availableSpots--;
		System.out.println("Car is entering...");
	}
	
	public synchronized void leave() {
		sleep();
		
		availableSpots++;
		System.out.println("Car is leaving...");
		notifyAll();
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getAvailableSpots() {
		return availableSpots;
	}

}
