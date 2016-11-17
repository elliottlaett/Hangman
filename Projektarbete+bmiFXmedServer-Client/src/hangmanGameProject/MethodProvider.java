package hangmanGameProject;

import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Interface provide scenes with methods
 * 
 * @author Elliott
 * 
 */

public interface MethodProvider {

	/**
	 * provides a method to classes implementing interface
	 * 
	 * @return text object with value 
	 */
	public abstract Text returnToMenu();

	/**
	 * Method use to set new stage due to nullpointer from origin stage in all
	 * its children classes.
	 * 
	 * @return stage to set scene.
	 */
	public abstract Stage newStage();
}
