package model;

import java.awt.Point;
import java.util.LinkedList;

public class Snake
{
	private LinkedList<Point> snekPoints;

	public Snake()
	{
		snekPoints = new LinkedList<Point>();
	}

	public void resetSnake(Point head)
	{
		snekPoints.clear();
		snekPoints.add(head);
	}

	public Point getHead()
	{
		return snekPoints.peekFirst();
	}

	public Point removeLast()
	{
		return snekPoints.removeLast();
	}

	public Point getFirst()
	{
		return snekPoints.peekFirst();
	}

	public void pushP(Point P)
	{
		snekPoints.push(P);
	}

	public int getSize()
	{
		return snekPoints.size();
	}
}
