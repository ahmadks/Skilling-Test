package com.skilling.core.domain;

public class Point extends Location{

	Integer droneId;
	
	String time;
	Boolean isNearStation =false;

	public Boolean IsNearStation() {
		return isNearStation;
	}

	public void setIsNearStation(Boolean isNearStation) {
		this.isNearStation = isNearStation;
	}

	public Point(String[] array) {
		 droneId=Integer.parseInt(array[0]);
		 latitude=Double.parseDouble(array[1]);
		 longitude=Double.parseDouble(array[2]);
		 time=array[3];	}

	public Integer getDroneId() {
		return droneId;
	}

	public void setDroneId(Integer droneId) {
		this.droneId = droneId;
	}

	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
