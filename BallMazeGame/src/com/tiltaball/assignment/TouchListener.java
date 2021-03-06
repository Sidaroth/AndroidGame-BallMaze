package com.tiltaball.assignment;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

// Custom touch listener that changes the colour of the text on the main menu. 
public class TouchListener implements View.OnTouchListener 
{
	public boolean onTouch(View view, MotionEvent motionEvent)
	{
		switch(motionEvent.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				((TextView) view).setTextColor(0xCCFFFFFF);	// Sets the color a transparent white color on click
				break;
				
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				((TextView) view).setTextColor(0xFF000000); // Sets the color to black
				break;
		}
		
		return false;
	}
}