package entity;

import block.Door2;
import entity.mob.Character;
import main.World;

public class Key2 extends Entity
{
        public Key2()
        {
            
        }
	@Override
	protected boolean interactOnCharacter(Character character)
	{
		World.islands.get(0).removeBlock(Door2.class);           
                delete();
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
