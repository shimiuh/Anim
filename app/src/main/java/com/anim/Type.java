package com.anim;


public class Type {
	
	
	/**
	 * By defining this animation type the endValue parameter must be from 1 to 360 to define how much the view will rotate
	 * Parameters can be passed in this order.  int endValue
	 * if no endValue is passed you will get a default 180 rotation.
	 */
	public final static int ROTATE_RIGHT        = 110;
	
	/**
	 * By defining this animation type the end value parameter must be from 1 to 360 to define how much the view will rotate
	 * Parameters can be passed in this order.  int endValue
	 * if no endValue is passed you will get a default 180 rotation.
	 */
	public final static int ROTATE_LEFT         = 111;
	
	/**
	 * By defining this animation type the end value parameter must be from 1 to 360 to define how much the view will rotate
	 * Parameters can be passed in this order.  int endValue
	 * if no endValue is passed you will get a default 180 rotation.
	 */
	public final static int ROTATE_3D_TOP        = 112;
	
	/**
	 * By defining this animation type the end value parameter must be from 1 to 360 to define how much the view will rotate
	 * Parameters can be passed in this order.  int endValue
	 * if no endValue is passed you will get a default 180 rotation.
	 */
	public final static int ROTATE_3D_BOTTOM      = 113;
	
	/**
	 * By defining this animation type the end value parameter must be from 1 to 360 to define how much the view will rotate
	 * Parameters can be passed in this order.  int endValue
	 * if no endValue is passed you will get a default 180 rotation.
	 */
	public final static int ROTATE_3D_RIGHT     = 114;
	
	/**
	 * By defining this animation type the end value parameter must be from 1 to 360 to define how much the view will rotate
	 * Parameters can be passed in this order.  int endValue
	 * if no endValue is passed you will get a default 180 rotation.
	 */
	public final static int ROTATE_3D_LEFT      = 115;
	
	
	/**
	 * By defining this animation type the end value parameter must be from 1 to 100 to define how much the view will fade
	 * Parameters can be passed in this order.  int endValue
	 * if no endValue is passed you will get a default 180 rotation.
	 */
	public final static int FADE                = 116;
	
	
	
	/**
	 * By defining this animation type the view will move up from it's current position to the end value parameter in pixels 
	 */
	public final static int MOVE_UP             = 117;
	
	/**
	 * By defining this animation type the view will move down from it's current position to the end value parameter in pixels 
	 */
	public final static int MOVE_DOWN           = 118;
	
	/**
	 * By defining this animation type the view will move right from it's current position to the end value parameter in pixels 
	 */
	public final static int MOVE_RIGHT          = 119;
	
	/**
	 * By defining this animation type the view will move left from it's current position to the end value parameter in pixels 
	 */
	public final static int MOVE_LEFT           = 1110;
	
	/**
	 * By defining this animation type the views center will move in a straight line from it's current position to a point on the screen 
	 * int x and int y must be passed as parameters in this order
	 */
	public final static int MOVE_TO_POSITION    = 1111;

	
	
	/**
	 * By defining this animation type the view will resize (only from the top of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_TOP          = 1112;
	
	/**
	 * By defining this animation type the view will resize (only top and bottom of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_TOP_BOTTOM   = 1113;
	
	/**
	 * By defining this animation type the view will resize (only top and right of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_TOP_RIGHT    = 1114;
	
	/**
	 * By defining this animation type the view will resize (only top and left of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_TOP_LEFT     = 1115;
	
	/**
	 * By defining this animation type the view will resize (only from the bottom of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_BOTTOM       = 1116;
	
	/**
	 * By defining this animation type the view will resize (only bottom and right of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_BOTTOM_RIGHT = 1117;
	
	/**
	 * By defining this animation type the view will resize (only bottom and left of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_BOTTOM_LEFT  = 1118;
	
	/**
	 * By defining this animation type the view will resize (only from the right of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_RIGHT        = 1119;
	
	/**
	 * By defining this animation type the view will resize (only right and left of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_RIGHT_LEFT   = 1120;
	
	/**
	 * By defining this animation type the view will resize (only from the left of the view) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_LEFT         = 1121;
	
	/**
	 * By defining this animation type the view will resize (from all directions) from it's current size times the end value parameter.
	 * A value of 1 means that no scaling is applied. A value of 2 means that the view will duplicate it self.
	 */
	public final static int RESIZE_ALL          = 1122;
	
	/**
	 * By defining this animation type the views center will move in a circular line from it's current position to a point on the screen 
	 * Point to pas by and Point to end on must be passed as parameters in this order
	 */
	public final static int MOVE_TO_POSITION_CIRCULAR    = 1123;
	
}
