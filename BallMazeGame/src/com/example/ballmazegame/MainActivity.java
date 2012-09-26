package com.example.ballmazegame;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	public static Typeface typeface;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	    typeface = Typeface.createFromAsset(getAssets(), "BonvenoCF-Light.otf");
	    
	    TextView startGame = (TextView) findViewById(R.id.start_game);
	    TextView howToPlay = (TextView) findViewById(R.id.how_to);
	    TextView credits = (TextView) findViewById(R.id.credits);
	    TextView highScore = (TextView) findViewById(R.id.high_score);
	    TextView quitGame = (TextView) findViewById(R.id.quit_game);
	    
	    startGame.setTypeface(typeface);
	    howToPlay.setTypeface(typeface);
	    credits.setTypeface(typeface);
	    highScore.setTypeface(typeface);
	    quitGame.setTypeface(typeface);
	    
	    
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
    }
}
