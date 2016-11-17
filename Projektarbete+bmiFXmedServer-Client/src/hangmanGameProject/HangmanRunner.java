package hangmanGameProject;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main class which sends object to all the classes
 * 
 * @author Elliott
 * 
 */
public class HangmanRunner extends Application {
	private Stage stage;

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		/*
		  Polymorphism is used here and by the looks of the code you can see
		  that you are sending in the same data in different classes but they
		  are handled in different ways regarding to which class that revieves
		  the data. This also stands for aggregation.
		 */
		SceneManager manager;
		manager = new ListWordScene(stage);
		manager = new GameScene(stage);
		manager = new AddWordScene(stage);
		manager = new MenuScene(stage);

		stage.setScene(manager.sceneSetter());
		stage.getIcons().add(((manager).hangmanPics[0]));
		stage.setTitle("The Hangman Game");
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
