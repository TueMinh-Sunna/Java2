package week3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingGarage2 {
	private static Lock lock = new ReentrantLock(); // true -> fair
	private Condition enterCondition = lock.newCondition();
	private Condition leaveCondition = lock.newCondition();

	private int capacity;
	private int availableSpots;

	public ParkingGarage2(int capacity) {
		super();
		this.capacity = capacity;
		this.availableSpots = capacity; // empty
	}

	public void enter() {
		lock.lock();

		try {
			while (availableSpots == 0) {
				System.out.println("full. Waiting..."); // 1 entry set, 1 empty set
				try {
					enterCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			availableSpots--;
			System.out.println("Car is entering...");
			leaveCondition.signal();
		} finally {
			lock.unlock();
		}
	}

	public void leave() {
		sleep();

		lock.lock();
		
		try {
			while(availableSpots == capacity) {
				System.out.println("Empty. Waiting...");
				leaveCondition.await();
			}
			
			availableSpots++;
			System.out.println("Car is leaving...");
			enterCondition.signal();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}
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
