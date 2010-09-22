package com.tiltdev.multitouchtest.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TouchSurfaceView extends GLSurfaceView {
    public TouchSurfaceView(Context context) {
    	super(context);
    }
	
	public TouchSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private float[] mX = { 0.0f };
	private float[] mY = { 0.0f };
	
	public float[] getXPoints() {
		return mX;
	}
	public float[] getYPoints() {
		return mY;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mX = new float[event.getPointerCount()];
		mY = new float[event.getPointerCount()];
		
		for(int i = 0; i < event.getPointerCount(); i++) {
			mX[i] = event.getX(i)/getWidth()*2;
			mY[i] = event.getY(i)/getHeight()*2;
		}
		return true;
	}
}
