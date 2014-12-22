/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myarq;

/**
 *
 * @author AkshayaRaj
 */
import java.io.*;
import java.net.*;
import java.util.*;
public class ARQClient {
	public static void main(String args[])throws IOException{
		Socket client = new Socket("localhost", 8080);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader socket_read = new BufferedReader(new InputStreamReader(client.getInputStream()));
		PrintWriter socket_write = new PrintWriter(client.getOutputStream(), true);
		System.out.println(socket_read.readLine());
		System.out.println("Enter input:");
		String input, error;int no_tokens=0, err=-2;
		input = br.readLine();
		StringTokenizer str = new StringTokenizer(input, " ");
		List<String> tokens = new ArrayList<String>(); 
		no_tokens = str.countTokens();
		for(int i=0;i<no_tokens;i++)
			tokens.add(str.nextToken());
		send_tokens(tokens, socket_write);
		do{
			error = socket_read.readLine();
			if(error!=null){
			err = Integer.parseInt(error);
			if(err>=0){
				System.out.println("Frame:"+(err+1));
				List<String> repeat_list = tokens.subList(err+1, tokens.size());
				System.out.println("Resending: "+repeat_list);
				send_tokens(repeat_list, socket_write);
			}	
}
		
}







while(err!=-1);
		System.out.println("All data sent correctly!!");
		client.close();
	}
	public static void send_tokens(List<String> tokens, PrintWriter pw){
		for(String token: tokens){
			pw.println(token);
			System.out.println("Sent: "+token);
		}
	}
}
