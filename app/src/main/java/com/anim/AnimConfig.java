package com.anim;
import android.util.Log;

import com.facebook.rebound.SpringConfig;


public class AnimConfig {
	
	public final static SpringConfig BOUNCE_BIG_FAST_CONFIG      = new SpringConfig(120, 2);
	public final static SpringConfig BOUNCE_BIG_MEDIUM_CONFIG    = new SpringConfig(80, 2);
	public final static SpringConfig BOUNCE_BIG_SLOW_CONFIG      = new SpringConfig(40, 2);
	public final static SpringConfig BOUNCE_MEDIUM_FAST_CONFIG   = new SpringConfig(60, 3);
	public final static SpringConfig BOUNCE_MEDIUM_MEDIUM_CONFIG = new SpringConfig(40, 3);
	public final static SpringConfig BOUNCE_MEDIUM_SLOW_CONFIG   = new SpringConfig(20, 3);
	public final static SpringConfig BOUNCE_SMALL_FAST_CONFIG    = new SpringConfig(85, 5);
	public final static SpringConfig BOUNCE_SMALL_MEDIUM_CONFIG  = new SpringConfig(55, 5);
	public final static SpringConfig BOUNCE_SMALL_SLOW_CONFIG    = new SpringConfig(25, 4.5);
	public final static SpringConfig OVER_SHOT_FAST_CONFIG       = new SpringConfig(100, 8);
	public final static SpringConfig OVER_SHOT_MEDIUM_CONFIG     = new SpringConfig(35, 7);
	public final static SpringConfig OVER_SHOT_SLOW_CONFIG       = new SpringConfig(15, 5);
	public final static SpringConfig DECELERATION_FAST_CONFIG    = new SpringConfig(85, 11);
	public final static SpringConfig DECELERATION_MEDIUM_CONFIG  = new SpringConfig(40, 8);
	public final static SpringConfig DECELERATION_SLOW_CONFIG    = new SpringConfig(15, 7);
	
	public final static SpringConfig TEST    = SpringConfig.fromBouncinessAndSpeed(1, 1000);
	
	
	public final static SpringConfig ACCELERATION_CONFIG = null;
	private SpringConfig mConfig =   BOUNCE_MEDIUM_MEDIUM_CONFIG;
	private int [] mAnimType = {Type.FADE};
	

	private AnimConfig() {
	}
	/**
	 * Get the default fade animation the default decelerate interpolation and end value off view size
	 * @return new instance
	 */
	public static AnimConfig set(){
		AnimConfig result = new AnimConfig();
		return result;
	}
	/**
	 * Get the default decelerate interpolation 
	 * @param animType AnimConfig.TYPE
	 * @return new instance
	 */
	public static AnimConfig set(int... animType){
		AnimConfig result = new AnimConfig();
		result.mAnimType  = animType;
		return result;
	}
	
	/**
	 * @param config Rebound SpringConfig
	 * @param animType AnimConfig.TYPE
	 * @return new instance
	 */
	public static AnimConfig set(SpringConfig config ,int... animType){
		Log.d("shimi", "in AnimConfig set animType = "+animType);
		AnimConfig result = new AnimConfig();
		result.mConfig    = config;
		result.mAnimType  = animType;
		return result;
	}
	
	/**
	 * @param tension tension value for the SpringConfig
     * @param friction friction value for the SpringConfig
	 * @param animType AnimConfig.TYPE.
	 * @return new instance
	 */
	public static AnimConfig set(double tension, double friction, int... animType ){
		AnimConfig result = new AnimConfig();
		result.mConfig    = new SpringConfig(tension, friction);
		result.mAnimType  = animType;
		return result;
	}
	
	public SpringConfig getConfig() {
		return mConfig;
	}
	
	public int [] getAnimType() {
		return mAnimType;
	}
	
    /**
     * @param spring 
     * @return this
     */
	public void setConfig(SpringConfig spring) {
		this.mConfig = spring;
	}
	
  /**
   * setting the SpringConfig
   * @param tension tension value for the SpringConfig
   * @param friction friction value for the SpringConfig
   */
	public AnimConfig setConfig(double tension, double friction) {
		this.mConfig = new SpringConfig(tension, friction);
		return this;
	}
	
	/**
	 * create new AnimConfig from AnimConfig
	 * @param config
	 * @return
	 */
	public static AnimConfig clone(AnimConfig config){
		AnimConfig result = new AnimConfig();
		result.mConfig    = config.mConfig;
		result.mAnimType  = config.mAnimType;
		return result;
	}
}
