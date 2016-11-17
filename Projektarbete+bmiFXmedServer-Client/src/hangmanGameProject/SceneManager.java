package hangmanGameProject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * Parent Scene class provides child classes with data and methods
 * 
 * @author Elliott
 *
 */
public abstract class SceneManager {

	protected final int SCENE_WIDTH = 1000;
	protected final int SCENE_HEIGTH = 700;
	protected Stage stage;
	private Media[] soundEffects = { new Media(getClass().getResource("/sounds/Cheering.mp3").toString()),
			new Media(getClass().getResource("/sounds/aaaw.mp3").toString()),
			new Media(getClass().getResource("/sounds/applause.mp3").toString()),
			new Media(getClass().getResource("/sounds/boo.mp3").toString()),
			new Media(getClass().getResource("/sounds/ButtonPress.mp3").toString()),
			new Media(getClass().getResource("/sounds/hooverMenu.mp3").toString()),
			new Media(getClass().getResource("/sounds/choose.mp3").toString()) };
	protected Image hangmanPics[] = { new Image("image/hangman_nr1.gif"), new Image("image/hangman_nr2.gif"),
			new Image("image/hangman_nr3.gif"), new Image("image/hangman_nr4.gif"), new Image("image/hangman_nr5.gif"),
			new Image("image/hangman_nr6.gif"), new Image("image/hangman_nr7.gif") };

	/**
	 * empty contruktor used by child classes to call methods.
	 */
	public SceneManager() {
		super();
	}

	/**
	 * takes in a stage and provides children class
	 * 
	 * @param stage
	 *            set scenes
	 */
	public SceneManager(Stage stage) {
		super();
		this.stage = stage;

	}

	protected void playMedia(String text) {
		MediaPlayer mp;

		switch (text) {
		case "cheer":
			if (soundEffects[0] != null) {
				mp = new MediaPlayer(soundEffects[0]);
				mp.getTotalDuration();
				mp.setStopTime(Duration.seconds(2));
				mp.play();
			}
			break;
		case "aaw":
			if (soundEffects[1] != null) {
				mp = new MediaPlayer(soundEffects[1]);
				mp.getTotalDuration();
				mp.setStopTime(Duration.seconds(2));
				mp.play();
			}
			break;
		case "applause":
			if (soundEffects[2] != null) {
				mp = new MediaPlayer(soundEffects[2]);
				mp.getTotalDuration();
				mp.setStopTime(Duration.seconds(2));
				mp.play();
			}
			break;
		case "boo":
			if (soundEffects[3] != null) {
				mp = new MediaPlayer(soundEffects[3]);
				mp.getTotalDuration();
				mp.setStopTime(Duration.seconds(2));
				mp.play();
			}
			break;
		case "buttonPress":
			if (soundEffects[4] != null) {
				mp = new MediaPlayer(soundEffects[4]);
				mp.getTotalDuration();
				mp.setStopTime(Duration.seconds(2));
				mp.play();
			}
			break;
		case "hoover":
			if (soundEffects[5] != null) {
				mp = new MediaPlayer(soundEffects[5]);
				mp.getTotalDuration();
				mp.setStopTime(Duration.seconds(2));
				mp.play();
			}
			break;
		case "choosed":
			if (soundEffects[6] != null) {
				mp = new MediaPlayer(soundEffects[6]);
				mp.getTotalDuration();
				mp.setStopTime(Duration.seconds(2));
				mp.play();
			}
			break;

		default:
			break;
		}
	}

	/**
	 * Takes a button and set effects to it.
	 * 
	 * @param button
	 *            to set effect.
	 */
	protected void effects(Button button) {
		button.setStyle(
				"-fx-background-color: green; -fx-font-size: 14pt; -fx-font-family: Serif; -fx-text-fill: white; "
						+ "-fx-border-color: black; -fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 ) ;");

	}

	/**
	 * Sets color to background in all its child classes when called upon.
	 * 
	 * @param root
	 *            sets color to root.
	 */
	protected void setBackground(BorderPane root) {
		root.setStyle("-fx-background-color: black;");

	}

	/**
	 * Method use to set scenes in all the children classes
	 * 
	 * @return scene to set stage with returned scene
	 */
	public abstract Scene sceneSetter();

}
