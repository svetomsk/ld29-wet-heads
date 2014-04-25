package main.saving;

import java.util.HashMap;

import block.Rock;
import entity.Chest;
import entity.mob.Angel;
import entity.mob.Character;

public class IDManager
{
	private static HashMap<Integer, Class> classes = new HashMap()
	{
		{
			put(1, Character.class);
			put(2, Angel.class);
			
			put(65, Chest.class);
		}
	};
	
	private static HashMap<Class, Integer> ids = new HashMap()
	{
		{
			put(Character.class, 1);
			put(Angel.class, 2);
			
			put(Chest.class, 65);
		}
	};
	
	public static int getID(Class cl)
	{
		return ids.get(cl);
	}
	public static Class getClass(int id)
	{
		return classes.get(id);
	}
	
	private static HashMap<Byte, Class> blocks = new HashMap()
	{
		{
			put((byte) 2, Rock.class);
		}
	};
	
	private static HashMap<Class, Byte> blockIDs = new HashMap()
	{
		{
			put(Rock.class, (byte) 2);
		}
	};
	public static byte getBlockID(Class cl)
	{
		return blockIDs.get(cl);
	}
	public static Class getBlockClass(byte id)
	{
		return blocks.get(id);
	}
}
