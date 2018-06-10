package gui;
/**
 * This is the Summer Math Seating program for our client Mr. Gordon. It sorts freshman going to the summer magnet math session based a number of client-requested criteria to promote inclusivity.
 * @author CTRLS-Team
 */
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import studentOrg.Session;
import studentOrg.Student;

public class Program extends Application {
    
    private static BorderPane currentPane;
    private static Scene currentScene;
    private static Stage stage;
    private static String filePath;
    private static int sceneNum = 0;
    Session sess = new Session();
	
	public void start(Stage s) {
		//long time = System.nanoTime();
	    stage = s;
	    stage.setTitle("Summer Math Seating Program");
		stage.getIcons().add(new Image("sun.png"));//Image must be in the source directory and not the package
		switchScene(sess);
		stage.show();
		//long time = System.nanoTime();
		//time = System.nanoTime() - time;
		//System.out.println(time);
	}
	
	/**
	 * Displays the next scene of the Program window. Also passes the reference to the list of students that is to be sorted.
	 * @param arr The reference of the list of students to be sorted
	 */
	public static void switchScene(Session s) {
		//long time = System.nanoTime();
		sceneNum++;
		currentPane = new BorderPane();
		switch(sceneNum) {
		case(1):
			currentScene = new FileDrop_Scene(currentPane, s);
			break;
		case(2):
			currentScene = new SortedStudent_Scene(currentPane, s);
			break;
		}
		
		stage.setScene(currentScene);
		//time = System.nanoTime() - time;
		//System.out.println(time);
	}
	
	public static void main(String[] args) {
        Application.launch(args);
    }
	
	public static void setFilePath(String fp) {
		filePath = fp;
	}
	public static String getFilePath() {
		return filePath;
	}

	public static Window getPrimaryStage() {
		// TODO Auto-generated method stub
		return stage;
	}
	
}