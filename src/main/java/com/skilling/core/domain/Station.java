package com.skilling.core.domain;

public class Station extends Location {

	String stationId;

	public Station(String[] array) {
		stationId = array[0];
		latitude = Double.parseDouble(array[1]);
		longitude = Double.parseDouble(array[2]);
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

}
