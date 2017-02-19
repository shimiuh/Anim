package com.anim.utils;

import android.graphics.Point;

/**
 * Utilities for generating view hierarchies without using resources.
 */
public abstract class Utils {

	 /**
	  * algorithm from here: http://mathforum.org/library/drmath/view/54323.html
	  * @param start point p1
	  * @param end point   p2
	  * @param Point above center off line from point1 to point2 p3
	  * @return Circle
	  */
      static final double TOL = 0.0000001;
	  public static Circle circleFromPoints(final Point p1, final Point p2, final Point p3)
	  {
	    final double offset = Math.pow(p2.x,2) + Math.pow(p2.y,2);
	    final double bc =   ( Math.pow(p1.x,2) + Math.pow(p1.y,2) - offset )/2.0;
	    final double cd =   (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2))/2.0;
	    final double det =  (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x)* (p1.y - p2.y); 

	    if (Math.abs(det) < TOL) { return null; }

	    final double idet = 1/det;

	    final double centerx =  (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
	    final double centery =  (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
	    final double radius  =   Math.sqrt( Math.pow(p2.x - centerx,2) + Math.pow(p2.y-centery,2));

	    return new Circle( new Point((int)centerx, (int)centery),radius);
	  }
	  
	  public static double getCircularY(int xSrc, Point center, float radious) {
		    int centerXPoint = center.x;
		    int centerYPoint = center.y;
		    double squareXSrc = Math.pow(xSrc,2);
		    double squareCenterXPoint = Math.pow(centerXPoint,2);
		    double squareCenterYPoint = Math.pow(centerYPoint,2);
		    double squareRadious = Math.pow(radious,2);
		    
		    
		    double A = squareXSrc - centerXPoint*2 * xSrc + squareCenterXPoint + squareCenterYPoint -  squareRadious;
		  	double newY = centerYPoint + Math.sqrt(squareCenterYPoint - A);
//		  	System.out.println("\nxSrc = "+xSrc+ " squareXSrc = "+squareXSrc+"  squareCenterXPoint = "+squareCenterXPoint+" squareCenterYPoint = "+squareCenterYPoint+
//		  			"squareRadious = "+squareRadious+"  A = "+A+"\nnewY = "+newY);
		  	return  newY;
		  }
	  
}
