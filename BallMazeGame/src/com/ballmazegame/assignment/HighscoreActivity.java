package com.ballmazegame.assignment;

import java.util.ArrayList;
import java.util.HashMap;

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
        	JSONObject json = JSONfunctions.getJSONfromURL("http://game-details.com/tiltaball/highscore.php?");
            JSONArray highscores = json.getJSONArray("highscore");
            System.out.print(highscores);
            
	        for(int i=0;i<highscores.length();i++)
	        {
				HashMap<String, String> map = new HashMap<String, String>();	
				JSONObject e = highscores.getJSONObject(i);
				//fix strings
				map.put("placement",  String.valueOf(i));
	        	map.put("name", "name:" + e.getString("name"));
	        	map.put("time", "time: " +  e.getString("time"));
	        	map.put("location", "location: " +  e.getString("location"));
	        	mylist.add(map);
			}
	        
        } catch(Exception e){
            // In your production code handle any errors and catch the individual exceptions
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(this, mylist , R.layout.activity_high_score, 
                new String[] { "name", "time", "location" }, 
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
