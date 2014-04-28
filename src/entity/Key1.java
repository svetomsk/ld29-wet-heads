package entity;

import block.Dirt;
import block.Door1;
import block.Rock;
import entity.mob.Character;
import main.World;

public class Key1 extends Entity
{
        public Key1()
        {
            
        }
	@Override
	protected boolean interactOnCharacter(Character character)
	{
		World.islands.get(0).removeBlock(Door1.class);         
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
