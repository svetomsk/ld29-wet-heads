package entity.mob;

import main.Pictures;

public class GreenPlayer extends Character
{
	@Override
	protected void initPictures()
	{
		super.initPictures();
		img = Pictures.greenPlayer;
	}
}
