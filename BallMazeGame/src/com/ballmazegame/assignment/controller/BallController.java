package com.ballmazegame.assignment.controller;

import java.util.Observable;

import com.ballmazegame.assignment.model.BallModel;

//Extends Observable - setChanged & notifyObservers to raise a flag that some change
//has been made to the ball. 
public class BallController extends Observable {
	
	public BallModel mBallModel;
	
	public BallController()
	{
		mBallModel = new BallModel();
	}
	
	public void moveBall( float currX, float currY )
	{
		float xDirection = -1;
		float yDirection = 1;
		
		mBallModel.setX( mBallModel.getX() + currX);
		mBallModel.setY( mBallModel.getY() + currY);
		
	}
}
