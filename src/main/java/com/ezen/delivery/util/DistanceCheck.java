package com.ezen.delivery.util;

public class DistanceCheck {

	static final double EARTH_RADIUS = 6400;
	
	public static double getDistance(String lat, String lon, String lat0, String lon0) {
		
		double lat1 = Double.parseDouble(lat);
		double lon1 = Double.parseDouble(lon);
		double lat2 = Double.parseDouble(lat0);
		double lon2 = Double.parseDouble(lon0);
		
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		
		double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d =EARTH_RADIUS* c * 1000;    // Distance in m
		return d;
	}
	
}
