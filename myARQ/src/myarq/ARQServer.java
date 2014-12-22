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
public class ARQServer {
	private ServerSocket server;
	public ARQServer(int port){
		try{
			server = new ServerSocket(port);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void serve() throws IOException{
		while(true){
			Socket client = null;
			try{
				client = server.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter w = new PrintWriter(client.getOutputStream(), true);
				System.out.println("Waiting for client:");
				w.println("Connected:");
				while(true){
				String line;int count=0, flag = -1;String msg = "ACK: ";
				do{
					line = br.readLine();

	

				if(line!=null){
						if(line.equalsIgnoreCase("error")){
							flag = count;
						System.out.println("Frame:"+(count+1)+" contains error!!");
							msg = "DIS: ";
							System.out.println(msg+line);
						}
						else{
							System.out.println(msg+line);
						}
					}
					count++;
				}while(!line.equalsIgnoreCase("end"));
				/*if(flag!=-1)
					flag=-1;*/
				w.println(flag);  			

				if(flag==-1)
					break;
				}
			}catch(Exception f){
				f.printStackTrace();
			}finally{
				client.close();
			}
		}
	}
	public static void main(String args[])throws IOException{
		ARQServer server = new ARQServer(8080);
		server.serve();
	}
}

