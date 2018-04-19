package jgraphics;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        
        reddithello reddit = new reddithello();
        reddithello.startReddit();
        ArrayList<String> frontpage = reddit.frontpage();
        
        
        
        
        Button btn = new Button();
        btn.setText("Reddit Test");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (frontpage);
        list.setItems(items);
        list.setPrefWidth(100);
        list.setPrefHeight(70);
        
        
        
        
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(list);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}