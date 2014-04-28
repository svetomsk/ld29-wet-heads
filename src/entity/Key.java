package entity;

import block.Dirt;
import block.Door;
import block.Rock;
import entity.mob.Character;
import main.World;

public class Key extends Entity
{

	@Override
	protected boolean interactOnCharacter(Character character)
	{
		World.islands.get(0).removeBlock(Door.class);                
		return true;
	}

	@Override
	public int getHeight()
	{
		return 64;
	}
	@Override
	public int getWidth()
	{
		return 64;
	}

}
