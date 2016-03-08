package client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import application.Controller;

public class Client {
	
	private String host;
	private int portnumber;
	private Controller controller;
	private Socket socket;
	
	public String getHost() {
		return host;
	}



	public int getPortnumber() {
		return portnumber;
	}



	/**
	 * @return the client
	 */
	public Controller getClient() {
		return controller;
	}



	/**
	 * @param client the client to set
	 */
	public void setClient(Controller controller) {
		this.controller = controller;
	}
	


	public Client(String host, int portnumber, Controller controller) throws UnknownHostException, IOException {
		super();
		this.host = host;
		this.portnumber = portnumber;
		this.controller = controller;
		this.socket = new Socket(host, portnumber);
		
	}



	public void start() throws IOException
	{
		System.out.println("start");
		
		Thread thread = new Thread(new Runnable(){

		    @Override
		    public void run() {
		    	
		    	while (true) {
					
					BufferedReader br;
					try {
						br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					
					String temp1 = br.readLine();
					if(!temp1.equals("")){
						
					String temp3 = br.readLine();	
					
					String temp2 = br.readLine();
					controller.writeSomething2(temp2.toString());
					
					}else{
					
						controller.writeSomething2(br.readLine().toString());
						
					}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
			
			}   
		    }
		    
		});

		thread.start();
		
		
		
			
			
		}
	



public void send_message(String text) throws IOException{

	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	InputStream stream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
	
	BufferedReader userInputBR = new BufferedReader(new InputStreamReader(stream));
	
	String userInput = userInputBR.readLine();
	
	String userInput1 = userInputBR.readLine();
	out.println(userInput1);

	if ("exit".equalsIgnoreCase(userInput)) {
		socket.close();
	}
	
	
}



}
