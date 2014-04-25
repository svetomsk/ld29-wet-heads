package entity.mob.snake;

import main.Pictures;

public class SimpleSnakeHead extends SnakeHead
{
	protected void createBody()
	{
		body.add(new SimpleSnakePart().init(getCX()+getWidth(), getCY(), 0, 0, 0, 0, world, this));
		for(int q=0;q<8;q++)
		{
			SnakePart part = body.get(body.size()-1);
			body.add(new SimpleSnakePart().init(part.getCX()+part.getWidth(), part.getCY()-part.getHeight()/2, 0, 0, 0, 0, world, part));
		}
		SnakePart part = body.get(body.size()-1);
		body.add(new SimpleSnakeTail().init(part.getCX()+part.getWidth(), part.getCY(), 0, 0, 0, 0, world, part));
	}
    @Override
    protected void initPictures() 
    {
    	img = Pictures.simpleSnakeHead;
    }
    @Override
	public double getSpeed()
	{
		return 32;
	}
    
    @Override
    public void onLeft()
    {
    	angle -= Math.PI/30;
    }
    
    @Override
    public void onRight() 
    {
    	angle += Math.PI/30;
    }
    
    @Override
	protected void addBody()
	{
		SimpleSnakePart nbp = new SimpleSnakePart();
		SimpleSnakeTail tail = (SimpleSnakeTail) body.get(body.size()-1);
		SimpleSnakePart bp = (SimpleSnakePart) body.get(body.size()-2); 
		
		body.remove(body.size()-1);
		
		nbp.init(tail.getCX(), tail.getCY(), 0, 0, 0, 0, world, bp);
		nbp.setFrontPart(bp);
		nbp.setBackPart(tail);
		bp.setBackPart(nbp);
		body.add(nbp);
		
		tail.setFrontPart(nbp);
		body.add(tail);
	}
}
