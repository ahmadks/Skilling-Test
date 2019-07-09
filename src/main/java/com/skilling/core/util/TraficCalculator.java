package com.skilling.core.util;

import java.util.Random;

import com.skilling.core.domain.Conditions;

public class TraficCalculator {

	// calculate the traffic density
	public static synchronized String getDensity() {

		return Conditions.valueById((new Random()).nextInt(3) + 1).label;
	}
}