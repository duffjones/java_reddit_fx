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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WindowController implements Initializable {


    private RedditHandler reddit = new RedditHandler();
    private ArrayList < String > redditdata = reddit.frontpage();
    private ArrayList < String > redditdataself = reddit.frontpageself();
    private ArrayList < String > imagelinks = reddit.frontpageimages();
    private ProgressIndicator pi = new ProgressIndicator();
//    private VBox box = new VBox(pi);
    private Stage stage;
    private String name;
    private int i = 0;

    @FXML
    private TextField subtextfield;

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
    public Button btnBeginTargeting;

    @FXML
    private Button btnExit;

    @FXML
    private StackPane stack;
    
    @FXML
    private void spaceButton(ActionEvent event) throws IOException {
        subtextfield.setText("spaceporn");
    }
    
    @FXML
    private void villageButton(ActionEvent event) throws IOException {
        subtextfield.setText("villageporn");
    }
    
    @FXML
    private void earthButton(ActionEvent event) throws IOException {
        subtextfield.setText("earthporn");
    }
    
    @FXML
    private void imageButton(ActionEvent event) throws IOException {
        subtextfield.setText("images");
    }


    @FXML
    public void buttonClicked(ActionEvent event) throws IOException {

    	
        new Thread(task).start();
        task.setOnSucceeded(e -> {
            if (event.getSource() == btnBeginTargeting) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("jred_secondary.fxml"));
                    btnBeginTargeting.getScene().setRoot(root);
                } catch (IOException e1) {e1.printStackTrace();}}
            if (event.getSource() == btnExit) {
                Platform.exit();
            }
        });}

    Task < Void > task = new Task < Void > () {
        @Override
        protected Void call() throws Exception {
            buttonRootChange();
            return null;
        }
    };

    private void buttonRootChange() {

        if (subtextfield.getText().trim().isEmpty()) {
            System.out.println("textfield is empty, using default subreddit");
            Passer.getInstance().setSubreddit("earthporn");
        } else {
            name = subtextfield.getText();
            Passer.getInstance().setSubreddit(name);
        }

    }

    public void setStage(Stage thisStage) {
        stage = thisStage;
    }

    private void addTile() {
        AnchorPane newPanelContent = new AnchorPane();
        newPanelContent.setMaxWidth(400);
        newPanelContent.getChildren().add(new Label(redditdataself.get(i)));
        System.out.println(redditdataself.get(i));
        TitledPane pane = new TitledPane(redditdata.get(i), newPanelContent);
        ImageView imagetest = new ImageView(new Image(new String(imagelinks.get(i)), true));
        imagetest.setPreserveRatio(true);
        imagetest.fitHeightProperty().bind(pane.heightProperty());
        newPanelContent.getChildren().add(imagetest);
        accord.getPanes().add(pane);
        i++;
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reddit.getRedditData();
        
//        box.setAlignment(Pos.CENTER);
//        stack.getChildren().add(box);
//        box.setVisible(false);
        

        for (int j = 1; j < 10; j++) {
            addTile();
        }
    }
}