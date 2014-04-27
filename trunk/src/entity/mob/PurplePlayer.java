package entity.mob;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;

import main.Game;
import main.Pictures;
import main.World;
import GUI.GUI;
import entity.mob.controllers.Group;

public class PurplePlayer extends Mob
{
//	public int cooldownAfterDamage;
	private GUI control;
	
	@Override
	public void finalInit(World world)
	{
		super.finalInit(world);
		super.control = new GUI(this, Game.getInput());
		control = (GUI) super.control;
		
		Game.setGUI(control);
		
		group.removeMob(this);
		Group.villians.addMob(this);
		group = Group.villians;
	}
	
//	@Override
//	public void damage(int damage, int knockback, double dir)
//	{
//		if(cooldownAfterDamage>0) return;
//		super.damage(damage, knockback, dir);
//		cooldownAfterDamage = 12;
//	}
	
	private int stamina = 0;
	private static int maxStamina = 240;
	
	int time = (int) (1000*Math.random());
	@Override
	public void tick()
	{
		super.tick();
		if(stamina < maxStamina) stamina++;;
		time++;		
	}
    
	private Image img;
    @Override
    protected void initPictures() 
    {    	
    	img = Pictures.purplePlayer;
    	super.initPictures();
    }
    int amplitude = 8;
    @Override
    public void draw(Graphics2D g)
    {
    	int drawx = (int) (x-Game.x+width/2);
    	int drawy = (int) (y-Game.y+height/2);

//		double angle = getAngle(lvx, lvy);
		  
//		g.rotate(angle, drawx, drawy);
		g.drawImage(img, drawx-img.getWidth(null)/2,
				(int) (amplitude*Math.sin(Math.PI*time/20))-4 + drawy - img.getHeight(null)/2,
				null);
//		g.rotate(-angle, drawx, drawy);
//        double angle = getAngle(control.getX()-drawx, control.getY()-drawy)+Math.PI/2;
//        super.draw(g);
		
//		drawBounds(g);
        drawHealth(g);
    }
    
    public GUI getGUI()
    {
    	return control;
    }
    @Override
	public double getSpeed()
	{
		return 15;
	}
    @Override
	public double getJumpPower()
	{
		return 18;
	}
    @Override
	public int getMaxHP()
	{
		return 500;
	}
    @Override
	public int getDamage()
	{
		return 0;
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

}
