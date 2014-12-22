/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package first_follow;

/**
 *
 * @author AkshayaRaj
 */


public class Grammar {

	private String[] grammar;
	private String[] lhs;
	private String[] rhs;
	// respective sides of the grammar

	private String[] first;
	private String[] follow;

	/**
	 * Constructor
	 * 
	 * @param gram
	 */
	public Grammar(String[] gram) {
		grammar = gram;
		lhs = new String[grammar.length];
		rhs = new String[grammar.length];

		for (int i = 0; i < grammar.length; ++i) {
			String[] temp = grammar[i].split("->");
			lhs[i] = temp[0];
			rhs[i] = temp[1];
		}
		first = new String[grammar.length];
		follow = new String[grammar.length];
	}

	/**
	 * Evaluate the first of the grammar
	 */
	public void evaluateFirst() {
		System.out.println("FIRST : \n");
		boolean skip[] = new boolean[grammar.length];
		for (int z = 0; z < grammar.length; ++z)
			skip[z] = false;

		// outer:
		for (int i = 0; i < grammar.length; ++i) {
			first[i] = "";
			String[] rhsToken = rhs[i].split("/");
			for (int j = 0; j < rhsToken.length; ++j) {
				// The first is a non-terminal as well

				if (Character.isUpperCase(rhsToken[j].charAt(0))) {
					skip[i] = true;
					continue;
				} else {
					if (rhsToken[j].charAt(0) == '#')
						first[i] += Symbol.EPSILON + " ";
					else
						first[i] += rhsToken[j].charAt(0) + " ";
				}

			}

		}

		for (int i = 0; i < grammar.length; ++i) {
			String[] rhsToken = rhs[i].split("/");
			if (skip[i] == true) {
				for (int j = 0; j < rhsToken.length; ++j) {
					if (Character.isUpperCase(rhsToken[j].charAt(0))) {
						StringBuffer sbfr = new StringBuffer(rhsToken[j]);
						// Keep on removing tokens from the rhs to compare with
						// the lhs
						while (true) {
							for (int k = 0; k < grammar.length; ++k) {
								// First(S) = First(N.T.)
								if (lhs[k].charAt(0) == sbfr.charAt(0)) {
									first[i] += first[k];
								}
							}

							if (first[i].contains("#")) {

								sbfr.replace(0, 1, "");
								if (sbfr.length() == 0)
									break;
								else if (Character.isLowerCase(sbfr.charAt(0))) {
									first[i] += sbfr.charAt(0) + " ";
									break;
								}

							} else
								break;
						}
					}

				} // rhs tokens iterator ends
			}
		}
		removeDuplicates(first);
		for (String k : first)
			System.out.println(k);
	}

	/**
	 * Removes the duplicates from the first string
	 */
	private void removeDuplicates(String[] first) {

		for (int i = 0; i < first.length; ++i) {
			if (first[i].length() > 2) {
				StringBuffer sbfr = new StringBuffer(first[i]);
				// Remove duplicates
				for (int j = 0; j < sbfr.length() - 1; ++j) {
					for (int k = j + 1; k < sbfr.length(); ++k) {
						if (sbfr.charAt(j) == sbfr.charAt(k)
								&& (!Character.isWhitespace(j))) {
							sbfr.replace(k, k + 1, " ");
						}
					}
				}

				// Remove more than 1 whitespace
				for (int j = 0; j < sbfr.length() - 1; ++j)
					for (int k = j + 1; k < sbfr.length(); ++k)
						if (sbfr.charAt(j) == sbfr.charAt(k)
								&& (Character.isWhitespace(j)))
							sbfr.deleteCharAt(j);

				first[i] = sbfr.toString();
			}
		}

	}

	/**
	 * Evaluate the follow of the expression
	 */
	public void evaluateFollow() {

		boolean skip[] = new boolean[grammar.length];
		
		for (int z = 0; z < grammar.length; ++z) {
			String l = lhs[z];
			skip[z] = false;
			if(z==0)
				follow[z] = "$ ";
			else
				follow[z] = "";
			int ownGram = z;
			
			for (int i = 0; i < rhs.length; ++i) {
				if (i != ownGram) {
					for (int j = 0; j < rhs[i].length(); ++j) {
						if (l.charAt(0) == rhs[i].charAt(j)) {

							if ((j + 1) != rhs[i].length()) {
								if (rhs[i].charAt(j + 1) == '/') {
									skip[z] = true;

								} else if (Character.isUpperCase(rhs[i]
										.charAt(j + 1))) {
									follow[ownGram] += first[getFirstIndex(rhs[i]
											.charAt(j + 1))] + " ";
								} else if (Character.isLowerCase(rhs[i]
										.charAt(j + 1))) {
									follow[ownGram] += rhs[i].charAt(j + 1) + " ";
								}
							} else{
								skip[z] = true;
							}
						}
					}
				}
			}
		}
		
		for(int z=0; z<grammar.length ; ++z){
			if(skip[z]){
				String l = lhs[z];
				int ownGram = z;
				
				for (int i = 0; i < rhs.length; ++i) {
					if (i != ownGram) {
						for (int j = 0; j < rhs[i].length(); ++j) {
							if (l.charAt(0) == rhs[i].charAt(j)) {

								if ((j + 1) != rhs[i].length()) {
									if (rhs[i].charAt(j + 1) == '/') {
										follow[z] += follow[i];

									} else if (Character.isUpperCase(rhs[i]
											.charAt(j + 1)));
									else if (Character.isLowerCase(rhs[i]
											.charAt(j + 1))) ;
								} else{
									follow[z] += follow[i];
								}
							}
						}
					}
				}
			}
		}
		
		
		System.out.println("\n FOLLOW");
		for (int j = 0; j < follow.length; ++j)
			System.out.println(follow[j]);

	}
	
	

	private int getFirstIndex(char k) {
		int index = -1;
		for (int i = 0; i < lhs.length; ++i) {
			if (lhs[i].charAt(0) == k) {
				index = i;
				break;
			}
		}
		return index;
	}
}
