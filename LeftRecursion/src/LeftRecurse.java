/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AkshayaRaj
 */




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

public class LeftRecurse {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static content c=new content();
	static Dictionary first = new Hashtable();
	public static void main(String arg[])throws IOException
	{
		System.out.println("Enter file name: ");
		String data=c.retrivData(br.readLine());
		System.out.println(data);
		List<String> list=new ArrayList<>();
		String send="";
		StringTokenizer tz = new StringTokenizer(data,"\n");
		while(tz.hasMoreElements())
		{
			
			String content[]= tz.nextToken().split("->");
			eliminateLeft(content[0].trim(),content[1].trim());
			try
			{
			leftFactoring(content[0].trim(),content[1].trim());
			}
			catch(Exception e)
			{
				System.out.println("");
			}
		}
	}

	
	public static void eliminateLeft(String LHS,String RHS)
	{	
	if (LHS.equals(""+RHS.charAt(0)))
	{
		String val[]=RHS.split("/");
		System.out.println(LHS+" -> "+val[1]+""+LHS+"'");
		System.out.println(LHS+"' -> "+val[0].replace(LHS,"")+""+LHS+"'/ e");

	}
	}
	
	public static void leftFactoring(String LHS,String RHS)
	{
	try{
		String val[]=RHS.split("/");
		String sub="";
		String first=val[0];
		String second=val[1];
		int i=0;
		int flag=0;
		while((""+first.charAt(i)).equals(""+second.charAt(i)) && i<first.length()-1)
		{
			sub=sub+first.charAt(i);
			i++;
			flag++;
		}
		if(flag!=0)
		{
		System.out.println("Left Factoring");
		System.out.println(LHS+"' "+"-> "+sub);
		System.out.println(LHS+" -> "+first.replace(sub, LHS+"'")+"/"+second.replace(sub, LHS+"'"));
		}
	}
	catch(Exception e)
	{
		System.out.println("");
	}
	}
	}
