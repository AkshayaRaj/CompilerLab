/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stopandwait;
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author AkshayaRaj
 */
public class Machine1 {
    public static void main(String args[]){
        try{
            Socket s=new Socket("localhost",8080);
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
            String string1=JOptionPane.showInputDialog("Enter string 1");
            String string2=JOptionPane.showInputDialog("Enter string 2");
            String response;
            pw.println(string1);
            System.out.println("Sent message 1: "+string1);
            do{
                response=br.readLine();
            }
            while(response.trim().equals("ACK"));
            System.out.println("ACK Received");
            response="";
            
            pw.println(string2);
            System.out.println("Sent message 2: "+string2);
            do{
                response=br.readLine();
            }
            while(response.trim().equals("ACK"));
            System.out.println("ACK Received");
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
