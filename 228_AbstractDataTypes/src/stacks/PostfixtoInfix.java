package stacks;

import java.util.Stack;

public class PostfixtoInfix {
	
	public static boolean isOperand(String token) {
		
		return token.matches("[A-Za-z0-9]");
	}
	
	public static String Postfixtoinfix(String exp) {
		Stack<String> st = new Stack<>();
		
		String[] tokens = exp.split("\\s+");
		
		for (String token : tokens) {
			if (isOperand(token)) {
				st.push(token);
			} else {
				String op2 = st.pop();
				String op1 = st.pop();
				String infix = "(" + op1 + " " + token + " " + op2 + ")";
				st.push(infix);
			}
		}
		
		return st.pop();
	}
	
	public static void main(String[] args) {
		String postfix = "7 3 - 2 * 3 +";
		
		//test here
		
	}
}
