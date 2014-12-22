/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myPack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.*;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Akshaya
 */
public class Infix2Postfix {
    StringReader reader;
    StreamTokenizer st;
    Stack output;
    Stack stack;
    
   private static Logger logger;
    
    public Infix2Postfix(String infix)throws IOException{
        reader=new StringReader(infix);
        st=new StreamTokenizer(reader);
        output=new Stack();
         stack=new Stack();
    //    logger=Logger.getLogger(regextonfa.Infix2Postfix.class.getName());
     //   logger.addHandler(new ConsoleHandler());
        
       
       
        
    }
    
    void convert() throws IOException{
      // System.err.println(Precedence.precedenceOf('+'));
        while(st.nextToken()!=st.TT_EOF){
            char token;
            if(st.ttype==StreamTokenizer.TT_WORD){
                String s=st.sval;
                 token=s.charAt(0);
                 
            }
            else 
              token=(char)st.ttype;
            
            System.out.println(token);
            if(token=='('){
                stack.push(token);
                logger.log(Level.INFO, "pushed to temp stack: "+token);                        
            }  
            else if(token==')'){
                while(stack.peek()!='('){
                output.push(stack.pop());
                logger.log(Level.INFO, "pushed to o/p stack: "+token);   
                }
                stack.pop();// pop'('
            }
            else{
                while(stack.length()>0){
                    char peeked=stack.peek();
                    int peeked_precedence=Precedence.precedenceOf(peeked);
                    int current_precedence=Precedence.precedenceOf(token);
                    if(peeked_precedence>=current_precedence){
                        logger.log(Level.INFO, "popping out of temp stack: "+peeked);  
                        output.push(stack.pop());
                        logger.log(Level.INFO, "pushing to output stack: "+peeked);  
                        
                    }
                    
                    else
                        break;
                }
                stack.push(token);
                logger.log(Level.INFO, "pushing to output stack: "+token);  
            }              
               
        }
        while(stack.length()>0){
            output.push(stack.pop());
        }
        output.print();
    }

    
    
    
}

class Stack{
    StringBuffer stack;
    public Stack(){
        stack=new StringBuffer();
    }
    void push(char ch){
        stack.append(ch);
    }
    
    
    char pop(){
        if(stack.length()<=0)
            return '!';        
       char ret= stack.charAt(stack.length()-1);
       stack.deleteCharAt(stack.length()-1);
       return ret;       
    }
    char peek(){
        if(stack.length()<=0)
            return '!';
        return stack.charAt(stack.length()-1);
    }
    int length(){
        return stack.length();
    }
    
    void print(){
        System.out.println(stack.toString());
    }
}

class Precedence{
   static Map hm;
  
    private static Logger logger;
    public Precedence(){
       
    }
    
    public static int precedenceOf(char ch){
         logger=Logger.getLogger(myPack.Precedence.class.getName());
       // logger.addHandler(new ConsoleHandler());
        int prec;
        hm=new HashMap();
         hm.put('(', 1);
        hm.put('|' ,2);
        hm.put(',' ,3);
        hm.put('?' ,4);
        hm.put('*' ,4);
        hm.put('+' ,4);
        hm.put('^' ,5);
        if(hm.containsKey(ch))
         prec= (int)hm.get(ch);
        else{
          
            prec= 6;
        }
            logger.log(Level.INFO, "precedence of "+ch+" is "+prec);  
        return prec;
    }
}