package entity.mob.snake.weapon;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import entity.mob.snake.weapon.shell.Snow;

public class Snower extends Weapon
{
	private static int cooldown = 60;
	private int currentCooldown = 60;
	
	private static double rocketSpeed = 24;
	
	@Override
	public void use(double angle)
	{
		if(currentCooldown < 0)
		{
			new Snow().init(owner.getCX(), owner.getCY(),
				Math.cos(angle)*rocketSpeed, Math.sin(angle)*rocketSpeed,
				0, 0, owner.getWorld(), owner);
			currentCooldown = cooldown;
		}
	}
	
	@Override
	public void tick()
	{
		super.tick();
		currentCooldown--;
	}
	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.snow_item;
    }
}