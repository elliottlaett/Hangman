package hangmanGameProject;

import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * In this class you can add words to you list.
 * 
 * @author Elliott
 *
 */
public class AddWordScene extends SceneManager implements MethodProvider {

	private BorderPane rootAddWord;
	private Scene returnScene;

	public AddWordScene(Stage stage) {
		super(stage);
	}

	/**
	 * Constructor get scene from menu
	 * 
	 * @param returnScene
	 *            takes its menu scene
	 */
	public AddWordScene(Scene returnScene) {
		super();
		this.returnScene = returnScene;
	}

	@Override
	public Scene sceneSetter() {

		rootAddWord = new BorderPane();
		Scene sceneAddWord = new Scene(rootAddWord, SCENE_WIDTH - 300, SCENE_HEIGTH - 200);

		setBackground(rootAddWord);
		ArrayList<TextField> listTextField = new ArrayList<>();
		ArrayList<Label> listLabel = new ArrayList<>();

		Label numLabel = new Label("Enter NUMBER of new words:");
		effects(numLabel);
		TextField textfield = new TextField();
		textfield.setMaxWidth(50);
		effects(textfield);

		Button newWordsButton = new Button("Press to submit");
		effects(newWordsButton);
		VBox returnBox = new VBox();
		returnBox.setAlignment(Pos.CENTER);

		returnBox.getChildren().addAll(returnToMenu());

		rootAddWord.setPadding(new Insets(20, 0, 0, 0));
		rootAddWord.setMaxHeight(200);

		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		rootAddWord.setTop(hBox);
		rootAddWord.setBottom(returnBox);
		hBox.getChildren().addAll(numLabel, textfield, newWordsButton);

		HBox hbox2 = new HBox(20);
		VBox vBox = new VBox(10);
		VBox vBox2 = new VBox(20);
		
		ArrayList<String> textList = new ArrayList<>();
		
		for (String word : new ListWordScene().open().toUpperCase().replaceAll("\n", " ").split(" ")) {
			textList.add(word);
		}

		charIgnorer(textfield, 1);

		newWordsButton.disableProperty().bind(Bindings.isEmpty(textfield.textProperty()));

		newWordsButton.setOnAction(e -> {

			playMedia("buttonPress");

			textfield.setDisable(true);

			String text = textfield.getText();

			int value = Integer.parseInt(text);

			textfield.setText("");

			for (int i = 0; i < value; i++) {
				listTextField.add(new TextField());
				listLabel.add(new Label());

			}

			Button submitButton = new Button("Submit word(s)");

			for (TextField textField : listTextField) {

				effects(textField);
				charIgnorer(textField, 2);
				textField.setPromptText("ex boomerang");
				textField.setMaxWidth(150);
				submitButton.disableProperty().bind(Bindings.isEmpty(textField.textProperty()));

			}

			for (Label label : listLabel) {
				effects(label);
				label.setText("Enter new word:");
			}

			effects(submitButton);

			vBox.getChildren().addAll(listTextField);
			vBox2.getChildren().addAll(listLabel);

			Label submitLabel = new Label();
			effects(submitLabel);

			vBox.getChildren().add(hbox2);
			hbox2.getChildren().addAll(submitButton, submitLabel);
			hbox2.setTranslateX(-40);
			
			submitButton.setOnAction(event -> {
				
				ListWordScene listed = new ListWordScene(submitLabel, listTextField, textList);
				playMedia("buttonPress");
				listed.write();
				
				for (TextField textField : listTextField) {
					
					textField.setText("");
					textField.setDisable(true);
					submitButton.disableProperty().bind(Bindings.isEmpty(textField.textProperty()));
				}
			});
		});

		vBox.setAlignment(Pos.CENTER);
		vBox2.setPadding(new Insets(0, -110, 50, 200));
		vBox2.setAlignment(Pos.CENTER);

		rootAddWord.setLeft(vBox2);
		rootAddWord.setCenter(vBox);
		return sceneAddWord;

	}

	private void charIgnorer(TextField textfield, int kind) {

		textfield.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (kind == 1)
					textfield.setText(newValue.replaceAll("[[^\\d]0]", ""));
				else
					textfield.setText(newValue.replaceAll("[^a-z]", ""));
			}
		});
	}

	@Override
	public Text returnToMenu() {
		Text returnText = new Text("Return to menu");
		returnText.setFont(Font.font("Arial Black", 40));
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

	// Overloaded method
	private void effects(TextField textField) {
		textField.setStyle("-fx-font-size: 14pt; -fx-font-family: Serif; " + "-fx-text-fill: green; "
				+ "-fx-border-color: green; " + "-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 ) ;");
	}

	// Overloaded method
	private void effects(Label label) {
		label.setStyle(
				"-fx-font-size: 15pt; -fx-font-family: Serif; " + "-fx-text-fill: green; -fx-font-weight: bold;");
	}

	@Override
	public Stage newStage() {
		Stage stage = (Stage) rootAddWord.getScene().getWindow();
		return stage;
	}

}
