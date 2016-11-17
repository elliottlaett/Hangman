package hangmanGameProject;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the game class which handles all functionality within the game
 * 
 * @author Elliott
 *
 */
public class GameScene extends SceneManager implements MethodProvider {

	private ImageView hangmanGraphics = new ImageView();
	private ArrayList<String> newLetterList = new ArrayList<>();
	private boolean addOneTime = true;
	private int letterCounter = 0;
	private int counter = 5;
	private BorderPane rootGame;
	private Scene returnScene;

	public GameScene(Stage stage) {

		super(stage);
	}
	/**
	 * Constructor get scene from menu
	 * 
	 * @param returnScene
	 *            takes its menu scene
	 */
	public GameScene(Scene returnScene) {
		super();
		this.returnScene = returnScene;
	}

	@Override
	public Scene sceneSetter() {

		rootGame = new BorderPane();
		Scene sceneGame = new Scene(rootGame, SCENE_WIDTH, SCENE_HEIGTH);
		setBackground(rootGame);

		ArrayList<String> textList = new ArrayList<>();
		for (String word : new ListWordScene().open().replaceAll("\n", " ").split(" ")) {
			textList.add(word.toUpperCase());
		}

		VBox gameBox = new VBox(-30);
		HBox letterBox = new HBox(67);
		VBox vbox = new VBox();

		Random random = new Random();
		int randomNum = random.nextInt(textList.size());

		String secretWord = textList.get(randomNum);

		char[] guessedWord = new char[secretWord.length()];

		ArrayList<Label> labelList = new ArrayList<>();

		for (int i = 0; i < guessedWord.length; i++) {

			guessedWord[i] = secretWord.charAt(i);
			labelList.add(new Label());

		}

		for (Label label : labelList) {

			label.setFont(Font.font("Arial Black", FontWeight.EXTRA_BOLD, 50));
			label.setTextFill(Color.WHITE);
			label.setUnderline(true);
			label.setText("  ");
		}

		letterBox.setAlignment(Pos.CENTER);
		letterBox.getChildren().addAll(labelList);

		gameBox.setAlignment(Pos.CENTER);
		gameBox.getChildren().addAll(letterBox);
		rootGame.setCenter(gameBox);

		lettersOutput(guessedWord, labelList, vbox);

		Text header = new Text("Don't let the hangman, hang the man!");
		header.setFont(Font.font("Arial Black", 40));
		header.setFill(Color.GREEN);
		header.setStroke(Color.BLACK);

		ImageView imageview = hangmanGraphics;
		imageview.setImage(hangmanPics[counter]);

		VBox headBox = new VBox(30);
		headBox.setAlignment(Pos.CENTER);
		headBox.getChildren().addAll(header, imageview);

		rootGame.setTop(headBox);

		vbox.getChildren().addAll(returnToMenu());

		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(0, 0, 2, 0));
		rootGame.setBottom(vbox);

		return sceneGame;
	}
	
	private void lettersOutput(char[] guessedWord, ArrayList<Label> textList, VBox vbox) {

		ArrayList<Button> topButtons = new ArrayList<>();
		ArrayList<Button> middleButtons = new ArrayList<>();
		ArrayList<Button> bottomButtons = new ArrayList<>();

		HBox topBox = new HBox(2);
		HBox middleBox = new HBox(2);
		HBox bottomBox = new HBox(2);

		for (int i = 65; i <= 73; i++) {

			topButtons.add(new Button(String.valueOf((char) i)));

		}
		topBox.getChildren().addAll(topButtons);

		for (int i = 74; i <= 80; i++) {

			middleButtons.add(new Button(String.valueOf((char) i)));
		}

		middleBox.getChildren().addAll(middleButtons);

		for (int i = 81; i <= 90; i++) {

			bottomButtons.add(new Button(String.valueOf((char) i)));
		}

		bottomBox.getChildren().addAll(bottomButtons);

		buttonFunctions(guessedWord, textList, topButtons, middleButtons, bottomButtons, vbox);

		topBox.setAlignment(Pos.CENTER);
		middleBox.setAlignment(Pos.CENTER);
		bottomBox.setAlignment(Pos.CENTER);

		vbox.getChildren().addAll(topBox, middleBox, bottomBox);
	}

	private void buttonFunctions(char[] guessedWord, ArrayList<Label> textList, ArrayList<Button> topButtons,
			ArrayList<Button> middleButtons, ArrayList<Button> bottomButtons, VBox vbox) {

		for (Button button : topButtons) {
			effects(button);
			button.setOnAction(e -> {
				playMedia("buttonPress");
				switch (button.getText()) {
				case "A":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "B":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "C":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "D":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "E":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "F":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "G":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "H":
					showLetter(textList, button, guessedWord, vbox);
					break;

				default:
					showLetter(textList, button, guessedWord, vbox);
					break;
				}
			});
		}

		for (Button button : middleButtons) {
			effects(button);
			button.setOnAction(e -> {
				playMedia("buttonPress");
				switch (button.getText()) {
				case "J":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "K":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "L":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "M":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "N":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "O":
					showLetter(textList, button, guessedWord, vbox);
					break;
				default:
					showLetter(textList, button, guessedWord, vbox);
					break;
				}
			});
		}

		for (Button button : bottomButtons) {
			effects(button);
			button.setOnAction(e -> {
				playMedia("buttonPress");
				switch (button.getText()) {
				case "Q":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "R":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "S":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "T":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "U":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "V":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "W":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "X":
					showLetter(textList, button, guessedWord, vbox);
					break;
				case "Y":
					showLetter(textList, button, guessedWord, vbox);
					break;

				default:
					showLetter(textList, button, guessedWord, vbox);
					break;
				}
			});
		}
	}

	private void showLetter(ArrayList<Label> textList, Button button, char[] guessedWord, VBox vbox) {

		StringBuilder builder = new StringBuilder();
		boolean once = true;

		if (addOneTime == true) {
			addOneTime = false;
			for (int i = 0; i < guessedWord.length; i++) {
				newLetterList.add(null);
			}
		}
		for (int i = 0; i < guessedWord.length; i++) {

			if (guessedWord[i] == button.getText().charAt(0)) {
				once = false;

				playMedia("applause");

				button.setDisable(true);

				Label getLetter = textList.get(i);

				getLetter.setUnderline(true);

				getLetter.setText(String.valueOf(guessedWord[i]));

				newLetterList.set(i, String.valueOf(button.getText()));

				++letterCounter;
			}
			button.setDisable(true);
		}

		if (letterCounter == guessedWord.length) {
			for (String string : newLetterList) {
				builder.append(string);
			}
			if (builder.toString().equals(new String(guessedWord))) {
				hangmanGraphics.setImage(hangmanPics[6]);
				playMedia("cheer");

				Text text = new Text("Congratz! You've saved the hangman from getting hanged!");
				textEffects(text);
				vbox.getChildren().add(0, text);
				setDisabled(vbox);
			}
		}

		if (once) {
			playMedia("boo");
			hangmanGraphics.setImage(hangmanPics[--counter]);
			once = false;
		}
		if (counter == 0) {
			playMedia("aaw");
			String correctWord = new String(guessedWord);
			Text text = new Text("Aaaaw! Correct word was: " + correctWord);
			textEffects(text);
			vbox.getChildren().add(0, text);
			setDisabled(vbox);
		}
	}

	@Override
	public Text returnToMenu() {
		Text returnText = new Text("Return to menu");
		returnText.setFont(Font.font("Arial Black", 30));
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
			counter = 5;
			newStage().setScene(returnScene);
			letterCounter = 0;
			newLetterList.clear();
			addOneTime = true;

		});
		return returnText;
	}

	private Text textEffects(Text text) {
		text.setFont(Font.font("Arial Black", 30));
		text.setFill(Color.WHITE);
		text.setStroke(Color.GREEN);
		return text;
	}

	private void setDisabled(VBox vbox) {
		for (int i = 1; i < vbox.getChildren().size() - 1; i++) {
			vbox.getChildren().get(i).setDisable(true);
		}
	}

	@Override
	public Stage newStage() {
		Stage stage = (Stage) rootGame.getScene().getWindow();
		return stage;
	}

}
