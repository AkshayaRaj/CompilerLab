

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeftRecursion{
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AkshayaRaj
 */
/*
//Eliminate left recursion 
public class LeftRecursion {
    
    Reader fileIn;
    StringTokenizer st;
    StringBuffer input;
    String inputString;
    String right;
    String left;
    String prod1;
    String prod2;

    public LeftRecursion()  {
        try{
        input=new StringBuffer(new Scanner(new File("productions.txt")).useDelimiter("\\Z").next());
        inputString=input.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(input.toString());   
        prod1="";
        prod2="";
        left="";
    }

   void getProductions(){
            for(int i=0;i<inputString.length();i++){
                if(inputString.charAt(i)=='>')
                {
                    i-=2;
                    left+=inputString.charAt(i);
                    prod1+=left+"->";
                    prod2+=left+"`->";
                    i+=3;
                    right+='|';
                 for(int j=i;j<inputString.length();j++)   {
                     right+=inputString.charAt(j);
                 }
                 break;
                }                
            }
            st=new StringTokenizer(right,"|");
            st.nextElement();
            while(st.hasMoreElements()){
                String next=(String)st.nextElement();
               // System.out.println(next);
                if(next.charAt(0)==left.charAt(0)){
                    prod2+=next.substring(1,next.length())+left+'`'+'|';
                    
                }
                else if(next.charAt(0)>='a' && next.charAt(0)<='z'){
                    prod1+=next+left+"`|";
                }
                                
            }
            prod2+="e";
    System.out.println(prod1.substring(0,prod1.length()-1));        
    System.out.println(prod2);
    leftFactor(prod1);
}
   void leftFactor(String prod){
       ArrayList terms=new ArrayList();
       StringTokenizer st=new StringTokenizer(prod.substring(prod.indexOf(">")+1),"|");
       while(st.hasMoreTokens()){
       //System.out.println(st.nextToken());
           String token=st.nextToken();
           terms.add(token);
       }
   }
}

class Run{
    public static void main(String args[])  {
        LeftRecursion lr=new LeftRecursion();
        lr.getProductions();
        
    }
  }
*/