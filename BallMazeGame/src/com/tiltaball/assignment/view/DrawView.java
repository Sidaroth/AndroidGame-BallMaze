package com.tiltaball.assignment.view;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.tiltaball.assignment.controller.BallController;

 // This class handles the drawing of the ball and the maze bitmap. 
 // On changes to the model, it redraws itself. 
public class DrawView extends View implements Observer {

	Paint paint;
	Resources res;
	Bitmap maze;
	int mViewWidth;
	int mViewHeight;
	
	public BallController mController;
	
	public DrawView(Context context) {
		super(context);
		paint = new Paint();
		
		mController = new BallController(context);
		mController.mBallModel.addObserver(this);
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		// Draw entire screen white. 
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
				
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		
		paint.setTextSize(30);
		canvas.drawText("Score: " + Float.toString(mController.mBallModel.getScore()), 25, 25, paint);
		paint.setColor(Color.RED);
		canvas.drawCircle(mController.mBallModel.getX(), mController.mBallModel.getY(), mController.mBallModel.getRadius(), paint);
	}
	
	@Override
	 protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
	     super.onSizeChanged(xNew, yNew, xOld, yOld);

	     mViewWidth = xNew;
	     mViewHeight = yNew;
	     
	     mController.updateViewSize(mViewWidth, mViewHeight);
	}


	// On changes to the model, invalidates the currently drawn image, which in turn redraws
	// the view with the new model data. 
	public void update(Observable observable, Object data) {
		this.invalidate();
	}
}
