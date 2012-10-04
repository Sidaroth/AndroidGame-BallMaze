package com.ballmazegame.assignment.view;

import com.ballmazegame.assignment.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;


// This class handles the user "input" aka. the sensor input
// I can't currently find a way to get this done inside DrawView - so for now at least, we need 1 view and 1 activity. 

public class TiltaballView extends Activity implements SensorEventListener {

	private DrawView mDrawView;
	private boolean mInitialized;	
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	float currX, currY;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE); //hide title bar
		 //set app to full screen and keep screen on 
		 getWindow().setFlags(0xFFFFFFFF, LayoutParams.FLAG_FULLSCREEN | LayoutParams.FLAG_KEEP_SCREEN_ON);		
		 super.onCreate(savedInstanceState); 
		 
//		 super.onCreate(savedInstanceState);
	     
	     mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	     mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // Accesses the accelerometer.
	     
	     // See comment for onResume();
	     mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	     
	     mInitialized = false;
	     
	     mDrawView = new DrawView(this);
	     setContentView(mDrawView);
	 }
	
//	@Override
//	public void onBackPressed() {
//	   Intent setIntent = new Intent(this, MainActivity.class);
//	   startActivity(setIntent);
//	}
	
	// This is called each time the sensor gets new data. 
     public void onSensorChanged(SensorEvent event) {
    	 currX = event.values[0];
    	 currY = event.values[1];
    	 
 		
    	 if ( !mInitialized )
    	 {
    		 mInitialized = true;  		 
    		 // Do some initialization here?
    	 }
    	 else
    	 {
  		 	mDrawView.mController.moveBall(currX, currY);
    	 }
     }
     
    public void onBackPressed()
    {
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
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
	
	// We don't really care to much if the accuracy is changed, hence empty. 
	public void onAccuracyChanged(Sensor arg0, int arg1) {
	}

}
