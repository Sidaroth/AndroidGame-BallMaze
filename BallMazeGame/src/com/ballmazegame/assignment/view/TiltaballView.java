package com.ballmazegame.assignment.view;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.ballmazegame.assignment.R;
import com.ballmazegame.assignment.model.Ball;

public class TiltaballView extends Activity implements Observer {

	private Ball mBall;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiltaball_view);
        
        mBall = new Ball();
        mBall.addObserver(this);
    }

    
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}
