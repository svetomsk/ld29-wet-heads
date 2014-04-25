package entity.mob;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;
import main.World;
import particle.Blood;
import entity.mob.controllers.EnemyController;
import entity.mob.snake.weapon.Weapon;

public class Enemy extends Mob
{
	private Weapon wep;
	public Weapon getWep()
	{
		return wep;
	}
	
	private double v = 0;
	private double angle = Math.PI;
	
	@Override
	public void finalInit(World world)
	{
		super.finalInit(world);
		control = new EnemyController(this);
	}
	
	@Override
	public void tick()
	{
		v++;
		super.tick();
		slowly();
		lvx = v*Math.cos(angle);
		lvy = v*Math.sin(angle);
	}
	
	@Override
	protected boolean collideIslands(boolean verticalWalls) 
	{
		return false;
	}
	
	@Override
	protected void slowly()
	{
		v *= getSpeed()/(getSpeed()+1);
	}
    @Override
    public void damage(int damage, int knockback, double dir)
	{	
		if(damage == 0) return;
		hp -= Math.max(damage - getStrength(), 0);
		for(int q=0;q<2;q++)
		{
			new Blood(getCX(), getCY(), world);
		}
	}	
    @Override
    public void onDeath() 
    {
    	super.onDeath();
    	quantity--;
    }
    public static int quantity = 0;
    @Override
    public void feed() 
    {
    	super.feed();
    	
    	if(quantity<100)
    	{
    		quantity+=5;
    		new Enemy().init(x, y, world);
    		new Enemy().init(x, y, world);
    		new Enemy().init(x, y, world);
    		new Enemy().init(x, y, world);
    		new Enemy().init(x, y, world);
    	}
    }
    private Image img;
    @Override
    protected void initPictures() 
    {
    	img = Pictures.monster;
    }
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (getCX()-Game.x);
    	int drawy = (int) (getCY()-Game.y);
		  
		g.rotate(angle+Math.PI/2, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-3*img.getHeight(null)/4, null);
		g.rotate(-angle-Math.PI/2, drawx, drawy);
//		drawBounds(g);
		drawHealth(g);
    }
    @Override
	public double getSpeed()
	{
		return 16;
	}
    @Override
	public int getMaxHP()
	{
		return 400;
	}
    @Override
	public int getDamage()
	{
		return 10;
	}
    @Override
	public int getKnokback()
	{
		return 0;
	}
    @Override
	public double getStrength()
	{
		return 0;
	}
    public double getAngle()
	{
		return angle;
	}
	public void setNewWeapon(Weapon wep)
	{
		this.wep = wep.init(getCX(), getCY(), 0, 0, 0, 0, world, this);
	}
	public void setAngle(double angle) 
	{
		this.angle = angle;
	}
}
