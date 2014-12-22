/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parity;

import java.io.*;
import java.net.*;
public class receiver {
	public static void main(String args[])throws IOException{
		Socket receiver = new Socket("localhost", 8080);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader socket_read = new BufferedReader(new InputStreamReader(receiver.getInputStream()));
		PrintWriter socket_write = new PrintWriter(receiver.getOutputStream(), true);
		System.out.println(socket_read.readLine());
	while(true){
			System.out.println("Enter 1 for Even Parity else 0 for Odd parity:");
			int parity;parity = Integer.parseInt(br.readLine());
			socket_write.print(parity);
			System.out.println("Enter the message:");
			String input;input = br.readLine();
			socket_write.println(input);
			String output = socket_read.readLine();
			System.out.println("Data Received without Error!!");
			System.out.println("Parity checked output:\n"+output);
		}
	}
}
