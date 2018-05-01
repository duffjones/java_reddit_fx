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
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WindowController implements Initializable {
    
	
	private RedditHandler reddit = new RedditHandler();
	private ArrayList<String> redditdata = reddit.frontpage();
	private ArrayList<String> redditdataself = reddit.frontpageself();
	private ArrayList<String> imagelinks = reddit.frontpageimages();
	private Stage stage; 


	
	 private int i = 0; 
	 
    @FXML
    private TextField field;

    @FXML
    private Label label;
    
    @FXML
    private Accordion accord;
    
    @FXML
    private ImageView imagev; 
    
    @FXML
    private VBox subredphotos; 

    @FXML 
    private WebView webv; 
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        addTile();
    }

    @FXML
    public Button btnBeginTargeting;
    
    @FXML
    private Button btnExit;
    
    @FXML
    public void buttonClicked(ActionEvent event)throws IOException
    {
        Parent root;
        if(event.getSource()==btnBeginTargeting)
        {
           root=FXMLLoader.load(getClass().getResource("jred_secondary.fxml"));
           btnBeginTargeting.getScene().setRoot(root);
        }
        if(event.getSource()==btnExit)
        {
            Platform.exit();
        }
    }
    
            
    public void setStage (Stage thisStage){
        stage = thisStage;
    }
    
    private void addTile() {
        AnchorPane newPanelContent = new AnchorPane();
        newPanelContent.setMaxWidth(400);
        newPanelContent.getChildren().add(new Label(redditdataself.get(i)));
        System.out.println(redditdataself.get(i));
        TitledPane pane = new TitledPane(redditdata.get(i), newPanelContent);
        
        //System.out.println("test 2");
        ImageView imagetest = new ImageView(new Image(new String(imagelinks.get(i))));

        imagetest.setPreserveRatio(true);
        imagetest.fitHeightProperty().bind(pane.heightProperty());
        newPanelContent.getChildren().add(imagetest);
        accord.getPanes().add(pane);
        i++;
    }
    
    private void addImages() {
    	//ArrayList<String> sublinks = new ArrayList<String>();
    	//sublinks = reddit.getSubredditImgs("earthporn");
        //ImageView imagetest = new ImageView(new Image(new String("https://i.imgur.com/bfvBnwD.png")));
        //imagetest.setPreserveRatio(true);
        //imagetest.fitHeightProperty().bind(subredphotos.heightProperty());
        //subredphotos.getChildren().add(imagetest);
        //subredphotos.add((imagetest), 0, 0);
        subredphotos.getChildren().add(new ImageView(new Image(new String("https://i.imgur.com/bfvBnwD.png"))));
        
    }
    
    
    
//    private void loadEngine(String URL) {
//    	WebEngine engine = webv.getEngine();
//    	engine.load(URL);
//    }
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	reddit.getRedditData();
    	

    	String path = "https://i.imgur.com/bfvBnwD.png";
    	Image image = new Image(path);
        //imagev.setImage(image);
		
    			
    	  label.setText("this is a test to change shit");
          for(int j=1; j<20; j++){
              addTile(); 
          }  
    } 
    
    
    
    
    
}