package bmiServerFX;

// Is used to an additional task in school

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Client extends Application {

	DataInputStream fromServer = null;
	DataOutputStream toServer = null;
	private Socket socket;

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		VBox vbox = new VBox(10);
		Text headerText = new Text("Bmi calylator");
		headerText.setFont(Font.font("Arial", FontWeight.BOLD, 35));

		Label label = new Label("Enter your weight in kilo (KG)");
		Label label2 = new Label("Enter your height in metres (M)");

		TextArea ta = new TextArea();
		ta.setMaxWidth(430);
		TextField weightTf = new TextField();
		TextField heightTf = new TextField();
		weightTf.setMaxWidth(150);
		heightTf.setMaxWidth(150);
		heightTf.setPromptText("ex: 1.8");

		Button button = new Button("Press to submit");

		vbox.setAlignment(Pos.CENTER);
		root.setCenter(vbox);
		vbox.getChildren().addAll(headerText, label, weightTf, label2, heightTf, ta, button);

		Scene scene = new Scene(root, 450, 350);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();

		try {
			socket = new Socket("localHost", 8000);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		}

		catch (IOException ex) {

		}

		button.setOnAction(e -> {
			try {

				double weight = Double.parseDouble(weightTf.getText());
				double height = Double.parseDouble(heightTf.getText());

				toServer.writeDouble(weight);
				toServer.flush();

				toServer.writeDouble(height);
				toServer.flush();

				String bmi = fromServer.readUTF();
				ta.appendText(bmi + '\n');
				
			}

			catch (IOException ex) {
			}
		});
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
