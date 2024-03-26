package insect_grasshopper_locust;

public class Main {
	public static void main(String [] args) {
		Grasshopper hoppy = new Grasshopper(5, "Gray");
		
		System.out.println(hoppy.feedOn());
		
		hoppy.attack();
	}
}
