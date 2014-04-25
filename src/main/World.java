package main;

import items.Apple;
import items.Flame_item;
import items.Poison_item;
import items.Rocket_Item;
import items.Snow_item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.saving.IDManager;
import particle.Particle;
import block.Block;
import entity.Entity;
import entity.mob.Character;
import entity.mob.Enemy;
import entity.mob.snake.weapon.shell.Boom;

public class World
{
	public final int BLOCK_SIZE = 10;
	public final int GRAVITY = 1;
	public static final double VX_SLOWLY = 0.9;

	public boolean stepState = true;

	public static Input input;

	public static int k = 0;

	public ArrayList<Island> islands;
	public ArrayList<Entity> entities;
	public ArrayList<Particle> particles;

	private Character character = null;

	public World()
	{
		clear();
		Game.pic = new Pictures();
	}

	public void clear()
	{
		islands = new ArrayList<Island>();
		entities = new ArrayList<Entity>();
		particles = new ArrayList<Particle>();
		apple_quantity = 0;
		Enemy.quantity = 0;
	}

	public void createLevel(int n)
	{
		clear();
		parseInput(n);
	}

	private void parseInput(int n)
	{
		byte[][] arr = ImageParser.parseBlocks("resources/level"+n+"_phys.png");
		new Island(0, 0, 0, 0, this, arr, Pictures.level[n-1]);
		parseInputForEntities();
	}

	public void parseInputForEntities()
	{
		byte[][] arr = ImageParser.getArr();//parseBlocks("resources/firstIsland.png");
		Entity.parse(arr, this);
//		findCharacter();
	}

//	public void findCharacter()
//	{
//		for (Entity e : entities)
//		{
//			if (e instanceof Character) character = (Character) e;
//		}
//	}
	public Character getCharacter()
	{
		return character;
	}

	public int apple_quantity = 0;
	
	private void spawn(Entity entity)
	{
//		while(true)
//		{
		if(islands.size() == 0) return;
		if(islands.get(0).blocks == null) return;
		
		int x = (int) (islands.get(0).blocks.length*BLOCK_SIZE* (1.0/32+30*Math.random()/32) );
		int y = (int) (islands.get(0).blocks[0].length*BLOCK_SIZE* (1.0/32+30*Math.random()/32) );
		
		entity.init(x, y, this);
//		}
	}
	
	public void spawnRandomBonus()
	{
		double r = Math.random();
		if(r>0.75)
		{
			spawn(new Rocket_Item());
		} 
		else if(r>0.50)
		{
			spawn(new Flame_item());
		}
		else if(r>0.25)
		{
			spawn(new Poison_item());
		}
		else
		{
			spawn(new Snow_item());
		}
	}
	
	public void step()
	{
		
		for (int q = 0; q < entities.size(); q++)
		{
			Entity e = entities.get(q);
			if (e.isDeleted())
			{
				e.onDeath();
				entities.remove(q);
				q--;
				continue;
			}
			e.tick();
		}
		for (int q = 0; q < entities.size(); q++)
		{
			for (int w = q + 1; w < entities.size(); w++)
			{
				if (!entities.get(q).isCollide(entities.get(w)))
					continue;
				entities.get(q).interactOn(entities.get(w));
				entities.get(w).interactOn(entities.get(q));
			}
		}

//		---------------------------------- Particles ------------------------------

		for (int q = 0; q < particles.size(); q++)
		{
			Particle p = particles.get(q);
			if (p.isDeleted())
			{
				p.onDeath();
				particles.remove(q);
				q--;
				continue;
			}
			p.tick();
		}
		for (int q = 0; q < particles.size(); q++)
		{
			for (int w = q + 1; w < particles.size(); w++)
			{
				particles.get(q).interactOn(particles.get(w));
				particles.get(w).interactOn(particles.get(q));
			}
		}
		if(apple_quantity <= 2)
		{
			spawn(new Apple());
			apple_quantity++;
		}
		if(Math.random() > 0.997)
		{
			spawnRandomBonus();
		}
		if(Math.random() > 0.999)
		{
			spawn(new Enemy());
		}

//        for(int q=0;q<islands.size();q++)
//        {
//        	Island island = islands.get(q);
//        	island.tick();
//        }
	}

	public boolean collideIslands(double x, double y)
	{
		boolean c = false;
		for (Island island : islands)
		{
			try
			{
				byte id = island.blocks[(int) ((x - island.getX()) / BLOCK_SIZE)][(int) ((y - island.getY()) / BLOCK_SIZE)];
				Block block = (Block) IDManager.getBlockClass(id).newInstance(); 
				if(block.getCollidable())
					c = true;
			} catch (Exception ex)
			{
			}
		}
		return c;
	}

	public Entity getEntityByID(int id)
	{
		for (Entity e : entities)
		{
			if (e.getId() == id)
			{
				return e;
			}
		}
		return null;
	}

//	public static boolean collidePoint(double x, double y, Mob mob)
//	{
//		double xk = mob.x;
//		double yk = mob.y;
//		double w = mob.width;
//		double h = mob.height;
//		return (x>=xk && x<=xk+w && y>=yk && y<=yk+h);
//	}
	public void draw(Graphics2D g)
	{
//            count++;
//            for(int q=Math.max(0, Game.x/BLOCK_SIZE);q<Math.min((Game.x+Game.WIDTH*Game.SIZE)/BLOCK_SIZE+1, level.length);q++)
//            {
//                    for(int w=Math.max(0, Game.y/BLOCK_SIZE);w<Math.min((Game.y+Game.HEIGHT*Game.SIZE)/BLOCK_SIZE+1, level[0].length);w++)
//                    {
//                        if(level[q][w] == 0) continue;
//                        
//                        if(level[q][w] == 1)g.setColor(Color.BLACK);
//                        g.fillRect(q*BLOCK_SIZE - Game.x, w*BLOCK_SIZE - Game.y, BLOCK_SIZE, BLOCK_SIZE);
//                    }
//            }

		g.setColor(Color.BLACK);
		for (Island i : islands)
		{
			i.draw(g);
		}
		for (Entity e : entities)
		{
			e.draw(g);
		}
		for (Particle p : particles)
		{
			p.draw(g);
		}
//		g.setColor(Color.BLUE);
//		g.fillOval(-10 - Game.x, -10 - Game.y, 20, 20);
	}
}
