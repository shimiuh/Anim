package com.anim;
import java.util.Arrays;
import java.util.HashMap;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

import com.anim.utils.Circle;
import com.anim.utils.Size;
import com.anim.utils.Utils;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AnimManager {
	
	private Spring mSpring;
    public Spring getSpring() {
		return mSpring;
	}

    private HashMap<Integer, Float> mEndValueList  = new HashMap<Integer, Float>();
	public HashMap<Integer, Float> getEndValueList() {
		return mEndValueList;
	}
	
	private int[] mTypeList;
	public int [] getTypeList() {
		return mTypeList;
	}
	
	private AnimSpringListener mSpringListener;
	public AnimSpringListener getSpringListener() {
		return mSpringListener;
	}
	
	private View mView;
	private Point mMoveToPoint;
	private Point mViewOriginCenterPoint;
	protected Size mViewSize;
	private int mParamsIncreaser;
	private Point mPasByPointPoint;
	private Circle mCircle;
	public View getView() {
		return mView;
	}

	


	public AnimManager(View view, AnimConfig config, Object... params) {
		 init(view, config, params);
	}

	private void init(View view, AnimConfig config, Object... params){
		  this.mView            = view;
  		  this.mSpring          = Anim.SPRING_SYSTEM.createSpring().setSpringConfig(config.getConfig());
  		  this.mTypeList            = config.getAnimType();
  		  this.mSpringListener  = new AnimSpringListener();
  		  this.mView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
			mView.getViewTreeObserver().removeOnPreDrawListener(this);
			     int [] location = new int[2];
			     mView.getLocationOnScreen(location);
			     mViewOriginCenterPoint = new Point(location[0] + mView.getWidth()/2, location[1] + mView.getHeight()/2 );
			     mViewSize = new Size(mView.getWidth(), mView.getHeight());
			     if(mPasByPointPoint != null){
				     mCircle = Utils.circleFromPoints(mViewOriginCenterPoint,  mMoveToPoint,  mPasByPointPoint);
				     Log.d("shimi", "in initMoveToPointCircularParams  mViewOriginCenterPoint = "+mViewOriginCenterPoint+
								"  mMoveToPoint "+mMoveToPoint +"  mPasByPointPoint = "+mPasByPointPoint+"   mCircle = "+mCircle+
								"  mViewSize = "+mViewSize.toString()+" toMove = "+Math.abs(mViewOriginCenterPoint.x-mMoveToPoint.x)+
								"  location = "+Arrays.toString(location)); 
			     }

					
				return false;
			}
		});
  		  addSpringListener();
  		  config = null;
  		  mParamsIncreaser = 0;
  		  for(int i =0; i < mTypeList.length;i++){
  			initParams(mTypeList[i],params);
  		  }
	}

	private void initParams(int type, Object[] params) {
		switch (type) {
		case Type.ROTATE_RIGHT:
		case Type.ROTATE_LEFT:
		case Type.ROTATE_3D_TOP:
		case Type.ROTATE_3D_BOTTOM:
		case Type.ROTATE_3D_RIGHT:
		case Type.ROTATE_3D_LEFT:
			initRotateParams(params, type);
			break;
		case Type.FADE:
			initFadeParams(params);
			break;
		case Type.MOVE_UP:
		case Type.MOVE_DOWN:
		case Type.MOVE_RIGHT:
		case Type.MOVE_LEFT:
			initMoveParams(params, type);
		case Type.MOVE_TO_POSITION:
			initMoveToPointParams(params);
			break;
		case Type.MOVE_TO_POSITION_CIRCULAR:
			initMoveToPointCircularParams(params);
			break;
		case Type.RESIZE_ALL:
		case Type.RESIZE_TOP:
		case Type.RESIZE_TOP_BOTTOM:
		case Type.RESIZE_TOP_RIGHT:
		case Type.RESIZE_TOP_LEFT:
		case Type.RESIZE_BOTTOM:
		case Type.RESIZE_BOTTOM_RIGHT:
		case Type.RESIZE_BOTTOM_LEFT:
		case Type.RESIZE_RIGHT:
		case Type.RESIZE_RIGHT_LEFT:
		case Type.RESIZE_LEFT:
			initResizeParams(params, type);
			break;

		default:
			break;
		}
		
	}




	private void initMoveToPointCircularParams(Object[] params) {
		if(params.length - mParamsIncreaser < 2 || !(params[mParamsIncreaser] instanceof Point) || !(params[mParamsIncreaser+1] instanceof Point)){
			throw new RuntimeException("The paramiters for this animation type must be Point to pas by and Point to end on");
		}
		mPasByPointPoint = (Point)params[mParamsIncreaser];
		mMoveToPoint     = (Point)params[mParamsIncreaser+1];
		mParamsIncreaser+=2;
	}




	private void initMoveToPointParams(Object[] params){
		if(params.length - mParamsIncreaser < 2 || !(params[mParamsIncreaser] instanceof Integer) || !(params[mParamsIncreaser+1] instanceof Integer)){
			throw new RuntimeException("The paramiters for this animation type must be int x and int y");
		}
		mMoveToPoint = new Point((int)params[mParamsIncreaser], (int)params[mParamsIncreaser+1]);
		mParamsIncreaser+=2;
	}




	private void initResizeParams(Object[] params,int type) {
		initRotateParams(params,type);
	}




	private void initMoveParams(Object[] params, int type) {
		initRotateParams(params, type);
	}




	private void initFadeParams(Object[] params) {
		
	}




	private void initRotateParams(Object[] params, int type) {
		Log.d("shimi", "in initRotateParams type = "+type+"  mParamsIncreaser = "+mParamsIncreaser+"  "+Arrays.toString(params));
		if(params.length - mParamsIncreaser == 0){
			this.mEndValueList.put(type, 180f);
			return;
		}
	    this.mEndValueList.put(type, getParamFromObject(params[mParamsIncreaser]));
		mParamsIncreaser++;
	}




	private float getParamFromObject(Object tempParam) {
		float param = 0 ;
		  if(tempParam instanceof Float){
			param = (float)tempParam;
		  } else if(tempParam instanceof Integer){
			param = new Float((int)tempParam);
		  } else{
			throw new RuntimeException("paramiters must be pased int or float");
		  }
		return param;
	}




	public void removeSpringListener(){
    	this.mSpring.removeAllListeners();
    }
    public void addSpringListener(){
    	this.mSpring.removeAllListeners();
    	this.mSpring.addListener(this.mSpringListener);
    }
    
    public class AnimSpringListener extends SimpleSpringListener {

		@Override
		public void onSpringUpdate(Spring spring) {
	  		  for(int i =0; i < mTypeList.length;i++){
	  			  onSpringUpdate(spring, mTypeList[i]);
	    		  }
			super.onSpringUpdate(spring);
		}

		private void onSpringUpdate(Spring spring, int type) {
			switch (type) {
			case Type.ROTATE_RIGHT:
			case Type.ROTATE_LEFT:
			case Type.ROTATE_3D_TOP:
			case Type.ROTATE_3D_BOTTOM:
			case Type.ROTATE_3D_RIGHT:
			case Type.ROTATE_3D_LEFT:
				rotate(spring, type);
				break;
			case Type.FADE:
				fade(spring, type);
				break;
			case Type.MOVE_UP:
			case Type.MOVE_DOWN:
			case Type.MOVE_RIGHT:
			case Type.MOVE_LEFT:
				move(spring, type);
				break;
			case Type.MOVE_TO_POSITION:
				moveToPoint(spring);
				break;
			case Type.MOVE_TO_POSITION_CIRCULAR:
				moveToPointCircular(spring);
				break;
			case Type.RESIZE_ALL:
			case Type.RESIZE_TOP:
			case Type.RESIZE_TOP_BOTTOM:
			case Type.RESIZE_TOP_RIGHT:
			case Type.RESIZE_TOP_LEFT:
			case Type.RESIZE_BOTTOM:
			case Type.RESIZE_BOTTOM_RIGHT:
			case Type.RESIZE_BOTTOM_LEFT:
			case Type.RESIZE_RIGHT:
			case Type.RESIZE_RIGHT_LEFT:
			case Type.RESIZE_LEFT:
				resize(spring, type);
				break;

			default:
				break;
			}
		}

		@Override
		public void onSpringAtRest(Spring spring) {
			Log.d("shimi", "in onAnimEnd "+spring.getCurrentValue());
            if(mOnAnimationEnd != null ){
            	mOnAnimationEnd.run();
            }
            if(mIsInfinite){
            	toggle();
            }
			super.onSpringAtRest(spring);
		}

		@Override
		public void onSpringActivate(Spring spring) {
			Log.d("shimi", "in onAnimStart "+spring.getCurrentValue());
			super.onSpringActivate(spring);
		}

		@Override
		public void onSpringEndStateChange(Spring spring) {
			Log.d("shimi", "in onSpringEndStateChange "+spring.getCurrentValue());
			super.onSpringEndStateChange(spring);
		}
      }

    float lastVal = 0;
	private Runnable mOnAnimationEnd;
	private boolean mIsInfinite;
	public void move(Spring spring, int type) {
		float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0,  mEndValueList.get(type) );
		boolean ShouldMoveX = false;
		boolean ShouldMoveY = false;
		switch (type) {
		
		case Type.MOVE_UP:
			mappedValue *= -1;
		    ShouldMoveY = true;
			break;
		case Type.MOVE_DOWN:
			ShouldMoveY = true;
			break;
		case Type.MOVE_RIGHT:
			ShouldMoveX = true;
			break;
		case Type.MOVE_LEFT:
			mappedValue *= -1;
			ShouldMoveX = true;
			break;

		default:
			break;
		}
		if(ShouldMoveX){
			 mView.setTranslationX(mappedValue);
		}
		if(ShouldMoveY){
			mView.setTranslationY(mappedValue);
		}
	}
	
	
	public void moveToPoint(Spring spring) {
		
		int oldX = mViewOriginCenterPoint.x;
		int oldY = mViewOriginCenterPoint.y;
		int newX = mMoveToPoint.x;
		int newY = mMoveToPoint.y;
		float mappedXvalue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0,  Math.abs(oldX - newX ));
		float mappedYvalue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0,  Math.abs(oldY - newY ) );
		if(oldX > newX){
			mappedXvalue *= -1;
			mView.setTranslationX(mappedXvalue);
		}else{
			mView.setTranslationX(mappedXvalue);
		}
		
		if(oldY > newY){
			mappedYvalue *= -1;
			mView.setTranslationY(mappedYvalue);
		}else{
			mView.setTranslationY(mappedYvalue);
		}
	    
	}
	
	private void moveToPointCircular(Spring spring) {
		int oldX = mViewOriginCenterPoint.x;
		int oldY = mViewOriginCenterPoint.y;
		int newX = mMoveToPoint.x;
		int newY = mMoveToPoint.y;
		double currentValue = spring.getCurrentValue();
		
		float mappedXvalue = (float) SpringUtil.mapValueFromRangeToRange(currentValue, 0, 1, 0,  Math.abs(oldX - newX ) );
		//float mappedYvalue = (float) SpringUtil.mapValueFromRangeToRange(currentValue, 0, 1, 0,  Math.abs(oldY - newY ) );
		float mappedYvalue = Math.abs(oldY - (float) Utils.getCircularY((int) mappedXvalue, mCircle.center, (float) mCircle.radius));
	

		if(oldX > newX){
			mappedXvalue *= -1;
		}
		mView.setTranslationX(mappedXvalue);
		
		if(oldY > newY){
			mappedYvalue *= -1;
		}
		mView.setTranslationY(mappedYvalue);
		
		int [] location = new int [2];
		mView.getLocationOnScreen(location);
//		Log.d("shimi", "currentValue = "+currentValue+"  mappedXvalue = "+mappedXvalue+"  mappedYvalue = "+
//		                mappedYvalue+"  location = "+Arrays.toString(location));
	}




	public void fade(Spring spring, int type) {
		float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0, mEndValueList.get(type));
		mView.setAlpha(mappedValue);
	}



	public void rotate(Spring spring, int type) {
		float mappedValue;
		boolean ShouldRotateX = false;
		boolean ShouldRotateY = false;
		boolean ShouldMapNegative = false;
		switch (type) {
		case Type.ROTATE_RIGHT:
			break;
		case Type.ROTATE_LEFT:
			ShouldMapNegative = true;
			break;
		case Type.ROTATE_3D_TOP:
			ShouldRotateX = true;
			ShouldMapNegative = true;
			break;
		case Type.ROTATE_3D_BOTTOM:
			ShouldRotateX = true;
			break;
		case Type.ROTATE_3D_RIGHT:
			ShouldRotateY = true;
			ShouldMapNegative = true;
			break;
		case Type.ROTATE_3D_LEFT:
			ShouldRotateY = true;
			break;
		}
		if(ShouldMapNegative){
			mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0, -mEndValueList.get(type));
		}else{
			mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 0, mEndValueList.get(type));
		}
		if(ShouldRotateY){
			mView.setRotationY(mappedValue);
		}else if(ShouldRotateX){
			mView.setRotationX(mappedValue);
		} else{
			mView.setRotation(mappedValue);
		}
		//mView.setCameraDistance(mappedValue);
	}



	public void resize(Spring spring, int type) {
		//Log.d("shimi", "in resize type = "+type+"   mEndValueList.get(type) = "+mEndValueList.get(type));
		float mappedValue = (float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(), 0, 1, 1, mEndValueList.get(type));
		boolean ShouldScaleX = false;
		boolean ShouldScaleY = false;
		int width  = mView.getWidth();
		int height = mView.getHeight();
		switch (type) {
		
		case Type.RESIZE_ALL:
			ShouldScaleX = true;
		    ShouldScaleY = true;
		    mView.setPivotX(width/2);
		    mView.setPivotY(height/2);
			break;
		case Type.RESIZE_TOP:
			mView.setPivotY(height);
			ShouldScaleY = true;
			break;
		case Type.RESIZE_TOP_BOTTOM:
			 mView.setPivotY(width/2);
			ShouldScaleY = true;
			break;
		case Type.RESIZE_TOP_RIGHT:
			mView.setPivotY(height);
		    mView.setPivotX(1);
			ShouldScaleX = true;
			ShouldScaleY = true;
			break;
		case Type.RESIZE_TOP_LEFT:
			mView.setPivotY(height);
		    mView.setPivotX(width);
			ShouldScaleX = true;
			ShouldScaleY = true;
			break;
		case Type.RESIZE_BOTTOM:
			mView.setPivotY(1);
			ShouldScaleY = true;
			break;
		case Type.RESIZE_BOTTOM_RIGHT:
		    mView.setPivotY(1);
		    mView.setPivotX(1);
			ShouldScaleY = true;
			ShouldScaleX = true;
			break;
		case Type.RESIZE_BOTTOM_LEFT:
			mView.setPivotY(1);
			mView.setPivotX(width);
			ShouldScaleY = true;
			ShouldScaleX = true;
			break;
		case Type.RESIZE_RIGHT:
			mView.setPivotX(1);
			ShouldScaleX = true;
			break;
		case Type.RESIZE_RIGHT_LEFT:
			mView.setPivotX(width/2);
			ShouldScaleX = true;
			break;
		case Type.RESIZE_LEFT:
			mView.setPivotX(width);
			ShouldScaleX = true;
			break;
		default:
			break;
		}
		if(ShouldScaleX){
			 mView.setScaleX(mappedValue);
		}
		if(ShouldScaleY){
			mView.setScaleY(mappedValue);
		}
	     
	      
	}




	public void setOnAnimationEnd(Runnable run) {
		mOnAnimationEnd = run;
	}




	public void toggle() {
		Spring spring = getSpring();
	    double currentValue = spring.getEndValue();
	    spring.setEndValue(currentValue == 1 ? 0 : 1);
	}




	public void setInfinite() {
		mIsInfinite = true;
	}
}
