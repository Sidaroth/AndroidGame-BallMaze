package com.ballmazegame.assignment.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.ballmazegame.assignment.R;
import com.ballmazegame.assignment.controller.BallController;
public class DrawView extends View {

	Paint paint;
	Resources res;
	Bitmap maze;
	int viewWidth, viewHeight;
	
	public BallController mController;
	
	public DrawView(Context context) {
		super(context);
		paint = new Paint();
		Resources res = this.getResources(); 
		maze = BitmapFactory.decodeResource(res, R.drawable.maze1); // TODO this has to be fix'd to take an argument ID instead of hardcoded maze1. 
		
		mController = new BallController();
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		// Draw entire screen white. 
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
		
		canvas.drawBitmap(maze, 0, 0, paint);
		
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		
		paint.setTextSize(30);
		canvas.drawText(Float.toString(mController.mBallModel.getX()) + " , " + Float.toString(mController.mBallModel.getY()), 800, 200, paint);
		canvas.drawCircle(mController.mBallModel.getX(), mController.mBallModel.getY(), mController.mBallModel.getRadius(), paint);
	}
	
	@Override
	 protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
	     super.onSizeChanged(xNew, yNew, xOld, yOld);

	     viewWidth = xNew;
	     viewHeight = yNew;
	     
	     mController.updateViewSize(viewWidth, viewHeight);
	}
}
