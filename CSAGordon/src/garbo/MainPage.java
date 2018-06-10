package garbo;
/**
 * Represents the scene where the user drops in the Excel file to be read.
 * @author Skylar Chan
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.SheetReader;

public class MainPage extends Scene {
    Label label = new Label("Welcome to the Summer Math Seating Program!\nRight click any time for help.\n\nPlease drop in the Excel file you wish to sort.");
    /**
	 * Represents the button to be clicked to advance to the next scene.
	 */
    Button click = new Button("If this is the correct\nspreadsheet, click here");
    Button select = new Button("Or select it here");
    
    /**
	 * Creates the scene where the user drops in the Excel file to be read.
	 * @param bp The border pane that this scene is to be attached to
	 * @param arr The reference to the running Program object's student list
	 */	

    public MainPage(BorderPane bp) {
        super(bp, 551, 400);
        label.setTextAlignment(TextAlignment.CENTER);
     
        BorderPane myPane = new BorderPane();
        label.setFont(new Font("Times New Roman", 40.0));
        label.setAlignment(Pos.CENTER);
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        myPane.setTop(label);
        click.setFont(new Font("Times New Roman", 20.0));
        click.setTextAlignment(TextAlignment.CENTER);
        click.setVisible(false);
        select.setFont(new Font("Times New Roman", 20.0));
        select.setTextAlignment(TextAlignment.CENTER);

        bp.setTop(myPane);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(16);
        vbox.getChildren().addAll(select, click);
        vbox.setAlignment(Pos.CENTER);
        myPane.setCenter(vbox);
    }





  

    private boolean isExcel(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0 && fileName.substring(fileName.lastIndexOf(".") + 1).equals("xlsx")) {
            return true;
        }
        return false;
    }

    

    private void popUpError(String text) {
        Alert alert = new Alert(AlertType.ERROR, text);
        alert.setHeaderText(null);
        alert.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

}