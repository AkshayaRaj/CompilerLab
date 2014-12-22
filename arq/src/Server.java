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
import java.io.PrintWriter;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Server {
    ServerSocket socket;
    ServerSocket socket1;
    public Server(int port){
        try {
            socket=new ServerSocket(port);
            socket1=new ServerSocket(port+2);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void serve(){
        try{
        while(true){
            Socket client=socket.accept();
            Socket client1=socket1.accept();
            BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedReader br1=new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintWriter pw=new PrintWriter(client.getOutputStream(),true);
            PrintWriter pw1=new PrintWriter(client1.getOutputStream(),true);
            
            String line;
            String line1;
            
            line=br.readLine();
            line1=br1.readLine();
            
            if(line!=null){
               // pw1.print(line);
                System.out.print(line);
            }
            
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        Server s=new Server(8080);
        System.out.println("server running..");
        s.serve();
    }
    
}
