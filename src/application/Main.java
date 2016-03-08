package application;
	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Pane pane = (Pane) FXMLLoader.load(Main.class.getResource("file2.fxml"));
			
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            @Override
	            public void handle(WindowEvent t) {
	            	
	            	System.out.println("fin");
	            	
	            	Platform.exit();
	                System.exit(0);
	            }
	        });
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//launch(args);
//		InetAddress
//		localhostInetAddress;
		/*for the server class, we use localhost as InetAddress because
		 * the host is my machine
		 * We use the port 0 because when we will create a socket
		 * it will creates a socket on any free port
		 * */
//		localhostInetAddress = InetAddress.getByName("localhost");
//		Chatserver chatserver = new Chatserver(localhostInetAddress,15060);
//		chatserver.start();
		Application.launch(args);
		
	}
}