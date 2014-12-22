/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Akshaya
 */
package myPack;
import java.util.Stack;

public class InfixToPostfix {
    

	public static class Operators {

		private static String[] operators = { "(", ")", "+", "-", "." ,"*"};

		public static int getPriority(String test) {
			int prior = -1;
			for (String t : operators) {
				if (t.equals(test)) {

					if (t.equals("("))
						return 0;
					else if (t.equals(")"))
						return 0;
					else if (t.equals("+"))
						return 1;
					else if (t.equals("-"))
						return 1;
					else if (t.equals("."))
						return 2;
					else if (t.equals("*"))
						return 3;
					break;
				}
			}
			return prior;
		}

		public static boolean isAnOperator(String test) {
			boolean check = false;

			for (String t : operators) {
				if (t.equals(test)) {
					check = true;
					break;
				}
			}
			return check;
		}

	}

	/**
	 * Converting an infix expression to a postfix expression.
	 */
	public static String infixToPostfix(String infix) {

		String postfix = "";
		Stack<String> stack = new Stack<String>();

		for (int i = 0; i < infix.length(); ++i) {

			Character ch = infix.charAt(i);
			if (Operators.isAnOperator(ch.toString())) {
				// If it is a operator
				if (stack.empty())
					stack.push(ch.toString());

				// The priority of the stack element should be less
				else if (Operators.getPriority(stack.peek()) < Operators
						.getPriority(ch.toString())) {
					stack.push(ch.toString());
				}
				// The priority of the stack element is more
				else {
					// Pop the entire stack
					while (stack.empty() == false){
						String temp= stack.pop();
						if(temp.equals("(") || temp.equals(")"))
							;
						else
							postfix+=temp;
					}
					//Then push the new element on to the empty stack
					stack.push(ch.toString());
				}
			} else {
				// Not a operator
				// Append to the postfix string
				postfix += ch;
			}

		}

		// Append the remaining operators to the postfix string
		while (stack.empty() == false){
			String temp= stack.pop();
			if(temp.equals("(") || temp.equals(")"))
				;
			else
				postfix+=temp;
		}

		return postfix;
	}
}
