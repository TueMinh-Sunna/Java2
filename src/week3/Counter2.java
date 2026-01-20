package week3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter2 {
	private static Lock lock = new ReentrantLock();

	private int count = 0;
	
	public void increase() {
		//business rule
		
		lock.lock();
		try {
			count++;			
		} finally {
			lock.unlock();
		}
	}
	
	public int getCount() {
		return count;
	}
}
