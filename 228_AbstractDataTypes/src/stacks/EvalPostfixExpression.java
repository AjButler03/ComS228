package stacks;

import java.util.Stack;


// busy playing bloons td 5; i haven't a clue how the fuck this works or even what this is really
public class EvalPostfixExpression {
	public static double evaluatepostfix(String exp) {
		Stack<Double> st = new Stack<>();
		
		
		String[] tokens = exp.split("\\s+");
		
		for (String token : tokens) {
			char c = token.charAt(0);
			if (c >= '0' && c <= '9') {
				double temp = (double) (c- '0');
				st.push(temp);
			} else {
				double op1 = st.pop();
				double op2 = st.pop();
				//wtf is a switch?
				switch (c) {
				// supposed to be different cases for different operators
				case '+':
					st.push(op2);
					break;
				case '-':
					st.push(op2 - op1);
					break;
				case '*':
					st.push(op2 * op1);
					break;
				case '/':
					st.push(op2/op1);
					break;
				}
			}
		}
		
		return st.peek();
	}
	public static void main(String args[]) {
		String postfixExpression = "5 6 * 7 +";
		double val = evaluatepostfix(postfixExpression);
		
		// should output 37? 5 * 6 + 7
		System.out.println(val);
	}
}
