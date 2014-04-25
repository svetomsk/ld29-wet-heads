package entity.mob.snake;

import main.Pictures;
import main.World;
import entity.Entity;

public class SnakeTail extends SnakePart
{
//	public int cooldownAfterDamage;

//	public Entity init(long x, long y, double lvx, double lvy, double gvx, double gvy, World world, SnakePart nextPart)
//	{
//		this.frontPart = nextPart;
//		frontPart.setBackPart(this);
//		head = 
//		return super.init(x, y, lvx, lvy, gvx, gvy, world);
//	}
	@Override
	public void tick()
	{
		angle = getAngle(frontPart.frontPart.getCX() - frontPart.getCX(), frontPart.frontPart.getCY() - frontPart.getCY()) - Math.PI/2; 
				
//    	double dx = frontPart.getX() - getWidth()*Math.cos(angle);    	
//    	double dy = frontPart.getY() - getWidth()*Math.sin(angle);
//    	
//    	angle = getAngle(dx, dy) - Math.PI/2;
    	
    	double r2 = (frontPart.getCX()-getCX())*(frontPart.getCX()-getCX()) + (frontPart.getCY()-getCY())*(frontPart.getCY()-getCY());
    	
    	if(segment_gap*segment_gap < r2)
    	{
    		setX((long) (frontPart.getCX() + segment_gap*Math.cos(angle)));
    		setY((long) (frontPart.getCY() + segment_gap*Math.sin(angle)));
    	}
	}
    @Override
    protected void initPictures() 
    {
    	img = Pictures.tailsnake;
    }
}
