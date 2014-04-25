package entity.mob;

import main.World;
import entity.mob.controllers.AngelController;

public class Angel extends Mob{
	
	@Override
	protected void finalInit(World world)
	{
		super.finalInit(world);
		control = new AngelController(this);
	}
	@Override
	protected boolean interactOnCharacter(Character character) 
	{
		double dir = character.getCX()-x >= 0 ? 1 : -1; 
		character.damage(getDamage(), getKnokback(), dir);
		return true;
	}
    @Override
	public double getSpeed()
	{
		return 9;
	}
    @Override
	public double getJumpPower()
	{
		return 13;
	}
    @Override
	public int getMaxHP()
	{
		return 50;
	}
    @Override
	public int getDamage()
	{
		return 5;
	}
    @Override
	public int getKnokback()
	{
		return 7;
	}
    @Override
	public double getStrength()
	{
		return 0;
	}
	@Override
	public int getWidth()
	{
		return 42;
	}
	@Override
	public int getHeight()
	{
		return 64;
	}
}
