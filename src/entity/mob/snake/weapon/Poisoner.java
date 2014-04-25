package entity.mob.snake.weapon;

import java.awt.Image;

import main.Pictures;
import entity.mob.snake.weapon.shell.Poison;

public class Poisoner extends Weapon
{
	private static int cooldown = 5;
	private int currentCooldown = 5;
	
	private static double rocketSpeed = 32;
	
	@Override
	public void use(double angle)
	{
		if(currentCooldown < 0)
		{
			new Poison().init(owner.getCX(), owner.getCY(),
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
    	img = Pictures.poison_item;
    }
}
