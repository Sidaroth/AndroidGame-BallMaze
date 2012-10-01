package com.ballmazegame.assignment.controller;

import java.util.Timer;
import java.util.TimerTask;

import com.ballmazegame.assignment.model.BallModel;


public class BallController {
	
	public BallModel mBallModel;
	private float POS_NOISE_FILTER; 
	private float NEG_NOISE_FILTER;
	private int mViewWidth, mViewHeight;
	
	
	
	public BallController()
	{
		mBallModel = new BallModel();
		POS_NOISE_FILTER = 0.75f;
		NEG_NOISE_FILTER = POS_NOISE_FILTER * -1;
	}
	
	public void moveBall( float currX, float currY )
	{
		
		// If above noise levels, change position. Else do nothing. 
		
		// NOTE! in xPosition and yPosition, the opposite .get() is called
		// This is due to the screen being landscape oriented - So in a way X = Y, and Y = X. 
	
		if( currX > POS_NOISE_FILTER || currX < NEG_NOISE_FILTER )
		{
			float xDirection = -1;
			
			if ( currX > 0 )
			{
				xDirection = 1;
			}
			
			float xPosition = mBallModel.getY() + (mBallModel.getSpeed() * xDirection );
		
			// Checks to see if the ball goes out of bounds. 
			if ( xPosition > ( mViewHeight - mBallModel.getRadius() ))
			{
				xPosition = mViewHeight - mBallModel.getRadius();
			}
			else if( xPosition < ( 0 + mBallModel.getRadius() ))
			{
				xPosition = 0 + mBallModel.getRadius();
			}
			
			
			mBallModel.setY( xPosition );
		}
		
		if ( currY > POS_NOISE_FILTER || currY < NEG_NOISE_FILTER )
		{
			
			float yDirection = -1;
			
			if ( currY > 0 )
			{
				yDirection = 1;
			}
		
			float yPosition = mBallModel.getX() + (mBallModel.getSpeed() * yDirection );
			
			// Checks to see if the ball goes out of bounds. 
			if ( yPosition > ( mViewWidth - mBallModel.getRadius() ))
			{
				yPosition = mViewWidth - mBallModel.getRadius();
			}
			else if( yPosition < ( 0 + mBallModel.getRadius() ))
			{
				yPosition = 0 + mBallModel.getRadius();
			}
			
			mBallModel.setX( yPosition );
		}
		
		scoreTimer();
	}

	// Creates an anonymous timer class
	// to implement a score deduction timer
	// that deducts .getScoreDeductionRate() points each second. 
	public void scoreTimer()
	{
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run()
			{
				mBallModel.setScore(mBallModel.getScore() - mBallModel.getScoreDeductionRate());
			}
		};
		
		timer.schedule(task,  1000, 1000); // (Task, when to start, when to repeat)
	}
	
	public void updateViewSize(int viewWidth, int viewHeight) {
		mViewWidth = viewWidth;
		mViewHeight = viewHeight;
	}
}
