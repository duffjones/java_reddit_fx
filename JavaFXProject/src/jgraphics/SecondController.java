package jgraphics;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SecondController implements Initializable {
    
	
	private RedditHandler reddit = new RedditHandler();
	ArrayList<String> sublinks = new ArrayList<String>();
	private String subredditname; 
	private int i = 0; 
	 
    
    @FXML
    private VBox subredphotos; 
    
    @FXML 
    private Label label; 

    @FXML
    public Button returnbutton;

    @FXML
    private Button btnExit;

    @FXML
    public void buttonClicked(ActionEvent event)throws IOException
    {
        Parent root;
        if(event.getSource()==returnbutton)
        {
           root=FXMLLoader.load(getClass().getResource("jredditFXML.fxml"));
           returnbutton.getScene().setRoot(root);
        }
        if(event.getSource()==btnExit)
        {
            Platform.exit();
        }
    }
    

    private void addImages() {
    	
    	int imagecount = sublinks.size();
    	int rows; 
    	
    	if(imagecount > 3) { rows = imagecount/3;}
    	else {return;}
    	    	
    	for(int jj=1; jj<= rows; jj++){
    		
    	    HBox hbox = new HBox();
    	    hbox.setPadding(new Insets(10, 10, 10, 10));
    	    hbox.setSpacing(20);

    	    for(int j=1; j<=3; j++){
    	    	ImageView imagetest = new ImageView(new Image(new String((sublinks.get(i)))));
    	    	imagetest.setPreserveRatio(true);
    	    	imagetest.setFitHeight(200);
    	    	hbox.getChildren().add(imagetest);
    	    	i++; 
    	    }  
        subredphotos.getChildren().add(hbox);
    	}

    }
    
	public void setSubreddit(String subreddit){
	    this.subredditname = subreddit;
	}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String subby = Passer.getInstance().currentSubreddit(); 
    	System.out.println(subby);
    	label.setText(subby);
    	sublinks = reddit.getSubredditImgs(subby);
    	addImages();  
    } 
 
}