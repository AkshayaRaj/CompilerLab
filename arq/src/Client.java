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
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client {
    
    public static void main(String args[]){
        try{
            Socket socket=new Socket("localhost",8080);
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            String data="check";
            pw.print(data);
            
            while(true);
        }
        catch(Exception e){
            e.printStackTrace();
    }
   }
}

