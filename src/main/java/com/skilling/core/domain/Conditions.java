package com.skilling.core.domain;

import java.util.HashMap;
import java.util.Map;

public enum Conditions {

	HEAVY(1, "HEAVY"), LIGHT(2, "LIGHT"), MODERATE(3, "MODERATE");
	private static final Map<Integer, Conditions> BY_ID = new HashMap<>();

	static {
		for (Conditions e : values()) {
			BY_ID.put(e.id, e);

		}
	}
	public final String label;
	public final Integer id;

	private Conditions(Integer id, String label) {
		this.label = label;
		this.id = id;
	}

	public static Conditions valueById(Integer id) {
		return BY_ID.get(id);
	}

}