package jgraphics;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.AnchorPane;

public class WindowController implements Initializable {
    
	private reddithello reddit = new reddithello();
	private ArrayList<String> redditdata = reddit.frontpage();
	 private int i = 0; 
	 
    @FXML
    private TextField field;

    @FXML
    private Label label;
    
    @FXML
    private Accordion accord;


//    @FXML
//    private ListView<String> frontpagelist;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
//        AnchorPane newPanelContent = new AnchorPane();
//        newPanelContent.getChildren().add(new Label("Hello World"));
//        TitledPane pane = new TitledPane("World Pane", newPanelContent);
//        accord.getPanes().add(pane);
        System.out.println("test");
        addTile();
   
    }


    
    
    private void addTile() {
    	
    	
        AnchorPane newPanelContent = new AnchorPane();
        newPanelContent.getChildren().add(new Label(redditdata.get(i)));
        i++;
        TitledPane pane = new TitledPane("World Pane", newPanelContent);
        System.out.println("test 2");
        accord.getPanes().add(pane);
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	  label.setText("this is a test to change shit");
    	  
//    	  names.addAll(
//    	             "Adam", "Alex", "Alfred", "Albert",
//    	             "Brenda", "Connie", "Derek", "Donny", 
//    	             "Lynne", "Myrtle", "Rose", "Rudolph", 
//    	             "Tony", "Trudy", "Williams", "Zach"
//    	        );
//    	  
//    	  
//    	 frontpagelist.add("test");
    	  //frontpagelist.setItems(names);
    	 // frontpagelist.setCellFactory(ComboBoxListCell.forListView(names));

    } 
    
    
    
    
    
}