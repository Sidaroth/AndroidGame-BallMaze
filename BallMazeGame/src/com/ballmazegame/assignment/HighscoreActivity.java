package com.ballmazegame.assignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class HighScoreActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);

        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        
        try{
            // Create a new HTTP Client
            DefaultHttpClient defaultClient = new DefaultHttpClient();
            // Setup the get request
            HttpGet httpGetRequest = new HttpGet("http://game-details.com/tiltaball/highscore.php");

            // Execute the request in the client
            HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
            // Grab the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();

            // Instantiate a JSON object from the request response
            JSONObject  jsonObject = new JSONObject (json);
            JSONArray  highscores = jsonObject.getJSONArray("highscore");
            
	        for(int i=0;i<highscores.length();i++)
	        {						
				HashMap<String, String> map = new HashMap<String, String>();	
				JSONObject e = highscores.getJSONObject(i);
				
				map.put("id",  String.valueOf(i));
	        	map.put("name", "Earthquake name:" + e.getString("eqid"));
	        	map.put("magnitude", "Magnitude: " +  e.getString("magnitude"));
	        	mylist.add(map);			
			}	
            
        } catch(Exception e){
            // In your production code handle any errors and catch the individual exceptions
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.activity_high_score, 
                new String[] { "name", "magnitude" }, 
                new int[] { R.id.item_title, R.id.item_subtitle });

        setListAdapter(adapter);

        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);	
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        		
        		@SuppressWarnings("unchecked")
        		HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	        		
        		Toast.makeText(HighScoreActivity.this, "ID '" + o.get("id") + "' was clicked.", Toast.LENGTH_SHORT).show(); 

        	}
        });
    }
}
