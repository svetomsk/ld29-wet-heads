package entity.mob.controllers;

import items.Apple;
import items.Item;
import entity.Entity;
import entity.mob.Enemy;

public class EnemyController extends Controller
{
	protected Enemy mob;
	public EnemyController(Enemy mob) 
	{
		super(mob);
		this.mob = mob;
		super.mob = mob;
	}
	private Entity target;

	public void tick()
	{
		if(target == null)
		{
			findTarget();
		}
		if(target.isDeleted())
		{
			findTarget();
		}
		mob.setAngle(Entity.getAngle(target.getCX()-mob.getCX(), target.getCY()-mob.getCY())+Math.PI/2);
	}
	private void findTarget() 
	{
		for(Entity e:mob.getWorld().entities)
		{
			if(e instanceof Apple)
			{
				if(Math.random()>1.0/3)
				{
					target = e;
					return;
				}
			}
		}
		findTarget();
	}
	public boolean tryGet(Item item)
	{
		return false;
	}
}
