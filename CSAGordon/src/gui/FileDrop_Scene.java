package gui;
import util.SheetReader;
import javafx.scene.control.*;
/**
 * Represents the scene where the user drops in the Excel file to be read.
 * @author Kasra Torshiz 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import algo.StudentSorter;
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
import studentOrg.Session;
import studentOrg.Student;
import javafx.scene.layout.*;
public class FileDrop_Scene extends Scene {
	Session sess;
	VBox mainCont = new VBox(10);
    Label heading = new Label("Summer Math Seating Program");
    /**
	 * Represents the button to be clicked to advance to the next scene.
	 */
    Button click = new Button("If this is the correct\nspreadsheet, click here");
    Button select = new Button("Upload Excel");
    //ArrayList<Student> students = new ArrayList<Student>();
    TextField fileField = new TextField();
    HBox fileSelect = new HBox(10);
    BorderPane myPane = new BorderPane();
    ToggleGroup group = new ToggleGroup();

    RadioButton rb1 = new RadioButton("Session 1");


    RadioButton rb2 = new RadioButton("Session 2");
    HBox sessionSelect = new HBox(8);
    Button start = new Button("Sort");
    Button help = new Button("Help");
    Button settings = new Button("Settings");
    HBox bottom = new HBox(8);
    File excelFile;
    /**
	 * Creates the scene where the user drops in the Excel file to be read.
	 * @param bp The border pane that this scene is to be attached to
	 * @param arr The reference to the running Program object's student list
	 */	

    public FileDrop_Scene(BorderPane bp, Session s) {
        super(bp, 400, 120);
        heading.setTextAlignment(TextAlignment.CENTER);
        sess =s;
        heading.setFont(new Font("Times New Roman", 20));
        heading.setWrapText(true);
        heading.setAlignment(Pos.CENTER);
        myPane.setTop(heading);
        fileField.setPrefWidth(250);
        click.setFont(new Font("Times New Roman", 20.0));
        click.setTextAlignment(TextAlignment.CENTER);
        click.setOnAction(e ->ButtonClicked(e));
        click.setVisible(false);
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        rb2.setToggleGroup(group);
        /*select.setFont(new Font("Times New Roman", 16));
        select.setTextAlignment(TextAlignment.CENTER);*/
        select.setOnAction(e ->ButtonClicked(e));
        fileSelect.getChildren().addAll(fileField,select);
        sessionSelect.getChildren().addAll(rb1, rb2);
        mainCont.getChildren().addAll(fileSelect,sessionSelect);
        myPane.setCenter(mainCont);
        bp.setTop(myPane);
        start.setFont(new Font("Times New Roman", 16));
        start.setOnAction(e ->ButtonClicked(e));
        help.setFont(new Font("Times New Roman", 16));
        help.setOnAction(e ->ButtonClicked(e));
        settings.setFont(new Font("Times New Roman", 16));
        settings.setOnAction(e ->ButtonClicked(e));
        bottom.getChildren().addAll(start, help, settings);
        bp.setBottom(bottom);
        /*VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(16);
        vbox.getChildren().addAll(select, click);
        vbox.setAlignment(Pos.CENTER);
        myPane.setCenter(vbox);*/
    }

    public void ButtonClicked(ActionEvent e) {
        if (e.getSource() == start) {
        	this.readAndCheck(excelFile);
            Program.switchScene(sess);
        } 
        else if (e.getSource() == help){
        	HelpWindow hwindow = new HelpWindow();
        }
        else if (e.getSource() == settings){
        	SettingsScene s = new SettingsScene(); 
        }
        else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select your Excel file");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Microsoft Excel","*.xlsx"));
            excelFile = fileChooser.showOpenDialog(new Stage());
            String filePath = excelFile.getAbsolutePath();
            if (!isExcel(filePath)) {
                popUpError("The file you dropped in is not an Excel file!");
            } else {
            	fileField.setText(filePath);
            	fileField.positionCaret(filePath.length()-1);
            }
        }
    }

	private void addEvents() {
        setOnDragOver(new EventHandler<DragEvent>(){
        	@Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        setOnDragDropped(new EventHandler<DragEvent>(){
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    File file = db.getFiles().get(0);
                    readAndCheck(file);
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    private void readAndCheck(File file) {
        try {
            String filePath = file.getAbsolutePath();
            if (!isExcel(filePath)) {
                popUpError("The file you dropped in is not an Excel file!");
            } else {
            	fileField.setText(filePath);
            	fileField.positionCaret(filePath.length()-1);
                filePath = fileField.getText();
                int yearIndex = filePath.indexOf("2");
                //System.out.println(yearIndex);
                sess.setYear(filePath.substring(yearIndex, filePath.indexOf(" ",yearIndex)));
                //System.out.println(filePath.substring(yearIndex, filePath.indexOf(" ",yearIndex)));
                SheetReader reader = new SheetReader(filePath);
                reader.next(); //first line is not student
                RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
                String session = selectedRadioButton.getText();
                String sessionNum;
                if (session.equals("Session 1")){
                	sessionNum = "1st";
                	sess.setSessionNum(1);
                }
                else {
                	sessionNum = "2nd";
                	sess.setSessionNum(2);
                }

                while (reader.hasNext()){
                	ArrayList<String> line = reader.next();
                	if (line.get(4).trim().equals(sessionNum)){
	                	String lastName = line.get(1).substring(0,line.get(1).indexOf(","));
	                	String firstName = line.get(1).substring(line.get(1).indexOf(",")+1,line.get(1).indexOf(" ",line.get(1).indexOf(",")+2)).trim();
	                	String gender = line.get(2);
	                	String house = line.get(3);
	                	String school = line.get(5);
	                	sess.addStudent(new Student(firstName,lastName,house,gender,school));
                	}
                }
                for (int i =0;i<sess.getNumStudents();i++){
                	System.out.println(sess.getStudent(i));
                }
                StudentSorter sorter = new StudentSorter(sess);
                sorter.sort();
                for (int i = 0;i<sess.allDays.length;i++){
                	for (int j = 0; j<sess.getNumTables();j++){
                		for (int k = 0; k<sess.getDaySeating(sess.allDays[i]).getTable(j).getLength();k++){
                			System.out.println(sess.getDaySeating(sess.allDays[i]).getTable(j).get(k));
                		}
                		System.out.println();
                	}
                	System.out.println();
                	System.out.println();
                }
            }
        }
        catch (NullPointerException n) {
            popUpError("You didn't select your file!");
        } 
       catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
        	popUpError("Illegal File!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			popUpError("Illegal File!");
		}
        
            
    }

    private boolean isExcel(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0 && fileName.substring(fileName.lastIndexOf(".") + 1).equals("xlsx")) {
            return true;
        }
        return false;
    }

    private int scan(String filePath) {
        SheetReader sr = null;
        try {
            sr = new SheetReader(filePath);
        }
        catch (IOException e1) {
        	popUpError("The file path is not valid");
            //The file is guaranteed to exist since the file path is checked before this 
        }
        int row = 1;
        try {
            sr.next();//ignore first row since it's not a student
        }
        catch (Exception e) {
            return row;
        }
       // students.clear();
        while(sr.hasNext()) {//read the spreadsheet
            ++row;
            ArrayList<String> tmp = sr.next();
            if (!tmp.isEmpty()) 
	            try {
	                //Student s = new Student(tmp);
	                //students.add(s);
	            }
	            catch (Exception e) {
	                return row;//incorrect format
	            }
        }
        return -1;
    }

    private void popUpError(String text) {
        Alert alert = new Alert(AlertType.ERROR, text);
        alert.setHeaderText(null);
        alert.show();
    }

}

