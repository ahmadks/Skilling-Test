package com.skilling.server;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import com.skilling.core.Configuration;
import com.skilling.core.domain.Point;
import com.skilling.core.domain.Station;
import com.skilling.core.service.ProcessorService;

public class Dispatcher implements Runnable {
	protected BlockingQueue<Point> d1q, d2q;
	protected ConcurrentHashMap<String, Object> cache;

	public Dispatcher(BlockingQueue<Point> d1q, BlockingQueue<Point> d2q, ConcurrentHashMap<String, Object> cache) {
		this.d1q = d1q;
		this.d2q = d2q;
		this.cache = cache;
	}

	public void run() {
		try {

	
			boolean inTime = inTime(LocalTime.now());
			@SuppressWarnings("unchecked")  
			List<Point> points = (List<Point>) cache.get("points");
			@SuppressWarnings("unchecked")
			List<Station> stations = (List<Station>) cache.get("stations");
			
			for (Point nextOrder : points) {
				if (nextOrder != null) {
					// Check if there a near tube station
					nextOrder.setIsNearStation(ProcessorService.calculateNearStation(nextOrder,stations));
					
					// Send nextPoint to Drones
					if (nextOrder.getDroneId() == Configuration.DRONE_1) {
						d1q.put(nextOrder);
					} else if (nextOrder.getDroneId() == Configuration.DRONE_2) {
						d2q.put(nextOrder);
					}
				}
				inTime = inTime(LocalTime.now());
				if (!inTime) { // at 8:10 Shut down the drown??.. but the dispatcher will still active
					cache.replace("status", "SHUTDOWN");// Send Shut down signal
				}

			}
			// if all data has been processed, then finish the application
			cache.replace("status", "SHUTDOWN");// Send Shut down signal

	
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private boolean inTime(LocalTime now) {
		if (now.getHour() == 8 && now.getMinute() == 10)
			return false;
		return true;
	}

	
}
