/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parity;

/**
 *
 * @author AkshayaRaj
 */
import java.io.*;
import java.net.*;
public class sender {
	private ServerSocket server;
	public sender(int port){
		try{
			server = new ServerSocket(port);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void serve() throws IOException{
		
			Socket client = null;
			try{
				client = server.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter w = new PrintWriter(client.getOutputStream(), true);
				System.out.println("Waiting for input from client:");
				w.println("Connected:");
			while(true){
				int parity;
				parity = br.read();
				System.out.println("parity"+parity);
				String input;input = br.readLine();char[] characters = new char[input.length()];
				System.out.println("input:"+input);
				int count=0;
				characters = input.toCharArray();
				for(char i:characters){

					if(i=='1')
						count++;
					System.out.println("count"+count);
				}



				if(parity==48){		
					if(count%2==0)
						input = input.concat("1");
					else
						input= input.concat("0");
				}else if(parity==49){
					if(!(count%2==0))
						input = input.concat("1");
					else
						input = input.concat("0");
				}
				w.println(input);
			}
			}catch(Exception i){
				i.printStackTrace();
			}
		}
	public static void main(String args[])throws IOException{
		sender server = new sender(8080);
		server.serve();
	}
}
