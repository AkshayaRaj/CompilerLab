/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AkshayaRaj
 */
import java.util.Scanner;
public class srp
{
	
	static String zap[]=new String[10];
	static String input;
	static int start;
	public static void main(String[] args)
	{
		//SHIFT REDUCE PARSER
		/*
		 E->E+T/T
		 T->T*F/F
		 F->(E)/i
		 
		 input - i*i+i
		 
		E->E$
		E->E+T
		E->T
		T->T*F
		T->F
		F->(E)
		F->i
		  
		 */
		
		Scanner buf = new Scanner(System.in);
		System.out.println("No. of production lines:");
		int prod=buf.nextInt();
		
		
		System.out.println("Enter the productions :");
		for(int x=0;x<prod;x++)
		{
			zap[x]=buf.next();												//zap[] has the productions
		}
		
		
		System.out.println("Enter the input:");
		input=buf.next();
		input+='$';
		System.out.println("Stack\tInput\tAction\n");
		String stack=new String("");
		String tempstack=new String("");
		
		for(int x=0;x<input.length();x++)
		{
		 tempstack=stack+input.charAt(x);
		 boolean bool = ifPresent(prod, tempstack);
		 if(bool)
		  {
			 stack=shiftinput(stack, tempstack);
		  }
		 else if(!bool)
		  {
			 if(stack.length()==1)
			 {
			   stack=reduceprod(prod, stack);
			   x-=1;
			 }
			 else if(stack.length()>1&&ifPresent(prod, stack)==false)
			 {
				 String partition=""+stack.charAt(2);
				 for(int i=0;i<stack.length()-1;i++)
						System.out.print(stack.charAt(i));
				 partition=reduceprod(prod, partition);
				 stack=stack.replace(stack.charAt(2), partition.charAt(0));
				 x-=1;
			 }
			 else if(stack.length()>1&&ifPresent(prod, ""+tempstack.charAt(2))==true && ifPresent(prod, stack)==true && stack.length()==2)
			 {
				 stack=shiftinput(stack, tempstack);
			 }
			 else
			 {
				 stack=reduceprod(prod, stack);
				 x-=1;
			 }
		  }
		}

		
		
		

		
		System.out.println(stack+"\t\tAccept");
		
		
		
	}
	
	static boolean ifPresent(int prod,String tempstack)
	{	
		boolean bool=false;
		for(int i=0;i<prod;i++)
		{
		if(zap[i].contains(tempstack))
			{
				bool=true;
				break;
			}
		}
		return bool;
	}
	static String reduceprod(int prod,String stack)
	{
		for(int i=0;i<prod;i++)
		 {
		if(zap[i].contains(stack))
		  {
			if(zap[i].length()==stack.length()+3)
			{
				System.out.println(stack+"\t"+dispInput()+"\t"+"Reduce "+zap[i]);
				stack=new String(""+zap[i].charAt(0));
				break;
			}
		  }
		 }
		return stack;
	}
	static String shiftinput(String lol,String tempstack)
	{
		System.out.println(lol+"\t"+dispInput()+"\t"+"Shift");
		start+=1;
		String stack=new String(tempstack);
		return stack;
	}
	static String dispInput()
	{
		String current = new String("");
		for(int j=start;j<input.length();j++)
		{
			current+=input.charAt(j);
		}
		return current;
	}
}
