package insect;


public abstract class Insect implements Comparable<Insect> 
{
	protected int size;			// inches 
	protected String color;
 
	
	public Insect(int size, String color) 
	{
		this.size = size;
		this.color = color;
	}

	public int getSize() 
	{
		return size;
	}

	public String getColor() 
	{
		return color;
	}
	
	@Override 
	public int compareTo(Insect i)
	{
	    return size - i.getSize();
	}  

	public abstract void attack();	
}

