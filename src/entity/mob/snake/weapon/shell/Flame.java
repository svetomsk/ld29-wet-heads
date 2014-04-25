package entity.mob.snake.weapon.shell;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.Mob;

public class Flame extends Shell
{
	private Image img;
	
	@Override
	protected boolean interactOnMob(Mob mob)
	{
		if(mob.getGroup() != owner.getGroup())
		{
			mob.damage(40, 0, 0);
			delete();
		}
		return super.interactOnMob(mob);
	}

	@Override
	protected void interactOn(byte id)
	{
		delete();
	}
    @Override
    protected void initPictures() 
    {
    	double r = Math.random();
    	if(r>0.66)
    	{
    		img = Pictures.spark1;
    	}
    	else if(r>0.33)
    	{
    		img = Pictures.spark2;
    	}
    	else
    	{
    		img = Pictures.spark3;
    	}
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (getCX()-Game.x);
    	int drawy = (int) (getCY()-Game.y);

		double angle = getAngle(getLVX(), getLVY()) - Math.PI/2;
		  
		g.rotate(angle-Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
		g.rotate(-angle+Math.PI/2, drawx, drawy);
    }
    
    @Override
    public int getWidth() 
    {
    	return 16;
    }
    @Override
    public int getHeight() 
    {
    	return 16;
    }
}
