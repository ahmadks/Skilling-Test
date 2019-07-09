package com.skilling;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.skilling.client.Drone;
import com.skilling.core.Configuration;
import com.skilling.core.domain.Point;
import com.skilling.core.domain.Station;
import com.skilling.core.service.DataCollectorService;
import com.skilling.server.Dispatcher;

public class TraficDronesApplication {

	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<Point> d1q = new LinkedBlockingQueue<>(Configuration.DRONE_MAX_MEM);
		BlockingQueue<Point> d2q = new LinkedBlockingQueue<>(Configuration.DRONE_MAX_MEM);

		ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<>();
		
		cache.put("status", "START");
		List<Point> points = DataCollectorService.getPoints("src/main/resources/data.csv");
		List<Station> stations = DataCollectorService.getStations("src/main/resources/stations.csv");
		
		cache.put("points", points);
		cache.put("stations", stations);


		new Thread(new Dispatcher(d1q, d2q, cache)).start();

		new Thread(new Drone(d1q, cache)).start();
		new Thread(new Drone(d2q, cache)).start();

	}

}
