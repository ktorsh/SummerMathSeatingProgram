package gui;
/**The table where sorted students are viewed before saving.
 * @author Skylar Chan
 */
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import studentOrg.DaySeating;
import studentOrg.Session;
import studentOrg.Student;
import util.SeatGender;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class StudentTable extends TableView {
	//These are the columns of student data in the table view
	TableColumn firstNameCol = new TableColumn("First Name");
	TableColumn lastNameCol = new TableColumn("Last Name");
	TableColumn seatCol = new TableColumn("Seat");
	TableColumn genCol = new TableColumn("Gender");
	TableColumn schoolCol = new TableColumn("Middle School");
	TableColumn houseCol = new TableColumn("House");

	public StudentTable() {
		//set columns
		//property value factory: <Object, field type>("name of field")
		setEditable(false);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("firstName"));
		firstNameCol.setStyle("-fx-font-size:16;");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("lastName"));
		lastNameCol.setStyle("-fx-font-size:16;");
		seatCol.setCellValueFactory(new PropertyValueFactory<Student,String>("seat"));
		seatCol.setStyle("-fx-font-size:16;");
		seatCol.setComparator(new SeatComparator());
		genCol.setCellValueFactory(new PropertyValueFactory<Student, SeatGender>("gender"));
		genCol.setStyle("-fx-font-size:16;");
		schoolCol.setCellValueFactory(new PropertyValueFactory<Student,String>("stringSchool"));
		schoolCol.setStyle("-fx-font-size:16;");
		houseCol.setCellValueFactory(new PropertyValueFactory<Student,String>("house"));
		houseCol.setStyle("-fx-font-size:16;");
		getColumns().addAll(lastNameCol, firstNameCol, seatCol, genCol, schoolCol, houseCol);
		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	
            
       
	}
	
	/**
	 * Updates this Table's contents with the specified list of Students.
	 * @param students The list of students to be viewed.
	 */
	
	//update the data based on the arraylist of students, preserving orientation and sorting arrangement of previous chart
	public void update(DaySeating d) {
		TableColumn sortcolumn = null;
        SortType st = null;
        if (!getSortOrder().isEmpty()) {
            sortcolumn = (TableColumn) getSortOrder().get(0);
            st = sortcolumn.getSortType();
        }
        String[] letters = {"A","B","C","D"};
		ObservableList<Student> data = FXCollections.observableArrayList();
		for(int i =0;i<d.getSize();i++) {
			for (int j=0;j<d.getTable(i).getLength();j++){
				d.getTable(i).get(j).setSeat(Integer.toString(i+1)+letters[j]);
				data.add(d.getTable(i).get(j));
			}		
		}
		setItems(data);
		if (sortcolumn!=null) {
            getSortOrder().add(sortcolumn);
            sortcolumn.setSortType(st);
            sortcolumn.setSortable(true); // This performs a sort
        }
	}

	
	
}