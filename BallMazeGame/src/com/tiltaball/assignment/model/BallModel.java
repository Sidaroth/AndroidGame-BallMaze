package com.tiltaball.assignment.model;

import java.util.Observable;


// This is the model for the ball. It holds the variables
// for (x, y) position, the radius and the speed of the ball. 
// It is ignorant of it's "surroundings" following the MVC pattern.


public class BallModel extends Observable
{
	private float x;
	private float y;
	
	private int speed;
	private float ballRadius;
	
	private float score;
	private final float SCORE_ADDITION_RATE;
	
	// We need some kind of collision circle?
	
	public BallModel()
	{
		x = 700;
		y = 350;
		speed = 3;
		ballRadius = 15.0f;
		
		score = 0;
		SCORE_ADDITION_RATE = 2.5f;
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
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int speed)
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
	
	public float getScore()
	{
		return score;
	}
	
	public void setScore(float updatedScore)
	{
		score = updatedScore;
	}
	
	public float getScoreAdditionRate()
	{
		return SCORE_ADDITION_RATE;
	}
}
