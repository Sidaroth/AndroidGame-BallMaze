package com.tiltaball.assignment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class HowToActivity extends Activity {

	public static Typeface typeface;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE); //hide title bar
		 //set app to full screen and keep screen on 
		 getWindow().setFlags(0xFFFFFFFF, LayoutParams.FLAG_FULLSCREEN | LayoutParams.FLAG_KEEP_SCREEN_ON);	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        
	    typeface = Typeface.createFromAsset(getAssets(), "BonvenoCF-Light.otf");  // Create typeface with Custom font. 
	    
	    TextView howToPlayGame1 = (TextView) findViewById(R.id.howToPlayGame1);
	    TextView howToPlayGame2 = (TextView) findViewById(R.id.howToPlayGame2);
	    howToPlayGame1.setTypeface(typeface);
	    howToPlayGame2.setTypeface(typeface);
    }

}