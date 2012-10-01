package com.ballmazegame.assignment.model;

import java.util.Observable;


// This is the model for the ball. It holds the variables
// for (x, y) position, the radius and the speed of the ball. 
// It is ignorant of it's "surroundings" following the MVC pattern.


public class BallModel extends Observable
{
	private float x;
	private float y;
	
	private float speed;
	private float ballRadius;
	
	private int score;
	private final int SCORE_DEDUCTION_RATE;
	
	// We need some kind of collision circle?
	
	public BallModel()
	{
		x = 700;
		y = 350;
		speed = 1.0f;
		ballRadius = 10.0f;
		
		score = 10000;
		SCORE_DEDUCTION_RATE = 100;
	}
	
	public float getX()
	{
		return x;
	}
	
	public void setX(float x)
	{
		this.x = x;
		setChanged();
		notifyObservers();
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setY(float y)
	{
		this.y = y;
		setChanged();
		notifyObservers();
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(float speed)
	{
		this.speed = speed;
		setChanged();
		notifyObservers();
	}
	
	public float getRadius()
	{
		return ballRadius;
	}
	
	public void setRadius(float radius)
	{
		ballRadius = radius;
		setChanged();
		notifyObservers();
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setScore(int updatedScore)
	{
		score = updatedScore;
	}
	
	public int getScoreDeductionRate()
	{
		return SCORE_DEDUCTION_RATE;
	}
}
