package items;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.Mob;

public class Apple extends Item{
	
	private Image img; 
	
	@Override
	public void onDeath()
	{
		world.apple_quantity--;
		super.onDeath();
	}
	
	@Override
	protected void initPictures()
	{
		if( Math.random()>0.5 )
		{
			img = Pictures.apple;
		}
		else
		{
			img = Pictures.pear;
		}
	}
	
	@Override
	protected boolean interactOnMob(Mob mob)
	{
		mob.feed();
		delete();
		return true;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		int drawx = (int) (getCX()-Game.x);
    	int drawy = (int) (getCY()-Game.y);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/2, null);
//		drawBounds(g);
	}
}
