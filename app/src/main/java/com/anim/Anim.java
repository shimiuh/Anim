package com.anim;
import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;


public class Anim {
	public final static BaseSpringSystem SPRING_SYSTEM = SpringSystem.create();
	
	private List<AnimManager>      mSpringWrapList;
	
    private Anim() {
    	this.mSpringWrapList = new ArrayList<AnimManager>();
    }
    

    /**
     * @return new instance
     * @throws Exception 
     */
    public static Anim get()  {
    	Anim result = new Anim();
        return result;
    }
    
//    /**
//     * Get the default fade animation with end value off 0
//     * @param view to animate 
//     * @return this
//     */
//    public Anim addAnimation(View view){
//    	this.mSpringWrap.add(getAnimConfigWrap(view, AnimConfig.set() , null, null, null));
//		return this;
//    }
//    
//    /**
//     * Get the default end value off view size and center pivot
//     * @param animation config AnimConfig.set
//     * @param view to animate 
//     * @return this
//     */
//    public Anim addAnimation(View view, AnimConfig config){
//    	this.mSpringWrap.add(getAnimConfigWrap(view, AnimConfig.clone(config) , null, null, null));
//		return this;
//    }
    
    /**
     * Check the config Type to see what parameters can be passed after View and AnimConfig 
     * @param view to animate
     * @return this
     */
    public Anim addAnimation(View view, AnimConfig config, Object... params){
    	this.mSpringWrapList.add(getAnimConfigWrap(view, AnimConfig.clone(config) , params));
		return this;
    }
    
    
    public Anim addAnimation(AnimManager configWrap){
    	this.mSpringWrapList.add(configWrap);
		return this;
    }
    
    /**
     * will set the animation to continue infinitely by reversing the animation on animation end
     */
    public void setInfinite(){
		for(int i=0; i < mSpringWrapList.size(); i++){
			mSpringWrapList.get(i).setInfinite();
		}
    }

    public void start(){
		for(int i=0; i < mSpringWrapList.size(); i++){
			mSpringWrapList.get(i).getSpring()
		        .setCurrentValue(0)
		        .setEndValue(1);
		}
    }
	
    public void toggle() {
		for(int i=0; i < mSpringWrapList.size(); i++){
			mSpringWrapList.get(i).toggle();
		}
	 }
    
    public void removeListeners(){
		for(int i=0; i < mSpringWrapList.size(); i++){
			mSpringWrapList.get(i).removeSpringListener();
		}
    }
    
    public void addListeners(){
		for(int i=0; i < mSpringWrapList.size(); i++){
			mSpringWrapList.get(i).addSpringListener();
		}
    }
    /**
     * @param run Runnable to run On Animation Ends 
     * @param animPosition starting at 0 the # off animation added 
     * @return this
     * @throws IndexOutOfBoundsException
     *                if {@code animPosition < 0 || animPosition >= amount off animations added}
     */
    public Anim setOnAnimationEnd(Runnable run ,int animPosition){
		mSpringWrapList.get(animPosition).setOnAnimationEnd(run);
		return this;
    }
    
    public AnimManager getAnimConfigWrap(View view, AnimConfig config, Object... params) {
		return new AnimManager(view, config, params);
	  }
	
}
