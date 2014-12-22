/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ff;

/**
 *
 * @author AkshayaRaj
 */
import java.io.*;
import java.util.*;
public class first_follow {
	
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Auxilary c=new Auxilary();
	static Dictionary first = new Hashtable();
	static Dictionary follow = new Hashtable();
	static List<String> list=new ArrayList<String>();
	
	static List<String> send=new ArrayList<String>();
	public static void main(String arg[])throws IOException
	{
		System.out.println("Enter file name: ");
		String data=c.retrivData(br.readLine());

		StringTokenizer tz = new StringTokenizer(data,"\n");
		while(tz.hasMoreElements())
		{
			String content[]= tz.nextToken().split("->");
			list.add(content[0].trim());
			send.add(content[1].trim());
		}
		for (int i=0;i<list.size();i++)
		{
			
			getFirst(list.get(i),i);
		}
		
		for (int i=0;i<list.size();i++)
		{
			getFollow(list.get(i));
		}
		
		System.out.println("-----FIRST----");
		
		Dictionary dt=new Hashtable();
		dt=(Hashtable) first;
		Enumeration e= dt.keys();
		while(e.hasMoreElements())
		{
			String n=""+e.nextElement();
			System.out.println(n+"->"+dt.get(n));
			
		}
		
		System.out.println("-----FOLLOW----");
		
		Dictionary dt1=new Hashtable();
		dt1=(Hashtable) follow;
		Enumeration e1= dt1.keys();
		while(e1.hasMoreElements())
		{
			String n=""+e1.nextElement();
			System.out.println(n+"->"+dt1.get(n));
			
		}

	}

	public static String getFirst(String value,int index)
	{
		
		String ins="";
		String set;
		if (!(""+send.get(index)).contains("/"))
		{
			set=send.get(index)+"/";
		}
		else
		{
			set=send.get(index);
		}
		String sendList[]=set.split("/");
		
		
	
		for (int i=0;i<sendList.length;i++)
		{
		int k=0; 
		
		while(k<sendList[i].length())
		{

		if((((int) sendList[i].charAt(k)>=65 && (int) sendList[i].charAt(k)<=90 && (int) sendList[i].charAt(k)!= (int) value.charAt(0)) ))
		{
			if((boolean) ((Hashtable) first).containsKey(sendList[i].charAt(k)))
			{
				if(!ins.contains((CharSequence) (first.get(sendList[i].charAt(k)))))
				ins=ins+first.get(sendList[i].charAt(k))+",";
				if (send.get(list.indexOf(send.get(index).charAt(k))).contains("(-"))
				{
					k++;
				}
				else
				{
					break;
				}
			}
			
			else
			{
				
				ins=ins+getFirst(""+sendList[i].charAt(k),list.indexOf(""+sendList[i].charAt(k)));	
				if (send.get(list.indexOf(""+sendList[i].charAt(k))).contains("(-"))
				{
					k++;
				}
			else
			{
					break;
			}
			}
		}
		else if((""+sendList[i].charAt(k)).equals(value))
		{
		System.out.println("");	
		break;
		}
		else
		{
			//if(!(""+sendList[i].charAt(k)).equals("("))
				ins=ins+sendList[i].charAt(k)+",";
			break;
		}
		}
		}
		
		if(!(boolean) ((Hashtable) first).containsKey(value))
		{
			first.put(value, ins);
		}
		
		
		return ins;
	}
	
	
	public static String getFollow(String token)
	{
		String ins="";
		int flag=0;
		if (!(boolean) ((Hashtable) follow).containsKey(token))
		{
		
		for(int i=0; i<send.size();i++)
		{
			
			if (send.get(i).contains(token))
			{
				flag=1;
				String splits;
				if (!send.get(i).contains("/"))
					splits=send.get(i)+"/";
				else
					splits=send.get(i);
				
				String sendList[]=splits.split("/");
				
				for (int k=0;k<sendList.length;k++)
				{
				if(sendList[k].contains(token))
				{
					//System.out.println(sendList[k]+"|"+token);
				int posi=sendList[k].indexOf(token);
				if (posi==sendList[k].length()-1)
				{
					//System.out.println(list.get(i));
					if (!getFollow(list.get(i)).equals(ins))
						ins=ins+getFollow(list.get(i));
				}
				else if ((int)sendList[k].charAt(posi+1)>=65 && (int)sendList[k].charAt(posi+1)<=90)
				{
					if (!ins.equals(first.get(sendList[k].charAt(posi+1))))
						ins=ins+first.get(sendList[k].charAt(posi+1));
					
				}
				
				else
				{
					ins=ins+sendList[k].charAt(posi+1)+",";
				}
				}
				}
			}
			

		}
		if(flag==1)
		{
			if (token.equals(list.get(0)))
					follow.put(token, "$,"+ins);
			else
				follow.put(token, ins);
		}
		else
			follow.put(token,"$,");
		}
		
		else
		{
			ins=(String) follow.get(token);
		}
		
	
		return ins ;
		}
	
}