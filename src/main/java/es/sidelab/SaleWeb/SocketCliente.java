package es.sidelab.SaleWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketCliente {
	private String host;
	private int puerto;
	
	public SocketCliente(){
		this.host = "127.0.0.1";
		this.puerto = 5555;
	}
	
	public void enviarEmail(String linea){
		try {
		
			Socket socket = new Socket(this.host, this.puerto);
			PrintWriter escribirServer = new PrintWriter(socket.getOutputStream(),true);
		
			
			escribirServer.println(linea);
		
			escribirServer.close();
			socket.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
