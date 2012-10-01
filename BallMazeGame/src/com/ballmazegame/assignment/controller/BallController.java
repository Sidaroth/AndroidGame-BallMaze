package com.ballmazegame.assignment.controller;

import com.ballmazegame.assignment.model.BallModel;


public class BallController {
	
	public BallModel mBallModel;
	private float POS_NOISE_FILTER; 
	private float NEG_NOISE_FILTER;
	int viewWidth, viewHeight;
	
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
			if ( xPosition > ( viewHeight - mBallModel.getRadius() ))
			{
				xPosition = viewHeight - mBallModel.getRadius();
			}
			else if( xPosition < ( 0 + mBallModel.getRadius() ))
			{
				xPosition = 0 + mBallModel.getRadius();
			}
			
			
			mBallModel.setY( xPosition );
		}
		
		if ( currY > POS_NOISE_FILTER || currY < NEG_NOISE_FILTER )
		{
			System.out.println( currY );
			
			float yDirection = -1;
			
			if ( currY > 0 )
			{
				yDirection = 1;
			}
		
			float yPosition = mBallModel.getX() + (mBallModel.getSpeed() * yDirection );
			
			// Checks to see if the ball goes out of bounds. 
			if ( yPosition > ( viewWidth - mBallModel.getRadius() ))
			{
				yPosition = viewWidth - mBallModel.getRadius();
			}
			else if( yPosition < ( 0 + mBallModel.getRadius() ))
			{
				yPosition = 0 + mBallModel.getRadius();
			}
			
			mBallModel.setX( yPosition );
		}
	}

	public void updateViewSize(int viewWidth, int viewHeight) {
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
	}
}
