package insect.original;

import java.util.Comparator;

public class InsectComparator implements Comparator<Insect>
{
	@Override
	public int compare(Insect i1, Insect i2)
	{
	    return i1.getSize() - i2.getSize();
	}  
	
  
}


