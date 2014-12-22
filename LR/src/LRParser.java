/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AkshayaRaj
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

public class LRParser {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Dictionary states= new Hashtable();
	static Dictionary refDict= new Hashtable();
	
	public static Data c = new Data();
	static List<String> state= new ArrayList<String>();
	static List<String> newState=new ArrayList<String>();
	static List<String> refList= new ArrayList<String>();
	static List<String> shiftState=new ArrayList<String>();
	static List<String> reduceState=new ArrayList<String>();
	static int totalStates=0;
	public static void main(String arg[])throws IOException
	{
		System.out.println("Enter the name of the file :");
		String data = c.retrivData(br.readLine());
		String s=makeSimple(data);
               // System.err.println(s);
		addToRefList(s);
		makeRefDict(s);
		String temp=data.charAt(0)+"'->."+data.charAt(0)+"\n"+s;
		System.out.println(temp);
		state.add(temp);
		newState.add(""+totalStates);
		shiftState.add(""+totalStates);
		totalStates=totalStates+1;
		
		while(newState.size()!=0)
		{
			findNewStates(Integer.parseInt(newState.get(0)));
			newState.remove(0);
		}
		String finalStates="";
		for (int i=0;i<state.size();i++)
		{
			System.out.println("State "+i);
			System.out.println(state.get(i));
			String findFinal[]=state.get(i).split("\n");
		
			for (int j=0;j<findFinal.length;j++)
			{
				if (findFinal[j].indexOf(".")==findFinal[j].length()-1)
				{
					finalStates+="Final State "+i+" ["+findFinal[j]+"] \n";
				}
			}
			System.out.println("------------");
		}
		
		
		System.out.println(finalStates);

	}
	
	public static void makeRefDict(String s)
	{
	
		StringTokenizer tz = new StringTokenizer(s,"\n");
		while(tz.hasMoreTokens())
		{
			String getStr=tz.nextToken();
			String content[]=getStr.split("->");
			if (!((Hashtable) refDict).containsKey(content[0].trim()))
				refDict.put(content[0].trim(),getStr.trim());
			else
				refDict.put(content[0].trim(), refDict.get(content[0].trim())+"\n"+getStr);
			
		}
	
	}
	
	public static void addToRefList(String s)
	{
		StringTokenizer tz = new StringTokenizer(s,"\n");
		while(tz.hasMoreTokens())
		{
			refList.add(tz.nextToken().split("->")[1].replace(".",""));
		}
		
	}
	public static void findNewStates(int num)
	{
		StringTokenizer tz = new StringTokenizer(state.get(num).trim(),"\n");
		Dictionary dt = new Hashtable();
		List<String> lis= new ArrayList<String>();
		while(tz.hasMoreTokens() )
		{
			
			StringBuffer stbuf=new StringBuffer(tz.nextToken());
			//System.out.println(stbuf.toString()+"-");
			int dotIndex=stbuf.indexOf(".");
			if (!(dotIndex==stbuf.length()-1) && dotIndex!=-1)
			{
				stbuf=stbuf.deleteCharAt(dotIndex);
					String value=stbuf.insert(dotIndex+1,".").toString();
					if (dotIndex+1!=stbuf.length()-1)
					{
						if ((int) value.charAt(dotIndex+2)>=65 && (int) value.charAt(dotIndex+2)<=90)
						{
							value+="\n"+refDict.get(""+value.charAt(dotIndex+2));
						}
					}
						
					
					if (!((Hashtable) dt).containsKey(""+stbuf.charAt(dotIndex)))
					{
						if (!lis.contains(""+stbuf.charAt(dotIndex)))
							lis.add(""+stbuf.charAt(dotIndex));
						dt.put(""+stbuf.charAt(dotIndex), value);
					}
					
					else
					{
						String str=""+stbuf.charAt(dotIndex);
						
						dt.put(str, dt.get(str)+"\n"+value);
					}
			
			}
		}
		
		for (int i=0;i<lis.size();i++)
		{
			String element=(String) dt.get(""+lis.get(i));
			if (!state.contains(element))
			{
				state.add(element);
				newState.add(""+state.indexOf(element));
			}
		}
		
		states.put(num,dt);
	
	}
	
public static String makeSimple(String data)
	{
		String str="";
		StringTokenizer tz = new StringTokenizer(data,"\n");
		while(tz.hasMoreTokens())
		{
			String conc = tz.nextToken();
			if (conc.contains("/"))
			{
				String concList[]=conc.split("/");
				for (int i=0;i<concList.length;i++)
				{	
					if(!concList[i].equals("(-"))
					{
						if (i!=0)
						str+=concList[0].charAt(0)+"->"+concList[i]+"\n";
						else
							str+=concList[i]+"\n";
					}
				}
			}
			else
			{
				str+=conc;
			}
		}
		return str.replace("->", "->."); 
	}
}

