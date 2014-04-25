package items;

import java.awt.Graphics2D;
import java.awt.Image;

import main.Game;
import main.Pictures;

import entity.mob.snake.SnakeHead;
import entity.mob.snake.weapon.RocketLauncher;

public class Rocket_Item extends Item{
	
	private Image img; 
	
	@Override
	protected void initPictures()
	{
		img = Pictures.rocket_item;
	}
	
	@Override
	protected boolean interactOnSnakeHead(SnakeHead snakeHead)
	{
		snakeHead.setNewWeapon(new RocketLauncher());
		delete();
		return true;
	}
	
	@Override
	public void draw(Graphics2D g)
	{
		int drawx = (int) (getCX()-Game.x);
    	int drawy = (int) (getCY()-Game.y);
		g.drawImage(img, drawx-img.getWidth(null)/2, drawy-img.getHeight(null)/2, null);
	}
}
