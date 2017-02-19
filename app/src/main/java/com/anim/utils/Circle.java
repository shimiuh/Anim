package com.anim.utils;

import android.graphics.Point;

public class Circle {
	public final Point center;
	public final double radius;

	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("Center= ").append(center)
				.append(", r=").append(radius).toString();
	}
}
