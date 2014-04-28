package GUI;

import items.Item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Input;
import main.Pictures;
import main.World;
import weapon.BubbleGun;
import weapon.Jumper;
import weapon.Weapon;
import entity.GreenBubble;
import entity.GreenDestroyer;
import entity.PurpleBubble;
import entity.PurpleDestroyer;
import entity.mob.GreenPlayer;
import entity.mob.Mob;
import entity.mob.controllers.Controller;
import entity.mob.mignons.DarkMignon;
import entity.mob.mignons.Mignon;
import weapon.Destroyer;

public class GUI extends Controller
{
	protected Input input;
	public boolean stepState = true;
	private Weapon weapon;
	
	public GUI(Mob mob, Input input) 
	{
		super(mob);
		this.input = input;
//		leftHand = new SwordItem(mob);
	}
	
	@Override
	public void tick()
	{
		long x = getWorldX();
		long y = getWorldY();
		double angle = getAngle();
		
        //walk
        if(input.right.down) mob.onRight();
        else if(input.left.down) mob.onLeft();
        //jump
        if(input.up.down) mob.onUp();
        if(input.shift.typed) mob.shift();
        
    	//----------------------------------------------------------
        if(input.c1.typed)
        {
            
                if(this.mob instanceof GreenPlayer)
                {
                    weapon = new BubbleGun(GreenBubble.class); 
                }
                else 
                {
                    weapon = new BubbleGun(PurpleBubble.class);
                }
        }
        if(input.c2.typed)
        {
        	weapon = new Jumper(); 
        }
        
        if(input.c3.typed)
        {
            if(this.mob instanceof GreenPlayer)
                weapon = new Destroyer(GreenDestroyer.class);
            else
                weapon = new Destroyer(PurpleDestroyer.class);
        }
        
        if(weapon != null)
        {
        	if(input.wheelClicked)
        	{
        		
        	}
        	if(input.lmb)
        	{
        		weapon.use(getWorld(), getMobCX(), getMobCY(), angle);
        	}
        }
		if(input.space.typed)
		{
			floakReturn();
		}
	}
	
	public void tickGlobal()
	{	
        if(input.escape.typed)
        {
            Game.gameMenu();
        }
        if(input.quicksave.typed)
        {
        	Game.quickSave();
        }
        if(input.quickload.typed)
        {
        	Game.quickLoad();
        }
        if(input.save.typed)
        {
        	Game.saveMenu();
        }
        if(input.load.typed)
        {
        	Game.loadMenu();
        }
        if(input.test.typed)
        {
        	if(Game.getCreator() != null)
        	{
        		Game.loadc("buffer.dat");
        	}
        }
//        if(mob.isDeleted())
//        {
//        	Game.showDeath();
//        }
        if(input.pause.typed)
        {
           stepState = !stepState;
        }
	}
	
	private void heal()
	{
		if(mob.hp >= mob.getMaxHP()) return;
		
		DarkMignon target = null;
		int q = 0;
		
		for(;q<floak.size();q++)
		{
			Mignon m = floak.get(q);
			if(m instanceof DarkMignon)
			{
				target = (DarkMignon) m;
				break;
			}
		}
		if(target == null) return;
		
		mob.hp += mob.getMaxHP()/50;
		mob.hp = Math.min(mob.hp, mob.getMaxHP());
		
		target.delete();
		floak.remove(q);
	}
	
	@Override
	public void onDeath()
	{
		super.onDeath();
		if(Game.getCreator() != null)
		{
			Game.loadc("buffer.dat");
		}
		else
		{
			Game.showDeath();
		}
	}
	public void updateInput(Input input)
	{
		this.input = input;
	}
	
	@Override
	public boolean tryGet(Item item) {
//		if(leftHand==null)
//		{
//			leftHand = item;
//			return true;
//		}
		return false;
	}
	public void draw(Graphics2D g)
	{
		if(!stepState)
		{
			Image value = Pictures.pause;
			g.drawImage(value, (int)(Game.basicWIDTH/2-value.getWidth(null)/2), 128, null);
		}
		if(Game.getCreator() != null)
		{
			g.setColor(Color.BLACK);
			g.drawString("Map testing", Game.basicWIDTH/2, 10);
		}
	}

	public Input getInput()
	{
		return input;
	}
	public long getWorldX()
	{
		double x = (getMobCY()>0 ? Game.WIDTH-Game.scale*input.x : Game.scale*input.x);
		return (long) (Game.x+x);
	}
	public long getWorldY()
	{
		double y = (getMobCY()>0 ? Game.HEIGHT-Game.scale*input.y : Game.scale*input.y);
		return (long) (Game.y+y);
	}
	public double getAngle()
	{
		double angle = Mob.getAngle(getWorldX()-getMobCX(), getWorldY()-getMobCY())+Math.PI/2;
		return angle;
	}
	public Weapon getWeapon()
	{
		return weapon;
	}
	public World getWorld()
	{
		return mob.getWorld();
	}
	public long getMobCX()
	{
		return mob.getCX();
	}
	public long getMobCY()
	{
		return mob.getCY();
	}
	public int getX()
	{
		return input.x;
	}
	public int getY()
	{
		return input.y;
	}
}
