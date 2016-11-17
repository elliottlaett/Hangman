package hangmanGameProject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuScene extends SceneManager {

	private int y = 0;
	private BorderPane rootMenu;
	
	public MenuScene(Stage stage) {
		super(stage);
	}

	@Override
	public Scene sceneSetter() {

		rootMenu = new BorderPane();
		Scene scene = new Scene(rootMenu, SCENE_WIDTH, SCENE_HEIGTH);
		setBackground(rootMenu);

		VBox menuBox = new VBox(50);
		Text headerText = new Text("Welcome to the hangman game!");
		Text addWordsText = new Text("Add words");
		Text listWordsText = new Text("Listed words");
		Text startText = new Text("New game");
		Text exitText = new Text("Exit");

		headerText.setFont(Font.font("Arial Black", 40));
		headerText.setFill(Color.GREEN);
		headerText.setStroke(Color.BLACK);

		menuBox.getChildren().addAll(headerText, addWordsText, listWordsText, startText, exitText);
		menuBox.setAlignment(Pos.CENTER);

		hoverOver(addWordsText);
		hoverOver(listWordsText);
		hoverOver(startText);
		hoverOver(exitText);

		menuChoise(addWordsText);
		menuChoise(listWordsText);
		menuChoise(startText);
		menuChoise(exitText);

		rootMenu.setCenter(menuBox);
		return scene;

	}
	
	/*
	     Here is aggregation, all classes needs to be accesed from
		 the menu class in order to set the new scenes but the menu
		 class doesn't need to be created in other classes, just send
		 its layout (sceneSetter()) to the other classes in order for them
		 to "return to menu", the menu scene.
	  
	 */
	private void menuChoise(Text menuText) {

		menuText.setOnMouseClicked(e -> {
			ListWordScene listWord = new ListWordScene(sceneSetter());

			switch (menuText.getText()) {

			case "Add words":
				hoverOver(menuText);
				stage.setScene(new AddWordScene(sceneSetter()).sceneSetter());
				playMedia("choosed");
				break;
				
			case "Listed words":
				stage.setScene(listWord.sceneSetter());
				hoverOver(menuText);
				playMedia("choosed");
				break;
				
			case "New game":
				hoverOver(menuText);
				if (listWord.open().equals("")) {
					playMedia("boo");
					emptyListWindow();
				} else {
					playMedia("choosed");
					stage.setScene(new GameScene(sceneSetter()).sceneSetter());			
				}
				break;
				
			case "Exit":
				hoverOver(menuText);
				playMedia("choosed");
				System.exit(0);
				break;
			default:
				break;
			}
		});
	}

	private void emptyListWindow() {
		Stage stage2 = new Stage();
		BorderPane bpane = new BorderPane();
		Scene scene2 = new Scene(bpane, 250, 150);
		shakeStage(stage2);
		stage2.setScene(scene2);
		stage2.show();
		Label label = new Label("List is empty, please add words");
		bpane.setCenter(label);
	}

	private void shakeStage(Stage stage) {

		Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {

				if (y == 0) {
					stage.setY(stage.getY() + 10);
					y = 1;
				} else {
					stage.setY(stage.getY() - 10);
					y = 0;
				}
			}
		}));

		timelineY.setCycleCount(10);
		timelineY.setAutoReverse(false);
		timelineY.play();
	}

	private void hoverOver(Text text) {

		text.setFont(Font.font("Arial Black", 40));
		text.setFill(Color.WHITE);
		text.setStroke(Color.GREEN);

		text.setOnMouseEntered(e -> {
			playMedia("hoover");
			text.setEffect(new Glow(50));
			text.setFill(Color.DARKRED);

		});
		text.setOnMouseExited(e -> {
			text.setEffect(null);
			text.setFill(Color.WHITE);
		});

	}

}
