package entity.mob.snake.weapon;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import main.World;
import entity.Entity;
import entity.mob.Mob;

public class Weapon extends Entity
{
	protected Mob owner;
	protected Image img;
	
	public Weapon init(long x, long y, double lvx, double lvy, double gvx, double gvy, World world, Mob owner)
	{
		this.owner = owner;
		return (Weapon) super.init(x, y, lvx, lvy, gvx, gvy, world);
	}
	
	
	public void use(double angle)
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
    	int drawx = (int) (owner.getCX()-Game.x);
    	int drawy = (int) (owner.getCY()-Game.y);

		double angle = owner.getAngle();
		double k = 0.5;  
		
		g.rotate(angle+Math.PI/2, drawx, drawy);
		g.scale(k, k);
		g.drawImage(img, (int)( ( drawx-img.getWidth(null)/4 )/k ), (int) ( ( drawy-img.getHeight(null)/2 )/k ), null);
		g.scale(1/k, 1/k);
		g.rotate(-angle-Math.PI/2, drawx, drawy);
        
//		drawBounds(g);
    }
}
