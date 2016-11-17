package bmiServerFX;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {

	@Override
	public void start(Stage primaryStage) {

		TextArea ta = new TextArea();
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Server");
		primaryStage.setScene(scene);
		primaryStage.show();

		Thread thread = new Thread(() -> connectToThread(ta));
		thread.start();
	}

	private void connectToThread(TextArea ta) {

		try (ServerSocket serverSocket = new ServerSocket(8000)){
			

			Platform.runLater(() -> ta.appendText("Server started at: " + new Date() + '\n'));
			Socket socket = serverSocket.accept();

			DataInputStream clientWeigth = new DataInputStream(socket.getInputStream());
			DataInputStream clientHeigth = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

			while (true) {

				double weight = clientWeigth.readDouble();
				double height = clientHeigth.readDouble();
				double bmi = ((weight) / (Math.pow(height, 2)));

				bmiOutput(bmi, outputToClient);

				Platform.runLater(() -> {
					ta.appendText("weigth recieved from client: " + weight + '\n');
					ta.appendText("heigth recieved from client: " + height + '\n');
					ta.appendText("Client bmi is: " + bmi+'\n');
				});

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void bmiOutput(double bmi, DataOutputStream outputToClient) {
		try {
			if (bmi < 18.5) {

				outputToClient.writeUTF("Your bmi is ≈: " +(int)bmi + " and you're under weight");

			} else if (bmi > 18.5 && bmi < 25) {
				outputToClient.writeUTF("Your bmi is ≈ " + (int)bmi + " and you're normal weight");
			} else if (bmi > 25 && bmi < 30) {
				outputToClient.writeUTF("Your bmi is ≈ " +(int) bmi + " and you're over weight");
			} else {
				outputToClient.writeUTF("Your bmi is ≈ " + (int)bmi + " and you're Obese");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
