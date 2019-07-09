package com.skilling.client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import com.skilling.core.domain.Point;
import com.skilling.core.service.ProcessorService;
import com.skilling.core.util.DistanceCalculator;

public class Drone implements Runnable {
	private static final Integer SPEED = 12; // in meters/sec
	protected BlockingQueue<Point> queue;
	protected ConcurrentHashMap<String, Object> cache;
	protected Point currentPoint = null;

	public Drone(BlockingQueue<Point> queue, ConcurrentHashMap<String, Object> cache) {
		this.queue = queue;
		this.cache = cache;
	}

	public void run() {
		try {
			while (running()) {
				Point nextPoint = queue.take();
				moveTo(nextPoint);
				if (this.currentPoint.IsNearStation()) ProcessorService.logTraficInfo(currentPoint); 
			}
		} catch (InterruptedException ex) {
			System.out.println("CONSUMER INTERRUPTED");
		}
	}

	private boolean running() {
		return !((String)cache.get("status")).equalsIgnoreCase("SHUTDOWN");
		 
	}

	void moveTo(Point nextPoint) {
		// first point
		if (currentPoint == null) {
			currentPoint = nextPoint;
			return;
		}

		try {
			Thread.sleep((long) (1000 * (DistanceCalculator.distance(currentPoint,nextPoint) * SPEED))); // simulate time passing
			
			currentPoint = nextPoint;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
