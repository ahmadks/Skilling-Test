package com.skilling.core.util;

import com.skilling.core.domain.Location;

public class DistanceCalculator {

	//calculate the distance in meters
	public static double distance(Location l1, Location l2) {
		double lat1 = l1.getLatitude();
		double lon1 = l1.getLongitude();
		double lat2 = l2.getLatitude();
		double lon2 = l2.getLongitude();
		
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			dist = dist * 1609.34d;
			return (dist);
		}
	}
}