package entity.mob.controllers;

import items.Item;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import main.World;
import entity.mob.Mob;

public class Controller
{
	protected Mob mob;

	public Controller(Mob mob)
	{
		this.mob = mob;
	}
	public void tick()
	{

	}
	public void save(DataOutputStream os) throws IOException
	{
		
	}
	public void load(DataInputStream is, World world) throws IOException
	{
		
	}

	public boolean tryGet(Item item)
	{
		return false;
	}
}
