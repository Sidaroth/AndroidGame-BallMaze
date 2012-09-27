package com.ballmazegame.assignment;


import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ballmazegame.assignment.view.TiltaballView;

public class MainActivity extends Activity implements Observer, OnClickListener {

	
	public static Typeface typeface;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	    typeface = Typeface.createFromAsset(getAssets(), "BonvenoCF-Light.otf");  // Create typeface with Custom font. 
	    
	    TextView startGame = (TextView) findViewById(R.id.start_game);
	    TextView howToPlay = (TextView) findViewById(R.id.how_to);
	    TextView credits = (TextView) findViewById(R.id.credits);
	    TextView highScore = (TextView) findViewById(R.id.high_score);
	    TextView quitGame = (TextView) findViewById(R.id.quit_game);
	    
	    startGame.setTypeface(typeface);
	    howToPlay.setTypeface(typeface);		// Set the custom typeface. 
	    credits.setTypeface(typeface);
	    highScore.setTypeface(typeface);
	    quitGame.setTypeface(typeface);
	    
	    // The touch listener changes the color and/or alpha of the button when touched. See TouchListener.java
	    startGame.setOnTouchListener(new TouchListener());
	    howToPlay.setOnTouchListener(new TouchListener());
	    credits.setOnTouchListener(new TouchListener());
	    highScore.setOnTouchListener(new TouchListener());
	    quitGame.setOnTouchListener(new TouchListener());
	    
	    startGame.setOnClickListener(this);
	    howToPlay.setOnClickListener(this);
	    credits.setOnClickListener(this);
	    highScore.setOnClickListener(this);
	    quitGame.setOnClickListener(this);
	    
	    
	}

	// Handles clicks on the menu -> OnClickListener. 
	public void onClick(View v) {
		
		switch(v.getId()){
			case R.id.start_game:
				Intent startGameIntent = new Intent(this, TiltaballView.class);
				startActivity(startGameIntent);
				break;
				
			case R.id.how_to:
				Intent howToIntent = new Intent(this, HowToActivity.class);
				startActivity(howToIntent);
				break;
				
			case R.id.credits:
				Intent derp = new Intent(this, HowToActivity.class); // TEMP
				startActivity(derp);
				break;
				
			case R.id.high_score:
				break;	
				
			case R.id.quit_game:
				finish();
				break;
		}
		
	}

	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
