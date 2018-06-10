package gui;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpWindow extends Stage {
	private TextArea help = new TextArea("Summer Math Seating Program Help"
			+ "\n\nThe Excel spreadsheet must contain one student's data per row."
			+ "\n\nThe first row is not read by the program."
			+ "\n\n The structure of the excel file must be by Student ID, Alpha Name, Gender, ."
			+ "\n\nPHS House, Math Session, Middle School, and 8th Grade Math course"
			+ "\n\n");
	public HelpWindow() {
		help.setEditable(false);
		help.setFont(new Font("Times New Roman",16));
		help.setWrapText(true);
		Scene helpScene = new Scene(help, 551, 400);
		setScene(helpScene);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Help");
		show();
	}
	
}