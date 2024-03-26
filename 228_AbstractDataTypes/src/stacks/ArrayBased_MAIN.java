package stacks;

public class ArrayBased_MAIN {

	public static void main(String[] args) {
		ArrayBasedStack<String> stack = new ArrayBasedStack<>();
		
		stack.push("Dog");
		stack.push("Cat");
		stack.push("rat");
		
		System.out.println(stack.size());
		System.out.println(stack.peek());

	}

}
