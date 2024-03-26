package insect_grasshopper_locust;


public abstract class Insect
{
	protected int size;			// inches 
	protected String color;
 
	
	public Insect(int s, String c) 
	{
		size = s;
		color = c;
	}

	public int getSize() 
	{
		return size;
	}

	public String getColor() 
	{
		return color;
	}

	public abstract void attack();	
}

