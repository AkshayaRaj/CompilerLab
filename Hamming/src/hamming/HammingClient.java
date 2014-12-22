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
public class HammingClient {
	public static void main(String args[])throws IOException{
		Socket receiver = new Socket("localhost", 8080);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader socket_read = new BufferedReader(new InputStreamReader(receiver.getInputStream()));
		PrintWriter socket_write = new PrintWriter(receiver.getOutputStream(), true);
		System.out.println(socket_read.readLine());
		while(true){
			//System.out.println(socket_read.readLine());
			System.out.println("Enter 0 for Even Parity else 1 for Odd parity:");
			int parity;parity = Integer.parseInt(br.readLine());
			socket_write.print(parity);
			System.out.println("Enter the data:");
			String input;input = br.readLine();
			System.out.println("Enter the error position:");
			int error;error = (Integer.parseInt(br.readLine())-1);
			char[] bits = new char[input.length()];
			bits = input.toCharArray();
			char[] code = new char[11];
			code[2] = bits[0];
			code[4] = bits[1];
			code[5] = bits[2];
			code[6] = bits[3];
			code[8] = bits[4];
			code[9] = bits[5];
			code[10] = bits[6];
			char r1, r2, r4, r8;
			int[] r1check = {3,5,7,9,11};
			int[] r2check = {3,6,7,10,11};
			int[] r4check = {5,6,7};
			int[] r8check = {9,10,11};
			r1 = redundancy(code, r1check, parity);
			r2 = redundancy(code, r2check, parity);
			r4 = redundancy(code, r4check, parity);
			r8 = redundancy(code, r8check, parity);
			code[0] = r1;code[1] = r2;code[3] = r4;code[7] = r8;
			
			//socket_write.println(code.toString());
			System.out.println("Hamming Code without error:");
			System.out.println(code);
			if(code[error]=='0'){
				code[error]='1';
			}
			else
				code[error]='0';
			System.out.println("Hamming Code with error:");
			System.out.println(code);
			socket_write.println(code);
			String output = socket_read.readLine();
			System.out.println("Error occured on position: "+output);
			
		}
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

