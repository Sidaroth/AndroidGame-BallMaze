package com.ballmazegame.assignment.controller;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;

import com.ballmazegame.assignment.EndGameActivity;
import com.ballmazegame.assignment.model.BallModel;


public class BallController {
	
	public final static String SCORE = "com.ballmazegame.assignment.SCORE";
	
	public BallModel mBallModel;
	private float POS_NOISE_FILTER; 
	private float NEG_NOISE_FILTER;
	private int mViewWidth, mViewHeight;
	Context context;
	
	private int xDirection, yDirection;
	
	
	public BallController(Context context)
	{
		mBallModel = new BallModel();
		POS_NOISE_FILTER = 0.5f;
		NEG_NOISE_FILTER = POS_NOISE_FILTER * -1;
		xDirection = -1;
		yDirection = -1;
		
		this.context = context;
		scoreTimer();
	}
	
	public void moveBall( float currX, float currY )
	{
		// If above noise levels, change direction. Else keep moving at same speed. 
		
		// NOTE! in xPosition and yPosition, the opposite .get() is called
		// This is due to the screen being landscape oriented - So in a way X = Y, and Y = X. 
		float xPosition = mBallModel.getX();
		float yPosition = mBallModel.getY();
		
		if ((int)mBallModel.getScore() % 250 == 0)
		{
			mBallModel.setSpeed(mBallModel.getSpeed() + 1);
		}
	
		if( currX > POS_NOISE_FILTER || currX < NEG_NOISE_FILTER )
		{
			if ( currX > 0 )
			{
				xDirection = 1;
			}
			else
			{
				xDirection = -1;
			}
			
		}
		
		if ( currY > POS_NOISE_FILTER || currY < NEG_NOISE_FILTER )
		{
						
			if ( currY > 0 )
			{
				yDirection = 1;
			}
			else
			{
				yDirection = -1;
			}
		}
		
		if ( xPosition > ( mViewWidth - mBallModel.getRadius() ))
		{
			endGame();
		}
		else if( xPosition < ( 0 + mBallModel.getRadius() ))
		{
			endGame();	
		}
		
		if ( yPosition > ( mViewHeight - mBallModel.getRadius() ))
		{
			endGame();
		}
		else if( yPosition < ( 0 + mBallModel.getRadius() ))
		{
			endGame();
		}
		
		
		mBallModel.setY( mBallModel.getY() + (mBallModel.getSpeed() * xDirection ));
		mBallModel.setX( mBallModel.getX() + (mBallModel.getSpeed() * yDirection ));
		

	}


	// Opens the EndGameActivity, which sorts out highscores etc.
	
	private void endGame()
	{
		Intent intent = new Intent(context, EndGameActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		String score = Float.toString(mBallModel.getScore());
		intent.putExtra(SCORE, score);
		
		context.startActivity(intent);
	}
	
	// sets the user up for another go.
	private void restart()
	{
		mBallModel.setScore(0);
		mBallModel.setX(mViewWidth / 2);
		mBallModel.setY(mViewHeight / 2);
		mBallModel.setSpeed(3);
	}
	
	// Creates an anonymous timer class
	// to implement a score addition timer
	// that adds .getScoreAdditionRate() points each time it runs.
	// For a visual effect, the points are added every 50ms
	
	// ISSUE: Crashes if the model says that it's data has been changed.
	public void scoreTimer()
	{
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run()
			{ 
				mBallModel.setScore(mBallModel.getScore() + mBallModel.getScoreAdditionRate());	
			}
		};
		
		timer.schedule(task,  50, 50); // (Task, when to start, when to repeat)
	}
	
	public void updateViewSize(int viewWidth, int viewHeight) {
		mViewWidth = viewWidth;
		mViewHeight = viewHeight;
	}
}
