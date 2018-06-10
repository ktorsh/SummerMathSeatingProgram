package garbo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class SceneController extends Application{
	private static Stage stage;
	private static BorderPane currentPane;
	public void start(Stage s) {
		//long time = System.nanoTime();
		//Parent root = loader.load();
	    stage = s;
	    stage.setTitle("Summer Math Seating Program");
	    stage.setScene(new MainPage(currentPane));
		stage.show();
		//long time = System.nanoTime();
		//time = System.nanoTime() - time;
		//System.out.println(time);
	}
	/*public static void main(String[] args) {
        Application.launch(args);
    }*/

}
