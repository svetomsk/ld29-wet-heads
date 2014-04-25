package main;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pictures 
{
    public static Image pause;
    
    public static Image complete_level[];
    public static Image blood;
    
    public static Image flame_item;
    public static Image poison_item;
    public static Image snow_item;
    public static Image rocket_item = read("resources/rocketforhead.png", 64, 64);
    public static Image rocket_presized = read("resources/rocket.png", 64, 64);
    
    public static Image snow;
    public static Image rocket;
    public static Image flame;
    public static Image poison;
    
    public static Image level[];

    public static Image bodysnake;
    public static Image tailsnake;
    public static Image headsnake;
    
    public static Image apple;
    public static Image pear;
    
    public static Image spark1 = read("resources/spark1.png", 16, 16);
    public static Image spark2 = read("resources/spark2.png", 16, 16);
    public static Image spark3 = read("resources/spark3.png", 16, 16);

    public static Image simpleSnakePart = read("resources/simplebodysnake.png", 32, 32);
    public static Image simpleSnakeHead = read("resources/simpleheadsnake.png", 48, 48);
    public static Image simpleSnakeTail = read("resources/simpletailsnake.png", 64, 48);
    
//    public static Image enemy = read("resources/enemy.png", 64, 64);
    public static Image monster = read("resources/monster.png", 64, 64);

	public static Image explode = read("resources/explode.png", 512, 512);

    
    public Pictures()
    {
        try 
        {
        	pause = ImageIO.read(new File("resources/pause.png"));
        	
        	bodysnake = ImageIO.read(new File("resources/bodysnake.png"));
        	tailsnake = ImageIO.read(new File("resources/tailsnake.png"));
        	headsnake = ImageIO.read(new File("resources/headsnake.png"));
            
        	blood = ImageIO.read(new File("resources/blood.png"));
        	
        	rocket = ImageIO.read(new File("resources/rocket.png"));
        	flame = ImageIO.read(new File("resources/fireball.png"));
        	poison = ImageIO.read(new File("resources/poison.png"));
        	snow = ImageIO.read(new File("resources/snow.png"));
        	
        	poison_item = ImageIO.read(new File("resources/poison_item.png"));
        	flame_item = ImageIO.read(new File("resources/fire_item.png"));
        	snow_item = ImageIO.read(new File("resources/snow_item.png"));
        	
        	apple = ImageIO.read(new File("resources/apple.png"));
        	pear = ImageIO.read(new File("resources/pear.png"));
            
            level = new Image[3];
            level[0] = ImageIO.read(new File("resources/level1.png"));
            level[1] = ImageIO.read(new File("resources/level2.png"));
            
            complete_level = new Image[3];
            complete_level[0] = ImageIO.read(new File("resources/level1_complete.png"));
            complete_level[1] = ImageIO.read(new File("resources/level1_complete.png"));
            
            Canvas s = new Canvas();
            
            AreaAveragingScaleFilter aasf = new AreaAveragingScaleFilter((int)128, (int) 128);
            
            aasf = new AreaAveragingScaleFilter(12, 12);
            blood= s.createImage(new FilteredImageSource(blood.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 75);
            rocket = s.createImage(new FilteredImageSource(rocket.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 75);
            flame = s.createImage(new FilteredImageSource(flame.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 75);
            snow = s.createImage(new FilteredImageSource(snow.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 86);
            poison = s.createImage(new FilteredImageSource(poison.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(64, 64);
            apple = s.createImage(new FilteredImageSource(apple.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(64, 64);
            flame_item = s.createImage(new FilteredImageSource(flame_item.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(64, 64);
            snow_item = s.createImage(new FilteredImageSource(snow_item.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(64, 64);
            poison_item = s.createImage(new FilteredImageSource(poison_item.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(128, 128);
            pear = s.createImage(new FilteredImageSource(pear.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(5130, 2550);
            level[0] = s.createImage(new FilteredImageSource(level[0].getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(5090, 2540);
            level[1] = s.createImage(new FilteredImageSource(level[1].getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(240, 320);
            complete_level[0] = s.createImage(new FilteredImageSource(complete_level[0].getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 32);
            bodysnake = s.createImage(new FilteredImageSource(bodysnake.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(32, 48);
            tailsnake = s.createImage(new FilteredImageSource(tailsnake.getSource(), aasf));
            
            aasf = new AreaAveragingScaleFilter(64, 64);
            headsnake = s.createImage(new FilteredImageSource(headsnake.getSource(), aasf));
            
        } catch (IOException ex) 
        {
        	ex.printStackTrace();
//            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static Image read(String str, int w, int h)
    {
    	Image res = null;
    	try 
        {
    		res = ImageIO.read(new File(str));
    		
    		Canvas s = new Canvas();
            AreaAveragingScaleFilter aasf = new AreaAveragingScaleFilter(w, h);
            res = s.createImage(new FilteredImageSource(res.getSource(), aasf));
        }
    	catch (IOException ex) 
        {
        	ex.printStackTrace();
//            Logger.getLogger(Pictures.class.getName()).log(Level.SEVERE, null, ex);
        }
    	return res;
    }
    public static Image[] loadAndCut(String name, int sw, int scale) 
    {
    	Image buf = null;
    	try {
			buf = ImageIO.read(new File(name));
		} catch (IOException e) {}
    	
    	Image[] res = new Image[sw];
    	Canvas s = new Canvas();
    	
    	for(int q=0;q<sw;q++)
    	{
    		CropImageFilter filter = new CropImageFilter(q*buf.getWidth(null)/sw, 0, buf.getWidth(null)/sw, buf.getHeight(null));
    		res[q] = s.createImage(new FilteredImageSource(buf.getSource(), filter));
    		
    		AreaAveragingScaleFilter aasf = new AreaAveragingScaleFilter(scale, scale);
    		res[q] = s.createImage(new FilteredImageSource(res[q].getSource(), aasf));
    	}
    	return res;
	}
    
}