package com.skilling.core.service;

import java.time.LocalTime;
import java.util.List;

import com.skilling.core.Configuration;
import com.skilling.core.domain.Point;
import com.skilling.core.domain.Station;
import com.skilling.core.util.DistanceCalculator;
import com.skilling.core.util.TraficCalculator;

public class ProcessorService {

	public static Boolean calculateNearStation(Point nextOrder, List<Station> stations) {

		for (Station st : stations){
			if (DistanceCalculator.distance(nextOrder,st)<=Configuration.STATION_MAX_DISTANCE) return true;
		}
		
		return false;
	}

	public static void logTraficInfo(Point currentPoint) {
		
		System.out.println ("DRONE: " + currentPoint.getDroneId() + 
				" TIME: " + LocalTime.now() +
				" SPEED: " + Configuration.DRONE_SPEED +
				" TRAFFIC: " + TraficCalculator.getDensity() );
	}

}
