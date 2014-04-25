package entity.mob.snake.weapon.shell;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.Mob;

public class Snow extends Shell
{
	private Image img;
	
	private int charge = 150;
	
	@Override
	protected boolean interactOnMob(Mob mob)
	{
		if(mob.getGroup() != owner.getGroup())
		{
			charge--;
			
			if(charge < 0)
			{
				delete();
				return true;
			}
			mob.damage(100, 0, 0);
		}
		return super.interactOnMob(mob);
	}

	@Override
	protected void interactOn(byte id)
	{
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.snow;
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
