package com.ballmazegame.assignment.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.ballmazegame.assignment.controller.BallController;
import com.ballmazegame.assignment.model.BallModel;

public class TiltaballView extends Activity implements Observer, SensorEventListener {

	private BallModel mBall;
	private BallController mController;
	private boolean mInitialized;		
	private DrawView mDrawView;
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	float currX, currY, lastX, lastY;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     mDrawView = new DrawView(this);
	     
	     
	     mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	     mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR); // Linear excludes gravity. 
	     
	     // See comment for onResume();
	     mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	         
	     mInitialized = false;
	     
	     mDrawView = new DrawView(this);
	     mBall = new BallModel();
	     mController = new BallController(mBall);
	     
	     mBall.addObserver(this);
	     
	     setContentView(mDrawView);
	 }
	     
	
     public void onSensorChanged(SensorEvent event) {
    	 currX = event.values[0];
    	 currY = event.values[1];
    	 
		 
    	 if ( !mInitialized )
    	 {
    		 lastX = currX;
    		 lastY = currY;
    		 mInitialized = true;
    		 
    		 // Rearrange coordinate system here? 
    	 }
    	 else
    	 {
    		 mController.moveBall(currX, currY);
    	 }
     }

    
     // How to get drawview to update the position without replicating the model?...
	public void update(Observable observable, Object data) {
		// ÆSJ!
		mDrawView.updateBall(mBall);
		
		// This is as it should be. 
		mDrawView.invalidate();
		
	}


	
	// Accuracy will not be changed during runtime, hence empty. 
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	// Starts listening for accelerometer events on a rate suitable for games
	// If need be, this rate can be slowed down or sped up. (Normal, Fastest, ...)
	protected void onResume()
	{
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	}
	
	// Need to disable the sensor to avoid battery drainage.  
	protected void onPause()
	{
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
	
	protected void onStop()
	{
		super.onStop();
		mSensorManager.unregisterListener(this);
	}

}
