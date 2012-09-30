package com.ballmazegame.assignment.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.ballmazegame.assignment.R;
import com.ballmazegame.assignment.model.Ball;

public class TiltaballView extends Activity implements Observer, SensorEventListener {

	private Ball mBall;
	private float mLastX, mLastY;
	private boolean mInitialized;		
	
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	private final float NOISE = 0.2f;	// Used to see if the user just has spasms, or is actually trying to do stuff. 
	
	     
	@Override
	public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.activity_tiltaball_view);
	     
	     mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	     mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR); // Linear excludes gravity. 
	     
	     // Same as onResume();
	 	 // Starts listening for accelerometer events on a rate suitable for games
	 	 // If need be, this rate can be slowed down or sped up. (Normal, Fastest, ...)
	     mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
	     
	     
	     mInitialized = false;
	     
	     mBall = new Ball();
	     mBall.addObserver(this);
	 }
	 
	 
     public void onSensorChanged(SensorEvent event) {
    	 TextView tvX = (TextView) findViewById(R.id.x_axis);
    	 TextView tvY = (TextView) findViewById(R.id.y_axis);
    	 TextView tvZ = (TextView) findViewById(R.id.z_axis);
    	 
    	 float x = event.values[0];
    	 float y = event.values[1];
    	 float z = event.values[2];
    	 
    	 if ( !mInitialized )
    	 {
    		 mLastX = x;
    		 mLastY = y;
 
    		 tvX.setText("0.0");
    		 tvY.setText("0.0");
    		 tvZ.setText("0.0");
    		 
    		 mInitialized = true;
    	 }
    	 else
    	 {
    		 
//    		 All values are angles in degrees. 
//
//    		 values[0]: Azimuth, angle between the magnetic north direction and the y-axis, around the z-axis (0 to 359). 0=North, 90=East, 180=South, 270=West 
//
//    		 values[1]: Pitch, rotation around x-axis (-180 to 180), with positive values when the z-axis moves toward the y-axis. 
//
//    		 values[2]: Roll, rotation around y-axis (-90 to 90), with positive values when the x-axis moves toward the z-axis.
    		
    		
    		 tvX.setText( "X: " + Float.toString( x ));
    		 tvY.setText( "Y: " + Float.toString( y ));
    		 tvZ.setText( "Z: " + Float.toString( z ));
    		 
//    		 float deltaX = mLastX - x;
//    		 float deltaY = mLastY - y;
//    		 
//    		 if ( deltaX < NOISE && deltaX > ( NOISE * -1 ))
//    		 {
//    			 deltaX = 0.0f;
//    		 }
//    		 
//    		 if ( deltaY < NOISE && deltaY > ( NOISE * -1 ))
//    		 {
//    			 deltaY = 0.0f;
//    		 }
//    		 
//    		 if(deltaX != 0.0f)
//    		 {
//    			 System.out.println(System.out.append("X: " + Float.toString(deltaX)));
//    		 }
//    		
//    		 if(deltaY != 0.0f)
//    		 {
//    			 System.out.println(System.out.append("Y: " + Float.toString(deltaY)));
//    		 }
    		 
    	 }
     }

    
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}


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
