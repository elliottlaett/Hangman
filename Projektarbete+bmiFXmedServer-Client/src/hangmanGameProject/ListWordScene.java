package hangmanGameProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class handles read and write to file and adds/remove text from/to list
 * 
 * @author Elliott
 * 
 */
public class ListWordScene extends SceneManager implements MethodProvider {

	private ArrayList<String> al = new ArrayList<>();
	private String pathname = "src/textfile/project.txt";
	private Label submitLabel;
	private ArrayList<TextField> listTF;
	private ArrayList<String> textList = new ArrayList<>();
	private BorderPane rootListWord;
	private Scene returnScene;

	public ListWordScene() {
		super();
	}
	
	public ListWordScene(Stage stage) {
		super(stage);
	}

	/**
	 * Constructor get scene from menu
	 * 
	 * @param returnScene
	 *            takes its menu scene
	 */
	public ListWordScene(Scene returnScene) {
		super();
		this.returnScene = returnScene;
	}

	/**
	 * Method takes in datatypes and sets additional functionality
	 * 
	 * @param submitLabel
	 *            sets value to its text
	 * @param listTF
	 *            adds new textfields
	 * @param textList
	 *            adds words to list
	 */
	public ListWordScene(Label submitLabel, ArrayList<TextField> listTF, ArrayList<String> textList) {
		super();
		this.submitLabel = submitLabel;
		this.listTF = listTF;
		this.textList = textList;

	}

	/**
	 * reads a text file
	 * 
	 * @return String the whole text from the file
	 */
	String open() {

		String textToRead = "";
		String wholeText = "";
		try (BufferedReader br = new BufferedReader(new FileReader(pathname))) {
			while ((textToRead = br.readLine()) != null) {
				textList.add(textToRead.toUpperCase());
				wholeText += textToRead.toUpperCase() + '\n';
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Error with I/O");
			e1.printStackTrace();
		}
		return wholeText;
	}

	/**
	 * writes to text file.
	 */
	void write() {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathname, true))) {

			for (TextField textField : listTF) {

				String text = textField.getText().toUpperCase();

				if (textList.contains(text)) {
					al.remove(text);
				} else
					bw.append(textField.getText() + '\n');
				textList.add(textField.getText().toUpperCase());
			}
			submitLabel.setText("Word(s) succesfully submited!");

		} catch (IOException e1) {
			System.out.println("Error with I/O");
			e1.printStackTrace();
		}
	}

	@Override
	public Scene sceneSetter() {
		rootListWord = new BorderPane();
		Scene sceneListWord = new Scene(rootListWord, SCENE_WIDTH, SCENE_HEIGTH);

		setBackground(rootListWord);
		Text header = new Text("Current words in the list");
		header.setFont(Font.font("Arial Black", 40));
		header.setFill(Color.GREEN);
		header.setStroke(Color.BLACK);

		TextArea textArea = new TextArea(open());
		textArea.setStyle("-fx-background-color: green; -fx-font-size: 14pt; -fx-font-family: Serif; "
				+ "-fx-text-fill: green; " + "-fx-border-color: black; "
				+ "-fx-effect: dropshadow( one-pass-box , black , 50 , 0.0 , 15 , 0 ) ;");
		textArea.setMaxWidth(400);
		textArea.setMaxHeight(500);
		textArea.setEditable(false);

		Button removeButton = new Button("Press to remove all words in list");

		effects(removeButton);

		removeButton.setOnAction(e -> {

			playMedia("buttonPress");

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathname))) {

				bw.write("");

			} catch (Exception e1) {
				System.out.println("Error with I/O");
				e1.printStackTrace();
			}
			textArea.setText("");
			al.removeAll(al);
		});

		VBox vbox = new VBox(30);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(header, textArea, removeButton, returnToMenu());
		rootListWord.setCenter(vbox);

		return sceneListWord;
	}

	@Override
	public Text returnToMenu() {
		Text returnText = new Text("Return to menu");
		returnText.setFont(Font.font("Arial Black", 35));
		returnText.setFill(Color.WHITE);
		returnText.setStroke(Color.GREEN);

		returnText.setOnMouseEntered(e -> {
			playMedia("hoover");
			returnText.setEffect(new Glow(50));
			returnText.setFill(Color.DARKRED);
		});
		returnText.setOnMouseExited(e -> {
			returnText.setEffect(null);
			returnText.setFill(Color.WHITE);
		});

		returnText.setOnMouseClicked(event -> {
			playMedia("choosed");
			newStage().setScene(returnScene);
		});

		return returnText;
	}

	@Override
	public Stage newStage() {
		Stage stage = (Stage) rootListWord.getScene().getWindow();
		return stage;
	}

}
