/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stopandwait;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author AkshayaRaj
 */
public class Machine2 {
    public static void main(String args[]){
        try{
            Socket s=new Socket("localhost",8192);
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
           
            String response="ACK";
            String message="";
            
            do{
                System.out.println("in do loop");
                message=br.readLine();
                System.out.println(message+" got!");
            }
            while(message.length()<1);
            System.out.println("Received data: "+message);
            pw.println("ACK");
            message="";
            do{
                message=br.readLine();
            }
            while(message.length()>2);
            System.out.println("Received data: "+message);
            pw.println("ACK");
            
           
          
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
