package com.ballmazegame.assignment.model;

import java.util.Observable;


// This class might represent the walls in the maze / labyrinth. 

public class ObstacleWall extends Observable 
{
	private float x;
	private float y;
	
	public ObstacleWall(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	// getters
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
