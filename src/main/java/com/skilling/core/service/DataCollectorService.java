package com.skilling.core.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.skilling.core.domain.Point;
import com.skilling.core.domain.Station;

public class DataCollectorService {

	public static List<Point> getPoints(String src) {

		String line = "";
		List<Point> res = new ArrayList<Point>();

		try (BufferedReader br = new BufferedReader(new FileReader(src))) {
			while ((line = br.readLine()) != null) {

				Point nextOrder = getPoint(line.split(","));
				if (nextOrder != null) {
					res.add(nextOrder);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private static Point getPoint(String[] strings) {

		Point point = null;
		try {
			point = new Point(strings);
		} catch (Exception e) {
			e.printStackTrace(); // Invalid data... do something
		}
		return point;
	}

	public static List<Station> getStations(String src) {

		String line = "";
		List<Station> res = new ArrayList<Station>();

		try (BufferedReader br = new BufferedReader(new FileReader(src))) {
			while ((line = br.readLine()) != null) {

				Station nextOrder = getStation(line.split(","));
				if (nextOrder != null) {
					res.add(nextOrder);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // invalid input...
		}
		return res;
	}

	private static Station getStation(String[] strings) {

		Station point = null;
		try {
			point = new Station(strings);
		} catch (Exception e) {
			e.printStackTrace(); // Invalid data... do something
		}
		return point;
	}

}
