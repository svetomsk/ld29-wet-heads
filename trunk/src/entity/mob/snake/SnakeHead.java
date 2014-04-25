package entity.mob.snake;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.Game;
import main.Pictures;
import main.World;
import particle.Blood;
import GUI.GUI;
import entity.mob.controllers.Group;
import entity.mob.snake.weapon.RocketLauncher;
import entity.mob.snake.weapon.Weapon;

public class SnakeHead extends SnakePart
{
	private Weapon wep;
	
	public Weapon getWep()
	{
		return wep;
	}
	
	private GUI control;
	private double v = 0;
//	private double angle = Math.PI;
	
	public ArrayList<SnakePart> body = new ArrayList<SnakePart>();
	private Path bodyPath;
	
	@Override
	public void finalInit(World world)
	{
		super.finalInit(world);
		super.control = new GUI(this, Game.getInput());
		control = (GUI) super.control;
		Game.setGUI((GUI)control);
		
		group.removeMob(this);
		Group.character.addMob(this);
		group = Group.character;
		
		head = this;
		createBody();
		
		bodyPath = new Path(getCX(), getCY());
		angle = Math.PI;
	}
	
	protected void createBody()
	{
		body.add(new SnakePart().init(getCX()+getWidth(), getCY(), 0, 0, 0, 0, world, this));
		for(int q=0;q<8;q++)
		{
			SnakePart part = body.get(body.size()-1);
			body.add((SnakePart) new SnakePart().init(part.getCX()+part.getWidth(), part.getCY()-part.getHeight()/2, 0, 0, 0, 0, world, part));
		}
		SnakePart part = body.get(body.size()-1);
		body.add(new SnakeTail().init(part.getCX()+part.getWidth(), part.getCY(), 0, 0, 0, 0, world, part));
	}
	
	@Override
	public void tick()
	{
		v++;
		slowly();
		
//		speedUp = false;
		super.tick();
		
		bodyPath.add(getCX(), getCY());
		bodyPath.use(body);
		
		lvx = v*Math.cos(angle);
		lvy = v*Math.sin(angle);
	}
	@Override
	protected void slowly()
	{
		v *= getSpeed()/(getSpeed()+1);
	}
//	@Override
//	public void onUp() 
//	{
//		super.onUp();
//		v++;
//		speedUp = true;
//	}
	
	@Override
	public void onLeft()
	{
		angle -= Math.PI/40;
	}
	@Override
	public void onRight()
	{
		angle += Math.PI/40;
	}

    @Override
    public void damage(int damage, int knockback, double dir)
	{	
//		if(damage == 0) return;
//		hp -= Math.max(damage - getStrength(), 0);
//		for(int q=0;q<1;q++)
//		{
//			new Blood(getCX(), getCY(), world);
//		}
	}	
    @Override
    protected void initPictures() 
    {
    	img = Pictures.headsnake;
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
//		drawHealth(g);
    }
    @Override
	public double getSpeed()
	{
		return 16;
	}
    @Override
	public int getMaxHP()
	{
		return 8000;
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
    public double getAngle()
	{
		return angle;
	}

    @Override
	public void feed()
	{
		hp += 1000;
		if(hp > getMaxHP())
		{
			hp = getMaxHP();
		}
		addBody();
		addBody();
		addBody();
		control.feeded();
	}
	
	protected void addBody()
	{
		SnakePart nbp = new SnakePart();
		SnakeTail tail = (SnakeTail) body.get(body.size()-1);
		SnakePart bp = body.get(body.size()-2); 
		
		body.remove(body.size()-1);
		
		nbp.init(tail.getCX(), tail.getCY(), 0, 0, 0, 0, world, bp);
		nbp.setFrontPart(bp);
		nbp.setBackPart(tail);
		bp.setBackPart(nbp);
		body.add(nbp);
		
		tail.setFrontPart(nbp);
		body.add(tail);
	}

	public Path getBodyPath() 
	{
		return bodyPath;
	}
	
	public void setNewWeapon(Weapon wep)
	{
		if(this.wep != null) this.wep.delete();
		
		this.wep = wep.init(getCX(), getCY(), 0, 0, 0, 0, world, this);
	}
}
