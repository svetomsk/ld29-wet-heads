package entity.mob.snake.weapon;

import java.awt.Image;

import main.Pictures;
import entity.mob.snake.weapon.shell.Flame;

public class Flamer extends Weapon
{
	private static double flameSpeed = 16;
	
	@Override
	public void use(double angle)
	{
		double angle1;
		for(int q=0;q<3;q++)
		{
			angle1 = angle+Math.PI*(Math.random()-0.5)/6;
			new Flame().init(owner.getCX(), owner.getCY(),
				Math.cos(angle1)*flameSpeed, Math.sin(angle1)*flameSpeed,
				0, 0, owner.getWorld(), owner);
		}
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.flame_item;
    }
}
