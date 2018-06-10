
package gui;

import javafx.scene.control.Label;

import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingsScene extends Stage {
	VBox container = new VBox(4);
	Label title = new Label("Settings");
	Label girlTitle = new Label("Girls:");
    ToggleGroup girls = new ToggleGroup();
    RadioButton g1 = new RadioButton("Low");
    RadioButton g2 = new RadioButton("Med");
    RadioButton g3 = new RadioButton("High");
    HBox girlTab = new HBox(8);
    
    Label clemTitle = new Label("Clemente:");
    ToggleGroup clemente = new ToggleGroup();
    RadioButton c1 = new RadioButton("Low");
    RadioButton c2 = new RadioButton("Med");
    RadioButton c3 = new RadioButton("High");
    HBox clemTab = new HBox(8);
    
    Label togethLabel = new Label("Unique Pairing: ");
    ToggleGroup together = new ToggleGroup();
    RadioButton t1 = new RadioButton("Low");
    RadioButton t2 = new RadioButton("Med");
    RadioButton t3 = new RadioButton("High");
    HBox togetherTab = new HBox(8);
	public SettingsScene() {
		title.setFont(new Font("Times New Roman", 20.0));
		girlTitle.setFont(new Font("Times New Roman", 16.0));
		g1.setToggleGroup(girls);
		g2.setToggleGroup(girls);
		g3.setToggleGroup(girls);
		g3.setSelected(true);
		girlTab.getChildren().addAll(g1,g2,g3);
		clemTitle.setFont(new Font("Times New Roman", 16.0));
		c1.setToggleGroup(clemente);
		c2.setToggleGroup(clemente);
		c3.setToggleGroup(clemente);
		c2.setSelected(true);
		clemTab.getChildren().addAll(c1,c2,c3);
		togethLabel.setFont(new Font("Times New Roman", 16.0));
		t1.setToggleGroup(together);
		t2.setToggleGroup(together);
		t3.setToggleGroup(together);
		t1.setSelected(true);
		togetherTab.getChildren().addAll(t1,t2,t3);
		
		container.getChildren().addAll(title, girlTitle, girlTab, clemTitle, clemTab, togethLabel, togetherTab);

		Scene helpScene = new Scene(container, 551, 400);
		setScene(helpScene);
		initModality(Modality.APPLICATION_MODAL);
		setTitle("Settings");
		show();
	}
	
}


