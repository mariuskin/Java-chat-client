package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	private String host;
	private int portnumber;
	
	
	public String getHost() {
		return host;
	}



	public int getPortnumber() {
		return portnumber;
	}




	public Client(String host, int portnumber) {
		super();
		this.host = host;
		this.portnumber = portnumber;
	}



	public void start() throws IOException
	{
		System.out.println("start");
		while (true) {
			Socket socket = new Socket(this.host, this.portnumber);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			System.out.println("server says:" + br.readLine());

			BufferedReader userInputBR = new BufferedReader(new InputStreamReader(System.in));
			String userInput = userInputBR.readLine();

			out.println(userInput);

			System.out.println("server says:" + br.readLine());

			if ("exit".equalsIgnoreCase(userInput)) {
				socket.close();
				break;
			}
		
	}
	}
	

}
