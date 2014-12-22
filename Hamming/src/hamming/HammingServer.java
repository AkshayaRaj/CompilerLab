/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hamming;

/**
 *
 * @author AkshayaRaj
 */
import java.io.*;
import java.net.*;


public class HammingServer {
	private ServerSocket server;
	public HammingServer(int port){
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
				if(parity==48)
					parity=0;
				else
					parity=1;
				String input;input = br.readLine();
				System.out.println("Input:"+input);
				char[] bits = input.toCharArray();
				char[] redundantbits = new char[11];
				int[] r1check = {1,3,5,7,9,11};
				int[] r2check = {2,3,6,7,10,11};
				int[] r4check = {4,5,6,7};
				int[] r8check = {8,9,10,11};
				redundantbits[0] = redundancy(bits, r1check, parity);
				redundantbits[1] = redundancy(bits, r2check, parity);
				redundantbits[3] = redundancy(bits, r4check, parity);
				redundantbits[7] = redundancy(bits, r8check, parity);
				int count=0;
				for(int i=0;i<redundantbits.length;i++){
					if(redundantbits[i]=='1'){
						count+=(i+1);
						System.out.println(i);
					}
				}
				System.out.println("Error occured:"+(count));
				w.println(count);
				//System.out.println(input);
				
			}
		}catch(Exception f){
			f.printStackTrace();
		}
	}
	public static void main(String args[])throws IOException{
		HammingServer server = new HammingServer(8080);
		server.serve();
	}
	public static char redundancy(char[] input, int[] rcheck, int parity){
		char redu = '0';
		int count=0;
		for(int i:rcheck){
			if(input[i-1]=='1')
				count+=1;
		}
		if(count%2!=parity)
			redu='1';
		
		return redu;
	}
}

 class sender {
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
 }
