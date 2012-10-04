package com.ballmazegame.assignment;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ballmazegame.assignment.controller.BallController;
import com.ballmazegame.assignment.view.TiltaballView;

public class EndGameActivity extends Activity {

	String score;
	String name;
	Typeface typeface;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        
        typeface = Typeface.createFromAsset(getAssets(), "BonvenoCF-Light.otf");
        
        Intent intent = getIntent();
        score = intent.getStringExtra(BallController.SCORE);
        
        TextView highscore_main = (TextView) findViewById(R.id.highscore_main);
	    TextView submitted = (TextView) findViewById(R.id.submitted);
	    
	    highscore_main.setTypeface(typeface);
	    submitted.setTypeface(typeface);		// Set the custom typeface. 
        
        highscore_main.setText("Your score was : " + score + "\n\nEnter a name and press submit to check for highscore!");
    }
    
    // Does the networking in a background thread. 
	private class DataSend extends AsyncTask <Void, Void, Void> 
	{
		@Override
		protected Void doInBackground(Void... params) {
			send_data();
			return null;
		}
	}
	
	// creates a get request and executes it. 
	private void send_data()
	{
    	DefaultHttpClient httpClient = new DefaultHttpClient();
    	
    	String coreUrl = "http://game-details.com/tiltaball/insert.php?";
        String urlToSend = coreUrl + "name=" + name + "&" + "time=" + score;
    	
    	System.out.println(urlToSend);
    	
    	HttpGet get = new HttpGet(urlToSend);
    	
    	try 
    	{
			httpClient.execute(get);
		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    	
	}
	
	// Checks to see if the user is connected to the Internet
	public boolean isOnline()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			
		return (networkInfo != null && networkInfo.isConnected());
	}
	
	public void Submit(View view)
	{
		EditText nameEdit = (EditText) findViewById( R.id.namefield );
		name = nameEdit.getText().toString();
		
		if(isOnline())
		{
			System.out.println("derp");
			new DataSend().execute();
			
			Intent intent = new Intent(this, TiltaballView.class);
			startActivity(intent);
		}
	}
	
	@Override
	public void onBackPressed() {
		   Intent intent = new Intent(this, MainActivity.class);
		   startActivity(intent);
	}
	
	public void Quit(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
