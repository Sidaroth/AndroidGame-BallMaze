package com.ballmazegame.assignment.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.ballmazegame.assignment.controller.BallController;
import com.ballmazegame.assignment.model.BallModel;

public class TiltaballView extends Activity implements SensorEventListener, Observer {

	private DrawView mDrawView;
	private boolean mInitialized;	
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	float currX, currY, lastX, lastY;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE); //hide title bar
		 //set app to full screen and keep screen on 
		 getWindow().setFlags(0xFFFFFFFF, LayoutParams.FLAG_FULLSCREEN | LayoutParams.FLAG_KEEP_SCREEN_ON);		
		 super.onCreate(savedInstanceState); 
		 
//		 super.onCreate(savedInstanceState);
	     
	     mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	     mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Linear excludes gravity. 
	     
	     // See comment for onResume();
	     mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	        
	     mInitialized = false;
	     
	     
	     mDrawView = new DrawView(this);
	     mDrawView.mController.mBallModel.addObserver(this);
	       
	     setContentView(mDrawView);
	 }
	     
	
    public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		mDrawView.invalidate();
	}
	
	
	// This is called each time the sensor gets new data. 
     public void onSensorChanged(SensorEvent event) {
    	 currX = event.values[0];
    	 currY = event.values[1];
    	 
 		
    	 if ( !mInitialized )
    	 {
    		 mInitialized = true;  		 
    		 // Rearrange coordinate system here? 
    	 }
    	 else
    	 {
  		 	mDrawView.mController.moveBall(currX, currY);
    	 }
     }
 	


     
     
	
	// Accuracy will not be changed during runtime, hence empty. 
	public void onAccuracyChanged(Sensor arg0, int arg1) {
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
