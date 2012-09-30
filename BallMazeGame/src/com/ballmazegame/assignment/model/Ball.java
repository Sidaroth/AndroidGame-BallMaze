package com.ballmazegame.assignment.model;

import java.util.Observable;

// This is the model for the ball. It holds the variables
// for (x, y) position and the speed of the ball. 
// It is ignorant of it's "surroundings" following the MVC pattern.
// Extends Observable - setChanged & notifyObservers to raise a flag that some change
// has been made to the ball. 

public class Ball extends Observable
{
	private float x;
	private float y;
	
	private float speed;
	
	// We need some kind of collision circle?
	
//	public Ball()
//	{
//		x = 0;
//		y = 0;
//		speed = 1.0f;
//	}
//	
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
}
