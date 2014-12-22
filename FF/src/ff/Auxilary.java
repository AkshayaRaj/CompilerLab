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

public class Auxilary {
	public static void storeFile(String content,int opt,String name) throws IOException
	{
		FileWriter in;
	if (opt==0)
	{//for appending text into the file
		in = new FileWriter(name+".txt",true);
	}
	else
	{//for overwriting the existing file
		in = new FileWriter(name+".txt");
	}
	BufferedWriter fbw = new BufferedWriter(in);	
	if (!content.equalsIgnoreCase(""))
	{
		fbw.write(content);
	
		fbw.newLine();
	}
	fbw.flush();
	in.close();
	
	}
public static String retrivData() throws IOException
	{
	FileReader out = new FileReader("data.txt");
	BufferedReader fbr = new BufferedReader(out);
	String content= null ;
	String line = null;
	while((line = fbr.readLine()) != null)
		{
		content = content+line ;
		}
	return content;
	}

public static String retrivData(String name) throws IOException
{
FileReader out = new FileReader(name+".txt");
BufferedReader fbr = new BufferedReader(out);
String content= null ;
String line = null;
while((line = fbr.readLine()) != null)
	{
	content = content+line+"\n" ;
	}
return content.replace("null", "");
}
}





