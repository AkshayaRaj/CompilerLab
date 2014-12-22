/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package leadntrail;

/**
 *
 * @author AkshayaRaj
 */import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;


public class LeadNTrail
{
	static char[] STACK=new char[10];

	static int stack_ctr=-1;
	static void push(char item)
	{
		stack_ctr++;
		STACK[stack_ctr]=item;
	}
	static char pop()
	{
		char temp= STACK[stack_ctr];
		stack_ctr--;
		return temp;
	}
	static String reverse(String original)
	{
		String reverse = new String("");
		int length = original.length();

		  for ( int i = length - 1 ; i >= 0 ; i-- )
		     reverse = reverse + original.charAt(i);
		return (reverse);
	}
	
	
	public static void main(String arga[])
	{
		String input;int prod;String producer=new String();
		Scanner buf=new Scanner(System.in);
		//System.out.println("No. of production lines :");
		//prod=buf.nextInt();
                
                prod=Integer.parseInt(JOptionPane.showInputDialog("No. of production lines") );
        
        String ff[]=new String[prod];
		String zap[]=new String[prod];

		System.out.println("Enter the productions :");
		for(int x=0;x<prod;x++)
		{
		//zap[x]=buf.next();												//zap[] has the productions
                    zap[x]=JOptionPane.showInputDialog("Enter production "+(x+1));
		}
		
		for(int x=0;x<prod;x++)
		{
        ff[x]="";
		StringTokenizer sr = new StringTokenizer(zap[x],"->/");
		producer=sr.nextToken();
		System.out.print("\nLEAD("+producer+") - ");
		
		int inner=1;
		while(inner==1)
		{
		while(sr.hasMoreTokens())
		{
			String str=sr.nextToken();
			if(str.charAt(0)==producer.charAt(0))
            {
				for(int k=1;k<str.length();k++)
				{
					if( !Character.isUpperCase(str.charAt(k)) )
						{
							System.out.print(str.charAt(k)+"  ");
							break;
						}
				}
                
            }
			else if(Character.isUpperCase(str.charAt(0)))
				push(str.charAt(0));
			else if(!Character.isUpperCase(str.charAt(0)))
			{
                System.out.print(str.charAt(0)+"  ");
            }
		}
		
		if(stack_ctr!=(-1))											//stack not empty
		{	
			producer=pop()+"";
			inner=1;
			for(int y = x+1;y<prod;y++)
			{
				sr=new StringTokenizer(zap[y],"->/");
				String polka=sr.nextToken();
				if(producer.charAt(0)==polka.charAt(0))
					break;
			}
		}
		else if(stack_ctr==(-1))
		{
			inner=0;
		}
		
		}
		
		}

		System.out.print("\n");
		
		//here's to trailing
		for(int x=0;x<prod;x++)
		{
        ff[x]="";
		StringTokenizer sr = new StringTokenizer(zap[x],"->/");
		producer=sr.nextToken();
		System.out.print("\nTRAIL("+producer+") - ");
		
		int inner=1;
		while(inner==1)
		{
		while(sr.hasMoreTokens())
		{
			String str=sr.nextToken();
			str=reverse(str);
			if(Character.isUpperCase(str.charAt(0)))
            {
				if(str.length()!=1)
				{
				for(int k=1;k<str.length();k++)
				{
					if( !Character.isUpperCase(str.charAt(k)) )
						{
							System.out.print(str.charAt(k)+"  ");
							break;
						}
				}
				}
				else if(str.length()==1)
				{
					push(str.charAt(0));
				}
                
            }
			/*else if(Character.isUpperCase(str.charAt(0)))
				push(str.charAt(0));*/
			else if(!Character.isUpperCase(str.charAt(0)))
			{
                System.out.print(str.charAt(0)+"  ");
            }
		}
		
		if(stack_ctr!=(-1))											//stack not empty
		{	
			producer=pop()+"";
			inner=1;
			for(int y = x+1;y<prod;y++)
			{
				sr=new StringTokenizer(zap[y],"->/");
				String polka=sr.nextToken();
				if(producer.charAt(0)==polka.charAt(0))
					break;
			}
		}
		else if(stack_ctr==(-1))
		{
			inner=0;
		}
		
		}
		
		}
       
		
	}
}
		
	