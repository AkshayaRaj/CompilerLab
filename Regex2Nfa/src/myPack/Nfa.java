package myPack;

import myPack.InfixToPostfix.Operators;
import java.util.ArrayList;



public class Nfa {

	class State {

		State nextNode;
		State previousNode;
		private int stateCount = 0;
		private String toState[] = new String[10];
		// Goes to state on a particular input
		private int id;
		private boolean isFinalState;

		public State(int id) {

			nextNode = null;
			isFinalState = false;
			this.id = id;

			for (int i = 0; i < 10; ++i) {
				toState[i] = Symbol.NULL;
			}
		}

		public int getCount() {
			return stateCount;
		}

		public void incrimentCount() {
			++stateCount;
		}

		public void setStateOnInput(String input, int outputState) {
			int loc = getExprLocation(input);
			// get the index of the expr to store it in
			Integer iObj = new Integer(outputState);
			if(toState[loc].equals(Symbol.NULL))
				toState[loc] = iObj.toString();
			else
				toState[loc]+=","+iObj.toString();
		}

		// reset the state
		public void resetState() {
			for (int i = 0; i < 10; ++i) {
				toState[i] = Symbol.NULL;
			}
		}

		public boolean isFinalState() {
			return isFinalState;
		}

		private void setFinalState(boolean b) {
			isFinalState = b;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

	}

	private int idCount = 1;
	// 0 is the id of the startNode
	// The start node of the nfa
	State startNode;

	/**
	 * Get the last node of the Nfa.
	 */
	public State getLastNode() {

		State temp = startNode;
		while (temp.nextNode != null) {
			temp = temp.nextNode;
		}
		temp.setFinalState(true);
		return temp;

	}

	private ArrayList<String> expr;

	public Nfa() {
		startNode = new State(1);
		// startNode.setFinalState(true);
		expr = new ArrayList<String>();
		expr.add(Symbol.EPSILON);
	}

	/**
	 * count the number of expressions present in the string and store them in
	 * expr
	 */
	public void setExpr(String re) {
		Character ch;

		for (int i = 0; i < re.length(); ++i) {
			ch = re.charAt(i);

			if (isExpression(ch.toString()))
				;
			else if (Operators.isAnOperator(ch.toString()))
				;
			else {
				expr.add(ch.toString());
			}
		}
	}

	/**
	 * checks if the String is present in expr
	 */
	public boolean isExpression(String test) {
		boolean check = false;
		for (String t : expr) {
			if (t.equals(test)) {
				check = true;
				break;
			}
		}
		return check;
	}

	/**
	 * return the location of the expression in the ArrayList
	 */
	public int getExprLocation(String test) {
		int loc = -1;
		for (int i = 0; i < expr.size(); ++i) {
			if (test.equals(expr.get(i))) {
				loc = i;
				break;
			}
		}
		return loc;
	}

	/**
	 * Prints the entire Nfa via the state and the corresponding expressions
	 */
	public void print() {

		System.out.print("State\t");
		for (int it = 0; it < expr.size(); ++it)
			System.out.print(expr.get(it) + "\t");

		System.out.println();

		// Print the Nfa
		State temp = startNode;
		while (temp != null) {
			System.out.print(temp.getId() + "\t");
			for (int j = 0; j < expr.size(); ++j) {
				System.out.print(temp.toState[j] + "\t");
			}
			System.out.println();
                        System.out.println("--------------------------------------------");
			temp = temp.nextNode;
		}
		System.out.println();
                
	}

	public Nfa toNfa(String postfix) {
		setExpr(postfix);
		// set expression of the Nfa whose method is setExpr, i.e. the Nfa which
		// called the method
		Nfa nfa = new Nfa();
		nfa.setExpr(postfix);
		Character ch;
		State temp = nfa.startNode;

		for (int i = 0; i < postfix.length(); ++i) {
			ch = postfix.charAt(i);

			if (Operators.isAnOperator(ch.toString())) {
				if (ch.toString().equals("+")) {
					// Go 2 steps back;
					temp.previousNode.resetState();
					State temp2 = temp.previousNode.previousNode;
					Character ch2 = postfix.charAt(i - 1);
					temp2.setStateOnInput(ch2.toString(), --idCount);
					
					// Go 1 node back to perform the iterations again if
					// required
					temp = temp.previousNode;
				} else if (ch.toString().equals("*")) {
					temp.setStateOnInput(Symbol.EPSILON, idCount-1);
					//To previous node
					
					temp.nextNode = new State(++idCount);
					temp.nextNode.previousNode = temp;
					temp.setStateOnInput(Symbol.EPSILON, idCount);
					//To next node
					
					State temp2 = temp.previousNode;
					temp2.setStateOnInput(Symbol.EPSILON, idCount);
					//Skip entirely
					
					//Traverse to the latest node
					temp = temp.nextNode;

				} else if(ch.toString().equals(".")){
					
				}
				//Do nothing when . comes just to separate the operators
			} else {

				temp.setStateOnInput(ch.toString(), idCount + 1);
				temp.nextNode = new State(++idCount);
				temp.nextNode.previousNode = temp;
				temp = temp.nextNode;

			}
		}
		return nfa;
	}

}

class Symbol {

	static final String EPSILON = "Ep";
	static final String START = "Start";
	static final String NULL = "null";

}

class Run{
    public static void main(String args[]){
        //System.out.println(InfixToPostfix.infixToPostfix("a.b*c.d*.(a+b)"));
        Nfa nfa = new Nfa();
        nfa = nfa.toNfa(InfixToPostfix.infixToPostfix("(a+b)"));
      //   nfa = nfa.toNfa(InfixToPostfix.infixToPostfix("(a+b)*ab"));
	nfa.print();
    }
}