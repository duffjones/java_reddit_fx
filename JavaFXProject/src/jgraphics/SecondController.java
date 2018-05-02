package jgraphics;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SecondController implements Initializable {
    
	
	private RedditHandler reddit = new RedditHandler();
	ArrayList<String> sublinks = new ArrayList<String>();
	ArrayList<ImageView> imagelist = new ArrayList<ImageView>();
	
	private String subredditname; 
	private int i = 0; 
	 
	
	@FXML
	private Rectangle clipper; 
    
    @FXML
    private TilePane subredphotos; 
    
    @FXML 
    private Label label; 

    @FXML
    public Button returnbutton;

    @FXML
    private Button btnExit;

    @FXML 
    private Pane stackp; 
    
    @FXML
    public void buttonClicked(ActionEvent event)throws IOException
    {
    	
        ProgressIndicator pi = new ProgressIndicator();
        VBox box = new VBox(pi);
        box.setAlignment(Pos.CENTER);
    	stackp.getChildren().add(box);
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
                System.out.print("thread returning null");

    }
    
    
    Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
    	    for(int j=0; j<9; j++){
    	    	ImageView imagetest = new ImageView(new Image(new String((sublinks.get(i))), true));
    	    	imagetest.setPreserveRatio(true);
    	     	imagetest.setFitHeight(200);
    	    	imagelist.add(imagetest);
    	    	i++; 
    	    }  
			return null;
        }
    };
    
    private void addImages() {
    	subredphotos.setPrefTileHeight(200);
    	subredphotos.setVgap(20);
    	
    	
    	for(int j = 0; j<6; j++) {
    	subredphotos.getChildren().add(imagelist.get(j));
    }}

	public void setSubreddit(String subreddit){
	    this.subredditname = subreddit;
	}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String subby = Passer.getInstance().currentSubreddit();
    	sublinks = reddit.getSubredditImgs(subby);
    	new Thread(task).start();
    	task.setOnSucceeded(e -> {
    		label.setText(subby);
        	addImages();

    	});
    	
    
        
    } 
 
}