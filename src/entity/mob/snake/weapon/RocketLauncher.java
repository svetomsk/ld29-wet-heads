package entity.mob.snake.weapon;

import main.Pictures;
import entity.mob.snake.weapon.shell.Rocket;

public class RocketLauncher extends Weapon
{
	private static int cooldown = 40;
	private int currentCooldown = 0;
	
	private static double rocketSpeed = 16;
	
	@Override
	public void use(double angle)
	{
		if(currentCooldown < 0)
		{
			new Rocket().init(owner.getCX(), owner.getCY(),
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
    	img = Pictures.rocket_presized;
    }
}
