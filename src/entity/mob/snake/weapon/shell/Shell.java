package entity.mob.snake.weapon.shell;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import main.World;
import entity.Entity;
import entity.mob.Mob;

public class Shell extends Entity
{
	private Image img;
	protected Mob owner;

	public Shell init(long x, long y, double lvx, double lvy, double gvx, double gvy, World world, Mob owner)
	{
		this.owner = owner;
		
		return (Shell) super.init(x, y, lvx, lvy, gvx, gvy, world);
	}
	
	@Override
	protected void slowly()
	{
		
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.blood;
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (getCX()-Game.x);
    	int drawy = (int) (getCY()-Game.y);

		double angle = getAngle(getLVX(), getLVY());
		  
		g.rotate(angle-Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/4, null);
		g.rotate(-angle+Math.PI/2, drawx, drawy);
        
//		drawBounds(g);
    }
}
