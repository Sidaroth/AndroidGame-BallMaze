package com.ballmazegame.assignment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


// This activity shows the credits page. 
public class CreditsActivity extends Activity {

	public static Typeface typeface;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        
        typeface = Typeface.createFromAsset(getAssets(), "BonvenoCF-Light.otf");  // Create typeface with Custom font. 
	    
	    TextView creditsMain = (TextView) findViewById(R.id.creditsMain);
	    TextView creators = (TextView) findViewById(R.id.creators);
	    creditsMain.setTypeface(typeface);
	    creators.setTypeface(typeface);
        
    }


}
