package es.sidelab.SaleWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
		
	public static void main(String[] args) {
		String host = "127.0.0.1";
		int puerto = 5555;
		
		try {
			
			Socket socket = new Socket(host, puerto);
			BufferedReader teclado /*brStdIn*/ = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader leerServer/*brSocketIn*/= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter escribirServer/*pwSocketOut*/= new PrintWriter(socket.getOutputStream(),true);
			
			String linea;	
			while (!(linea = teclado.readLine()).equals("x")){
				
				escribirServer.println(linea);
				String respuesta = leerServer.readLine();
				System.out.println(respuesta);
			}
			
			leerServer.close();
			escribirServer.close();
			socket.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}