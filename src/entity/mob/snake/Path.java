package entity.mob.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.Game;

import entity.Entity;

public class Path 
{
	private ArrayList<Integer> path = new ArrayList<Integer>();
//	private ArrayList<Double> length = new ArrayList<Double>();
	
	private long x0;
	private long y0;
//	private long x1;
//	private long y1;
	
	public Path(long x0, long y0) 
	{
		this.x0 = x0;
		this.y0 = y0;
	}
	
	public void add(long x, long y)
	{
		int dx = (int) (x-x0);
		int dy = (int) (y-y0);
		x0 = x;
		y0 = y;
		
		if(dx == 0 && dy == 0) return;
		
//		length.add(Math.sqrt( dx*dx + dy*dy ));
		
		path.add(dx);
		path.add(dy);
	}
	
	public void use(ArrayList<SnakePart> body)
	{
		int pathPoint = 1;
		
		long curx = x0;
		long cury = y0;
				
		long dx = 0;
		long dy = 0;
		
		double dist = 0;
		
		for(int q=0;q<body.size();q++)
		{
			for(;;pathPoint++)
			{
				if(2*pathPoint > path.size())
				{
					return;
				}
				dx += path.get(path.size()-pathPoint*2);
				dy += path.get(path.size()-pathPoint*2+1);
				dist = Math.sqrt( dx*dx + dy*dy );
				if(dist >= SnakePart.segment_gap)
				{
					pathPoint++;
					break;
				}
			}
//			double angle = Entity.getAngle(dx, dy);
//			
//			dx = (long) (Math.cos(angle)*SnakePart.segment_gap);
//			dy = (long) (Math.cos(angle)*SnakePart.segment_gap);			
			
			body.get(q).setCoords(curx-=dx, cury-=dy);
			dx = 0;
			dy = 0;
		}
	}
	
	public void draw(Graphics2D g) 
	{
		g.setColor(Color.RED);
		
		int cx = (int) (x0-Game.x);
		int cy = (int) (y0-Game.y);
		
		for(int q=1;2*q<path.size();q++)
		{
			g.drawLine(cx, cy, cx-=path.get(path.size()-q*2), cy-=path.get(path.size()-q*2+1));
		}
	}
	
}
