package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import client.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

	@FXML
	private TextField TextAdd;

	@FXML
	private ListView<?> listview;

	@FXML
	private Button ButtonSend;

	@FXML
	private TextArea TextContent;

	private Client client;

	@FXML
	protected void initialize() throws IOException {

		// The previous display
		String arg0 = "";		
		
		// The name of the file to open.
				String fileName = "temp.txt";

				// This will reference one line at a time
				String line = null;

				try {
					// FileReader reads text files in the default encoding.
					FileReader fileReader = new FileReader(fileName);

					// Always wrap FileReader in BufferedReader.
					BufferedReader bufferedReader = new BufferedReader(fileReader);

					while ((line = bufferedReader.readLine()) != null) {
						arg0 = arg0.concat(line + "\n");
					}
					
					System.out.println(arg0);
					
					// Always close files.
					bufferedReader.close();
				} catch (FileNotFoundException ex) {
					System.out.println("Unable to open file '" + fileName + "'");
				} catch (IOException ex) {
					System.out.println("Error reading file '" + fileName + "'");
					// Or we could just do this:
					// ex.printStackTrace();
				}

		Platform.setImplicitExit(false);
		Platform.setImplicitExit(false);
		TextContent.appendText(arg0);
		InetAddress localhostInetAddress;
		/*
		 * for the server class, we use localhost as InetAddress because the
		 * host is my machine We use the port 0 because when we will create a
		 * socket it will creates a socket on any free port
		 */
		//localhostInetAddress = InetAddress.getByName("localhost");
		//this.chatserver = new Chatserver(localhostInetAddress, 15080, this);
		//this.chatserver.start();
		
		this.client = new Client("localhost",15080, this);
		
		this.client.start();
		
	}

	@FXML
	void OnClick(ActionEvent event) throws IOException {

		String arg0 = "\nYou: ";
		String gotText = TextAdd.getText();
		String text = arg0 + gotText;
		if (!text.equals("")) {
			
			this.writeSomething(text);
			this.saveContent(TextContent.getText() + "\nServer: " + text);

			client.send_message("\n" + gotText);
			
			System.out.println("Sent");
		} else {
			System.out.println("not sent");
		}

		
		
	}

	/* Receive a string in parameter and put this string in the TextContent */
	public void writeSomething(String t) {
		TextContent.appendText(t + "\n");
		TextAdd.clear();
	}
	
	public void writeSomething2(String t) {
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
		TextContent.appendText(t + "\n");
		TextAdd.clear();
		
		  //javaFX operations should go here
	        }
	   });
	}
	
	public void sleep(int i) {
		try {
		    Thread.sleep(i);;                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
	}

	public void saveContent(String text) throws FileNotFoundException {
		try(  PrintWriter out = new PrintWriter( "temp.txt" )  ){
		    out.println( text );
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("problem");
		}
	}
	
}
