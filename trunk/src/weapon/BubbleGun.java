package weapon;

import java.lang.reflect.Method;

import main.World;

public class BubbleGun extends Weapon
{
	private Class bubbletype;
	public BubbleGun(Class btype)
	{
		bubbletype = btype;
	}
	public void useClicked(World world, long x, long y, double angle)
	{
		
	}
	int stamina = 30;
	int power = 8;
	int rad = 68;
	public void use(World world, long x, long y, double angle)
	{
		stamina++;		
		if(stamina > 10)
		{
			stamina = 0;
			Class[] params = new Class[] { long.class, long.class, double.class, double.class, double.class, double.class, World.class };
			Method m;
			try
			{
				m = bubbletype.getMethod("init", params);
				m.invoke(bubbletype.newInstance(), (long)(x+Math.cos(angle)*rad), (long)(y+Math.sin(angle)*rad),
						power*Math.cos(angle), power*Math.sin(angle), 0, 0, world);
			}
			catch (ReflectiveOperationException ex)
			{
				
			}
		}
	}
	public void useControl(World world, long x, long y, double angle)
	{
		
	}
	public void useControlClicked(World world, long x, long y, double angle)
	{
		
	}
	public String getName()
	{
		return "Tool";
	}
}
