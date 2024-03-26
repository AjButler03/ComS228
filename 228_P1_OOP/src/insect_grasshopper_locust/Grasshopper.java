package insect_grasshopper_locust;

public class Grasshopper extends Insect
{	
	public Grasshopper(int size, String color)
	{
		super(size, color); 
	}
	
	@Override 
	public void attack()
	{
		System.out.println("bite"); 
	}

	public String feedOn()
	{
		return "grass"; 
	}
	
	public String antennae() {
		return "Short";
	}
}