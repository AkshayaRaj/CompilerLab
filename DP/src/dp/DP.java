/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dp;

import java.math.*;

/**
 *
 * @author AkshayaRaj
 */
public class DP {
   static int mins[];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        for(int i=1;i<1000;i++){
            mins=new int[i+2];
            for(int q=0;q<mins.length;q++)
                mins[q]=-1;
            
        System.out.println("fMin("+i+")= "+fMin(i));
        }
    }

    public static int fMin(int x){
       
        if(x==1)
            return 0;
        else{
            if(mins[x]!=-1)
                return mins[x];
            else{
                mins[x]= 1+smallest(x%2==0?fMin(x/2):Integer.MAX_VALUE,x%3==0?fMin(x/3):Integer.MAX_VALUE,fMin(x-1));
                return mins[x];
            }
        }
    }
    
    public static int smallest(int a,int b,int c){
        int min=a;
        if(b<min)
            min=b;
        if(c<min)
            min =c;
        return min;
    }
}


