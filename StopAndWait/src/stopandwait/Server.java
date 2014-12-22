/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stopandwait;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author AkshayaRaj
 */
public class Server implements Runnable {
    ServerSocket server;
    ServerSocket server1;
    private Thread t;
    String message;
    
    public Server(int port){
        try{
            server=new ServerSocket(8080);
            server1=new ServerSocket(8192);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    void serve(){
        try{
             while (true){
                 Socket client=server.accept();
                 BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter pw=new PrintWriter(client.getOutputStream());
                 //System.out.println("running server 1");
                 while(true){
                     message=br.readLine();
                     System.out.println(message+" Received");
                 }
             }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        try{
            while(true){
                System.out.println("Thread started");
                    Socket client2=server1.accept();
                    BufferedReader br2=new BufferedReader(new InputStreamReader(client2.getInputStream()));
                    PrintWriter pw2=new PrintWriter(client2.getOutputStream());
                //System.out.println("running server 2");
                
                while(true){
               try{
                    if(message.length()>1){
                        pw2.println(message);
                        System.out.println("Sent message: "+message );
                        pw2.println(message);
                        message="";
                        
                        break;
                        
                    }
                    else
                        System.out.println(message+ " not sent");
                          
               }
               catch(NullPointerException e){
                   continue;
               }
                }
            }
        }
        catch(Exception e){
            System.err.println("Exception in thread");
            e.printStackTrace();
        }
        
    }
    
    public void start(){
        if(t==null){
            t=new Thread(this,"server1");
               t.start();
        }
        
    }
    
    public static void main(String args[]){
        Server s=new Server(8080);
         s.start();
        s.serve();
       
    }
    
}
