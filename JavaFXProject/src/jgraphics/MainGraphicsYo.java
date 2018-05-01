package jgraphics;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class MainGraphicsYo extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
    
       Parent root = FXMLLoader.load(getClass().getResource("jredditFXML.fxml"));
        Scene scene = new Scene(root, 761, 798);
        stage.setTitle("FXML Welcome");
        
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.setMaxWidth(1600);
        stage.setScene(scene);
        
        stage.show();
        WindowController controller = new WindowController(); 
        controller.setStage(stage);
    }
 

}