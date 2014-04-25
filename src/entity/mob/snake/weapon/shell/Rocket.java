package entity.mob.snake.weapon.shell;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.Mob;

public class Rocket extends Shell
{
	private Image img;
	
	@Override
	protected boolean interactOnMob(Mob mob)
	{
		if(mob.getGroup() != owner.getGroup())
		{
			new Boom().init(getCX(), getCY(), 0, 0, 0, 0, getWorld());
			delete();
		}
		return super.interactOnMob(mob);
	}

	@Override
	protected void interactOn(byte id)
	{
		if(!isDeleted)
		{
			new Boom().init(getCX(), getCY(), 0, 0, 0, 0, getWorld());
			delete();
		}
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.rocket;
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
}
