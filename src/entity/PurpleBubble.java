package entity;

import main.Pictures;

public class PurpleBubble extends GreenBubble
{
	@Override
	protected void initPictures()
	{
		super.initPictures();
		img = Pictures.purpleBubble;
	}
	@Override
	public void tick()
	{
		super.tick();
		if(getCY() < 0)
		{
			delete();
		}
	}
}
