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
        int space = 30;

        TextField searchBar = new TextField();
        searchBar.setStyle(
                "-fx-background-radius: 50px;"
        );

        searchBar.setPrefWidth(500);
        searchBar.setPrefHeight(30);
        searchBar.setLayoutX(windowWidth / 2 - searchBar.getPrefWidth() / 2);
        searchBar.setLayoutY(space);

        root.getChildren().add(searchBar);


        Button searchButton = new Button("🔍");
        searchButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 14px;" +
                        "-fx-cursor: hand;"
        );

        searchButton.setPrefWidth(100);
        searchButton.setLayoutX(windowWidth / 2 + searchBar.getLayoutX() / 2 - 60);
        searchButton.setLayoutY(space + 2);

        root.getChildren().add(searchButton);

        Button recipeAddButton = new Button("Add Recipe");
        recipeAddButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 14px;" +
                        "-fx-cursor: hand;"
        );
        recipeAddButton.setLayoutX(1150);
        recipeAddButton.setLayoutY(space);
        recipeAddButton.setPrefWidth(100);
        root.getChildren().add(recipeAddButton);

        Button savedButton = new Button("Saved");
        savedButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 14px;" +
                        "-fx-cursor: hand;"
        );
        savedButton.setLayoutX(350);
        savedButton.setLayoutY(space);
        savedButton.setPrefWidth(100);
        root.getChildren().add(savedButton);


        for (int i=0; i < 5; i++){
            Button categoryButton = new Button("Category ");
            categoryButton.setStyle(
                    "-fx-background-color: #EE4D4D;" +
                    "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;" +
                    "-fx-text-fill: white;"
            );

            categoryButton.setLayoutX(400 + i * 130); // 130 = 150 - 20
            categoryButton.setLayoutY(200);
            categoryButton.setPrefWidth(150);
            categoryButton.setPrefHeight(50);

            Line line = new Line();
            Line endLine = new Line();

            if (i == 0 || i == 4) {
                categoryButton.setStyle(
                        "-fx-background-color: #EE4D4D;" +
                        "-fx-font-size: 20px;" +
                        "-fx-cursor: hand;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius:20px;" +
                        "-fx-text-fill: white;"
                );
            }
            else {
                line.setStartX(400 + i * 130); // Başlangıç X koordinatı
                line.setStartY(205);           // Başlangıç Y koordinatı
                line.setEndX(400 + i * 130);   // Bitiş X koordinatı
                line.setEndY(245);
                line.setStyle(
                    "-fx-stroke: #FFA329;" +
                    "-fx-stroke-width: 4px;" +
                    "-fx-stroke-line-cap: round;"
                );

                if (i == 3) {
                    endLine.setStartX(400 + 150 + i * 130); // Başlangıç X koordinatı
                    endLine.setStartY(205);           // Başlangıç Y koordinatı
                    endLine.setEndX(400 + 150 + i * 130);   // Bitiş X koordinatı
                    endLine.setEndY(245);
                    endLine.setStyle(
                            "-fx-stroke: #FFA329;" +
                            "-fx-stroke-width: 4px;" +
                            "-fx-stroke-line-cap: round;"
                    );
                }
            }


            if (i == 4){
                root.getChildren().add(0, categoryButton);
            }
            else {
                root.getChildren().add(categoryButton);
            }

            root.getChildren().add(line);
            root.getChildren().add(endLine);
        }

        Button allRecipesButton = new Button("Show All Recipes...");
        allRecipesButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #FFA329;" +
                        "-fx-cursor: hand;"
        );

        allRecipesButton.setLayoutX(620);
        allRecipesButton.setLayoutY(windowHeight - space * 2);
        allRecipesButton.setPrefWidth(185);
        root.getChildren().add(allRecipesButton);

        Scene scene = new Scene(root, windowWidth, windowHeight);

        stage.setTitle("Chef Decode");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}