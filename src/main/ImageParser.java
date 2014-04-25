package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.saving.IDManager;
import block.Rock;

public class ImageParser
{
	private static BufferedImage image;

	private static final int COLOR_BLACK = -16777216;
	private static final int COLOR_YELLOW = -3584;
	private static final int COLOR_ORANGE = -32985;
	private static final int COLOR_PINK = -20791;
	private static final int COLOR_LIGHT_YELLOW = -1055568;
	private static final int COLOR_GREEN = -14503604;
	private static final int COLOR_BROWN = -4621737;
	private static final int COLOR_GRAY = -8421505;
	private static final int COLOR_LIGHT_GRAY = -3947581;
	private static final int COLOR_PURPLE = -6075996;
	private static final int COLOR_RED = -1237980;
	private static final int COLOR_BLUE = -6694422;
	private static final int COLOR_BACKGROUND = -10398145;
	private static final int COLOR_WOOD = -6521554;
	private static final int COLOR_END = -3620889;

	private static byte[][] mas;

//	private static ArrayList<Entity> res;

	public static byte[][] parseBlocks(String filename)
	{		
//		res = new ArrayList<Entity>();
		try
		{
			image = ImageIO.read(new File(filename));
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}

		int width = image.getWidth();
		int height = image.getHeight();
		mas = new byte[width][height];
		for (int i = 0; i < height; i++)
		{
			for (int g = 0; g < width; g++)
			{
				int rgb = image.getRGB(g, i);
				if (rgb == COLOR_GRAY) // rock
				{
					mas[g][i] = IDManager.getBlockID(Rock.class);
				}

				else if (rgb == COLOR_PURPLE)
				{
					mas[g][i] = 127;
				} 
				else if (rgb == COLOR_GREEN)
				{
					mas[g][i] = 126;
				}
				else
				{
//					System.out.println("" + rgb);
				}
			}
		}
		return mas;
	}
	
	public static byte[][] getArr()
	{
		return mas;
	}
}
