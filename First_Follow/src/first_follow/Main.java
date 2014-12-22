/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package first_follow;


public class Main {

	public static void main(String[] args) {
		String[] grammar = new String[3];
		// grammar[0] = "S->Bb/cd";
		// grammar[1] = "B->aB/" + Symbol.EPSILON;
		// grammar[2] = "C->cC/" + Symbol.EPSILON;
		grammar[0] = "E->E+T/T";
		grammar[1] = "T->T*F/F";
		grammar[2] = "F->t/(E)";
		//grammar[3] = "E->TE'";
		//grammar[4] = "";
		//grammar[5] = "F->f/" + Symbol.EPSILON;
		
		
		for(int i=0;i<grammar.length;i++)
			System.out.println(grammar[i]);
		Grammar gr = new Grammar(grammar);
		gr.evaluateFirst();
		gr.evaluateFollow();
	}
}

class Symbol {

	static final String EPSILON = "ep";
	static final String START = "Start";
	static final String NULL = "-";

}