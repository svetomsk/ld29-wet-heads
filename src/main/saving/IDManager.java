package main.saving;

import items.seeds.DamageMignonSeed;
import items.seeds.DarkMignonSeed;
import items.seeds.JumpMignonSeed;
import items.seeds.LightMignonSeed;

import java.util.HashMap;

import block.Dirt;
import block.Green;
import block.Lava;
import block.Purple;
import block.Rock;
import block.decor.Background;
import block.decor.Ghost_Rock;
import block.decor.Wood;
import entity.Chest;
import entity.End;
import entity.mob.Angel;
import entity.mob.ArchAngel;
import entity.mob.Butterfly;
import entity.mob.Character;
import entity.mob.Creator;
import entity.mob.GreenPlayer;
import entity.mob.PurplePlayer;
import entity.mob.mignons.DamageMignon;
import entity.mob.mignons.DarkMignon;
import entity.mob.mignons.JumpMignon;
import entity.mob.mignons.LightMignon;

public class IDManager
{
	private static HashMap<Integer, Class> entities = new HashMap<Integer, Class>()
	{
		{
			put(1, Character.class);
			put(2, Angel.class);
			put(3, ArchAngel.class);
			put(4, Butterfly.class);
			put(5, Creator.class);
			put(6, GreenPlayer.class);
			put(7, PurplePlayer.class);
			
			put(65, Chest.class);
			put(66, End.class);

			put(129, DamageMignon.class);
			put(130, DarkMignon.class);
			put(131, JumpMignon.class);
			put(132, LightMignon.class);
			
			put(1025, DamageMignonSeed.class);
			put(1026, DarkMignonSeed.class);
			put(1027, JumpMignonSeed.class);
			put(1028, LightMignonSeed.class);
		}
	};
	
	private static HashMap<Class, Integer> entitiesIDs = new HashMap<Class, Integer>()
	{
		{
			put(Character.class, 1);
			put(Angel.class, 2);
			put(ArchAngel.class, 3);
			put(Butterfly.class, 4);
			put(Creator.class, 5);
			put(GreenPlayer.class, 6);
			put(PurplePlayer.class, 7);
			
			put(Chest.class, 65);
			put(End.class, 66);

			put(DamageMignon.class, 129);
			put(DarkMignon.class, 130);
			put(JumpMignon.class, 131);
			put(LightMignon.class, 132);
			
			put(DamageMignonSeed.class, 1025);
			put(DarkMignonSeed.class, 1026);
			put(JumpMignonSeed.class, 1027);
			put(LightMignonSeed.class, 1028);
		}
	};
	
	public static Class[] getEntitiesClasses()
	{
		Object[] preres = entities.values().toArray(); 
		Class[] res = new Class[preres.length];
		for(int q=0;q<res.length;q++)
		{
			res[q] = (Class) preres[q];
		}
		return res;
	}
	
	public static int getID(Class cl)
	{
		return entitiesIDs.get(cl);
	}
	public static Class getClass(int id)
	{
		return entities.get(id);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static HashMap<Byte, Class> blocks = new HashMap<Byte, Class>()
	{
		{
			put((byte) 1, Dirt.class);
			put((byte) 2, Rock.class);
			put((byte) 3, Lava.class);
			put((byte) 4, Purple.class);
			put((byte) 5, Ghost_Rock.class);
			put((byte) 6, Green.class);
			put((byte) 7, Wood.class);
			put((byte) 8, Background.class);
		}
	};
	
	private static HashMap<Class, Byte> blockIDs = new HashMap<Class, Byte>()
	{
		{
			put(Dirt.class, (byte) 1);
			put(Rock.class, (byte) 2);
			put(Lava.class, (byte) 3);
			put(Purple.class, (byte) 4);
			put(Ghost_Rock.class, (byte) 5);
			put(Green.class, (byte) 6);
			put(Wood.class, (byte) 7);
			put(Background.class, (byte) 8);
		}
	};
	
	public static Class[] getBlockClasses()
	{
		Object[] preres = blocks.values().toArray(); 
		Class[] res = new Class[preres.length];
		for(int q=0;q<res.length;q++)
		{
			res[q] = (Class) preres[q];
		}
		return res;
	}
	
	public static byte getBlockID(Class cl)
	{
		return blockIDs.get(cl);
	}
	public static Class getBlockClass(byte id)
	{
		return blocks.get(id);
	}
}
