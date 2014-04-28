package entity;

import java.awt.Graphics2D;

import main.Pictures;

public class PurpleBubble extends Entity
{
	boolean fixed = false;
	double elasticity = 0;
	
	@Override
	protected void initPictures()
	{
		super.initPictures();
		img = Pictures.purpleBubble;
	}
	@Override
	public void tick()
	{
		super.tick();
		if(getCY() < 0)
		{
			delete();
		}
	}
	@Override
	protected void updateVelocity()
	{
		slowly();
		if(Math.abs(lvx) < 1) lvx = 0;
		if(Math.abs(lvy) < 1) lvy = 0;
		if(lvy == 0 && lvx == 0) fixed = true;
	}
	@Override
	protected void slowly()
	{
		super.slowly();
		lvx *= 0.9;
		lvy *= 0.9;
	}
	@Override
	protected void updateCoord()
	{
		if(!fixed) super.updateCoord();
	}
	@Override
	protected void interactOn(byte id)
	{
		// TODO Auto-generated method stub
		super.interactOn(id);
		fixed = true;
	}
	@Override
	public void interactOn(Entity e)
	{
		super.interactOn(e);

		if(e instanceof PurpleBubble) return;
		
		e.x -= e.getLVX();
		e.y -= e.getLVY();
		
		int steps=1;
		steps = (int) Math.ceil(Math.max(steps, Math.abs(e.getLVX()/(2*getWidth()))));
		steps = (int) Math.ceil(Math.max(steps, Math.abs(e.getLVY()/(2*getHeight()))));
		for(int w=0;w<steps;w++)
		{
			e.x += e.getLVX()/steps;
			if(isCollide(e))
			{
				if(e.getLVX()-getLVX() < 0)
				{
					e.x = getX()+getWidth()+1;
				}
				else
				{
					e.x = getX()-e.getWidth()-1; 
				}
				e.lvx *= elasticity;
			}
			/////////////////////////////
	        e.y += e.getLVY()/steps;	
	        if(isCollide(e))
			{
				if(e.getLVY()-getLVY() >= 0)
				{
					e.y = getY()-e.getHeight()-1; 
				}
				else
				{
					e.y = getY()+getHeight()+1;
				}
				
				if(e.getLVY()*e.getCY() < 0 ) e.onGround = true;
				
				e.lvy *= elasticity;
			}
//			if(isCollide) break;
		}
	}
	@Override
	public void drawBounds(Graphics2D g){}
	@Override
	public int getHeight()
	{
		return 16;
	}
	@Override
	public int getWidth()
	{
		return 16;
	}
}
