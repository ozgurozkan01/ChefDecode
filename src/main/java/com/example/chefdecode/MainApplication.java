package com.example.chefdecode;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane root = new Pane();

        int windowHeight = 800;
        int windowWidth = 1440;

        TextField searchBar = new TextField();
        searchBar.setStyle(
                "-fx-background-radius: 50px;"
        );

        searchBar.setPrefWidth(500);
        searchBar.setPrefHeight(30);
        searchBar.setLayoutX(windowWidth / 2 - searchBar.getPrefWidth() / 2);
        searchBar.setLayoutY(15);

        root.getChildren().add(searchBar);


        Button searchButton = new Button("🔍");
        searchButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 14px;" +
                        "-fx-cursor: hand;"
        );

        searchButton.setPrefWidth(100);
        searchButton.setLayoutX(windowWidth / 2 + searchBar.getLayoutX() / 2 - 60);
        searchButton.setLayoutY(17);

        root.getChildren().add(searchButton);

        Button recipeAddButton = new Button("Add Recipe");
        recipeAddButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 14px;" +
                        "-fx-cursor: hand;"
        );
        recipeAddButton.setLayoutX(1150);
        recipeAddButton.setLayoutY(20);
        recipeAddButton.setPrefWidth(100);
        root.getChildren().add(recipeAddButton);

        Button savedButton = new Button("Saved");
        savedButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 14px;" +
                        "-fx-cursor: hand;"
        );
        savedButton.setLayoutX(350);
        savedButton.setLayoutY(20);
        savedButton.setPrefWidth(100);
        root.getChildren().add(savedButton);

        Line line = new Line(400, 250, 900, 250);
        line.setStyle(
                "-fx-stroke: #EE4D4D;" +
                "-fx-stroke-width: 50px;" +
                "-fx-stroke-line-cap: round;" );

        root.getChildren().add(line);

        Scene scene = new Scene(root, windowWidth, windowHeight);

        stage.setTitle("Chef Decode");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}