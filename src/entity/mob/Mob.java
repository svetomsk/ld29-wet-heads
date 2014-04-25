package entity.mob;

import items.Item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import main.Game;
import main.World;
import main.saving.IDManager;
import particle.Blood;
import block.Block;
import entity.Entity;
import entity.mob.controllers.Controller;
import entity.mob.controllers.Group;

public class Mob extends Entity{
	
	public double choosenDir = 1;
	public boolean onGround;
	public int hp = 100;
	protected Controller control;
	protected Group group;
	
//	public void init(long x, long y, World world, Group g)
//	{
//		super.init(x, y, world);
//		
//		g.addMob(this);
//		this.group = g;
//	}
	@Override
	protected void finalInit(World world)
	{
		super.finalInit(world);
		hp = getMaxHP();
		Group.mobs.addMob(this);
		this.group = Group.mobs;
	}
	@Override
	public void save(DataOutputStream os) throws IOException
	{
		super.save(os);
		os.writeInt(hp);
	}
	@Override
	public void load(DataInputStream is, World world) throws IOException
	{
		super.load(is, world);
		hp = is.readInt();
	}
	
	@Override
	public void onDeath() 
	{
		super.delete();
	}
	
	public void damage(int damage, int knockback, double dir)
	{	
		if(damage == 0) return;
		
		hp -= Math.max(damage - getStrength(), 0);
		
//		if(knockback>0)
//		{
//			this.lvx=dir*knockback;
//			this.lvy=-getJumpPower()*0.5;
//		}
		
		for(int q=0;q<7;q++)
		{
			new Blood(x, y, world);
		}
	}	
	public void tick()
	{
		control.tick();
    	if(hp<0.01)
		{
    		isDeleted = true;
    		return;
		}   
    	super.tick();
	}
	@Override
	protected void slowly()
	{
		lvx *= getSpeed()/(getSpeed()+1);
		lvy *= getSpeed()/(getSpeed()+1);
	}
	@Override
	protected boolean collideIslands(boolean verticalWalls)
	{
		tmp_damage = 0;
		boolean isCollide = super.collideIslands(verticalWalls);
		if(!verticalWalls && isCollide) onGround = true;
		damage(tmp_damage, 10, -1*Math.signum(lvx));
		return isCollide;
	}
	private static int tmp_damage;
	
	@Override
	protected void interactOn(byte id) 
	{
		Block block = null;
		try
		{
			block = (Block) IDManager.getBlockClass(id).newInstance();
		} catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tmp_damage = Math.max(block.getDamage(), tmp_damage);
		super.interactOn(id);
	}
	@Override
	public void draw(Graphics2D g)
	{                  
		super.draw(g);
        drawBounds(g);
        drawHealth(g);
	}
	
	protected void drawHealth(Graphics2D g)
	{
		g.setColor(new Color((float)(1-Math.max(hp/(double)getMaxHP(), 0)), (float)Math.max(hp/(double)getMaxHP(), 0), (float)0.0));
        g.fillRect((int)(x+getWidth()/2-(Math.max(hp/(double)getMaxHP(), 0)*getWidth())) - Game.x, 
        		(int)y-18 - Game.y, (int)(getWidth()*Math.max(hp/(double)getMaxHP(), 0))*2, 6);
	}
	
	public void onRight()
	{
//		lvx++;
	}
	public void onLeft()
	{
//		lvx--;
	}
	public void onUp()
	{
//		lvy--;
	}
	public void onDown()
	{
//		lvy++;
	}
	public boolean tryGet(Item item)
	{
		return control.tryGet(item);
	}
	
	public void addLVX(double value)
	{
		lvx += value;		
	}
	public void addLVY(double value)
	{
		lvy += value;
	}
	
//	public static double speed = 7;
//	public static double JUMP_POWER = 13;
//	public static int max_hp = 100;
//	public static int damage = 10;
//	public static int knockback = 7;	
//	protected static int width = 16;
//	protected static int height = 16;
//	public static double strength = 0;
	
	public double getSpeed()
	{
		return 15;
	}
//	public double getJumpPower()
//	{
//		return 1;
//	}
	public int getMaxHP()
	{
		return 100;
	}
	public int getDamage()
	{
		return 10;
	}
	public int getKnokback()
	{
		return 7;
	}
	public double getStrength()
	{
		return 0;
	}
	@Override
	public int getWidth()
	{
		return width;
	}
	@Override
	public int getHeight()
	{
		return height;
	}
	public Group getGroup()
	{
		return group;
	}
	
	protected double angle;
	public double getAngle() 
	{
		return angle;
	}
	public void feed(){}
}
