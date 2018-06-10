package gui;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import studentOrg.*;
import util.Weekday;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SortedStudent_Scene extends Scene {
    //private ArrayList<Student> students;
    //private ArrayList<ArrayList<Student>> output;
   // private Button resort;
    private Button save;
    private Button Mon;
    private Button Tue;
    private Button Wed;
    private Button Thu;
    private Button Fri;
    private String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri"};
    //private StudentSorter sorter;
    private BorderPane display = new BorderPane();
    private StudentTable table;
    private Weekday day = Weekday.MONDAY;
    private String session;
    private Session sess;

    public SortedStudent_Scene(BorderPane bp, Session s) {
        super(bp, 1000.0, 600.0);
       // session = arr.get(0).getSession();
       // students = arr;
        sess = s;
        //resort = new Button("Sort");
        save = new Button("Save");
        Mon = new Button(days[0]);
        Tue = new Button(days[1]);
        Wed = new Button(days[2]);
        Thu = new Button(days[3]);
        Fri = new Button(days[4]);
        Button[] list = {save, Mon,Tue,Wed,Thu,Fri};
		for(Button b:list) {
			b.setFont(new Font("Times New Roman",24));
			b.setOnAction(e->ButtonClicked(e));
		}
        Tooltip tooltip = new Tooltip("Click to save the results to seperate text files");
        //this.resort.setTooltip(tooltip);
        save.setTooltip(tooltip);
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10.0));
        hbox.setSpacing(8.0);
        hbox.getChildren().addAll(save);
        hbox.setAlignment(Pos.CENTER);
        bp.setBottom(hbox);
        HBox hungrybox = new HBox();//Jiggs!
		hungrybox.setPadding(new Insets(10));
		hungrybox.setSpacing(8);
		hungrybox.getChildren().addAll(Mon,Tue,Wed,Thu,Fri);
		hungrybox.setAlignment(Pos.CENTER);
		display.setTop(hungrybox);
        table = new StudentTable();
        display.setCenter(table);
        bp.setCenter(display);
        int clemente = 0;
        int femaleNum =0 ;
        int femaleClemente = 0;
		/*for(Student s:students) {
			boolean both = true;
			if(s.getSchool().equals("Roberto W. Clemente")) {
				clemente++;
			} else {
				both = false;
			}
			if(s.getGender().equals("F")) {
				femaleNum++;
			} else {
				both = false;
			}
			if(both) {
				femaleClemente++;
			}
		}
		int clementeLimit = (int)(Math.random()*100000);
		int groupNum = (int)(Math.random()*100000);
		System.out.println("Details:\nSession: "+session+"\nNumber of students: "+students.size()+"\nNumber of groups: "+groupNum+"\nMax Clemente per group: "+clementeLimit);
		System.out.println("Number of females: "+femaleNum+"\nNumber of Clementes: "+clemente+"\nNumber of female Clementes: "+femaleClemente);
		sorter = new StudentSorter(students);
        output = sorter.sort();*/
		switchView("Mon");
		/*Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Students Sorted");
		alert.setHeaderText("The file has been sorted.");
		//alert.setContentText("Details:\nSession: "+session+"\nNumber of students: "+students.size()+"\nNumber of groups: "+groupNum+"\nMax Clemente per group: "+clementeLimit);
		alert.show();*/
    }

    public void ButtonClicked(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == save) {
            save();
        } else {
        	switchView(((Button)obj).getText());//which day is pressed?
        }
    }


    private void switchView(String button) {
    	//updates the day that is currently being viewed
		switch(button) {
		case "Mon":
			day=Weekday.MONDAY;
			break;
		case "Tue":
			day=Weekday.TUESDAY;
			break;
		case "Wed":
			day=Weekday.WENDESDAY;
			break;
		case "Thu":
			day=Weekday.THURSDAY;
			break;
		case "Fri":
			day=Weekday.FRIDAY;
			break;
		}
		//System.out.println(day);
		table.update(sess.getDaySeating(day));
		Button[] list = {Mon, Tue, Wed, Thu, Fri};
		for(int i=0; i<list.length; i++) {
			if(list[i].getText().equals(button)) {
				list[i].setStyle("-fx-background-color: yellow;");
			} else {
				list[i].setStyle(null);
			}
		}	
    }
    private void save() {
		DirectoryChooser dircChooser = new DirectoryChooser();
        dircChooser.setTitle("Choose Save Location");
       // String initial = Program.getFilePath();//create the file
       // initial = initial.substring(0, initial.lastIndexOf("\\")+1);
        dircChooser.setInitialDirectory(new File("/")); 
        //File file = dircChooser.showDialog(new Stage());
        String selectedDirPath = dircChooser.showDialog(Program.getPrimaryStage()).getAbsolutePath();
        String dirName = "Summer_Math_Seating_"+sess.getYear()+"_Session_"+sess.getSessionNum();
        selectedDirPath += "//"+dirName;
        try {
			Files.createDirectories(Paths.get(selectedDirPath));
		} catch (IOException e1) {
			popUpError("Unable to create directory");
		}
        PrintWriter monStream;
        PrintWriter tuesStream;
        PrintWriter wendesStream;
        PrintWriter thursStream;
        PrintWriter friStream;
		try {
			monStream = new PrintWriter(selectedDirPath+"\\Monday.txt");
			tuesStream = new PrintWriter(selectedDirPath+"\\Tuesday.txt");
			wendesStream = new PrintWriter(selectedDirPath+"\\Wendesday.txt");
			thursStream = new PrintWriter(selectedDirPath+"\\Thursday.txt");
			friStream = new PrintWriter(selectedDirPath+"\\Friday.txt");
			PrintWriter[] fileStreams = {monStream, tuesStream, wendesStream, thursStream, friStream};
	        String[] headers = {"Monday", "Tuesday", "Wendesday", "Thursday", "Friday"};
	        for (int i = 0;i < fileStreams.length;i++){
	        	fileStreams[i].println(headers[i]+" Seating Configuration");
	        	fileStreams[i].println();
	        	fileStreams[i].println();
	        	for (int j = 0; j<sess.getNumTables();j++){
	        		fileStreams[i].println("Table "+ (j+1)+":");
	        		for (int k = 0; k<sess.getDaySeating(sess.allDays[i]).getTable(j).getLength();k++){
	        			fileStreams[i].println("    "+ sess.getDaySeating(sess.allDays[i]).getTable(j).get(k));
	        		}
	        		fileStreams[i].println();
	        	}
	        } 
	        monStream.flush();
	        monStream.close();
	        tuesStream.flush();
	        tuesStream.close();
	        wendesStream.flush();
	        wendesStream.close();
	        thursStream.flush();
	        thursStream.close();
	        friStream.flush();
	        friStream.close();
	        Weekday[] days = {Weekday.MONDAY,Weekday.TUESDAY,Weekday.WENDESDAY,Weekday.THURSDAY,Weekday.FRIDAY};
	        String[] letters = {"A","B","C","D"};
	        SheetWriter excelW = new SheetWriter(selectedDirPath+"//Tables.xlsx");
	        for (int a = 0;a < headers.length;a++){
	        	excelW.addSheet(headers[a]);
	        	//fileStreams[i].println(headers[i]+" Seating Configuration");
	        	DaySeating d = sess.getDaySeating(days[a]);
	        	for(int i =0;i<d.getSize();i++) {
	    			for (int j=0;j<d.getTable(i).getLength();j++){
	    				excelW.addRow();
	    				d.getTable(i).get(j).setSeat(Integer.toString(i+1)+letters[j]);
	    				excelW.addCell(d.getTable(i).get(j).getLastName());
	    				excelW.addCell(d.getTable(i).get(j).getFirstName());
	    				excelW.addCell(d.getTable(i).get(j).getSeat());
	    				excelW.addCell(d.getTable(i).get(j).getGender().toString());
	    				excelW.addCell(d.getTable(i).get(j).getStringSchool());
	    				excelW.addCell(d.getTable(i).get(j).getHouse());
	    			}		
	        	}
	        }
	        excelW.save();
	        popUp("All files saved.");
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			popUpError("Invalid File");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			popUpError("Cannot save excel.");
		}
        
    }
        
    /**
	 * Saves the text in the output field to an Excel file
	 */
	//@SuppressWarnings({ "rawtypes", "unchecked" })
	/*private void save() {
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Save Location");
        String initial = Program.getFilePath();//create the file
        initial = initial.substring(0, initial.lastIndexOf("\\")+1);
        fileChooser.setInitialDirectory(new File(initial)); 
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Microsoft Excel", "*.xlsx"));
        fileChooser.setInitialFileName("Summer Math Groups Session "+session);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
            	SheetWriter sw = new SheetWriter(file.getAbsolutePath());
    			for(int i=0;i<5;i++) {//for each day, read the table data and write it to the Excel file
    				switchView(days[i]);
    				ObservableList<TableColumn> temp = table.getColumns();
    				sw.addSheet(days[i]);
    				sw.addRow();
    				for(TableColumn tc:temp) {
    					sw.addCell(tc.getText());
    				}
    				for(int k=0; k<table.getItems().size(); k++) {//add a student's data
    					sw.addRow();
    					for(TableColumn col:temp) {
    						sw.addCell((String) col.getCellData(k));
    					}
    				}
    			}
    			sw.save();
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("File Saved!");
    			alert.setHeaderText("File Saved!");
    			alert.setContentText("The student list has been saved at "+file.getAbsolutePath());
    			alert.showAndWait();	
            } catch (IOException e) {
				//Pop up: can't save file!
				//e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error!");
				alert.setHeaderText("File Not Saved!");
				alert.setContentText("The file could not be saved.\nCheck the file "+file.getAbsolutePath()+" and see if it is open or being used by another process.");
				alert.showAndWait();
            }
        }
	}*/
    
    private void popUp(String text) {
        Alert alert = new Alert(AlertType.INFORMATION, text);
        alert.setHeaderText(null);
        alert.show();
    }
    private void popUpError(String text) {
        Alert alert = new Alert(AlertType.ERROR, text);
        alert.setHeaderText(null);
        alert.show();
    }
}