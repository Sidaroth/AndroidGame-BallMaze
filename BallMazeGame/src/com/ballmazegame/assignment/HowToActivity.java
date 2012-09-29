package com.ballmazegame.assignment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class HowToActivity extends Activity {

	public static Typeface typeface;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        
	    typeface = Typeface.createFromAsset(getAssets(), "BonvenoCF-Light.otf");  // Create typeface with Custom font. 
	    
	    TextView howToPlayGame1 = (TextView) findViewById(R.id.howToPlayGame1);
	    TextView howToPlayGame2 = (TextView) findViewById(R.id.howToPlayGame2);
	    TextView howToPlayGame3 = (TextView) findViewById(R.id.howToPlayGame3);
	    howToPlayGame1.setTypeface(typeface);
	    howToPlayGame2.setTypeface(typeface);
	    howToPlayGame3.setTypeface(typeface);
    }

}