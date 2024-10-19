package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("recipeDetail - Copy.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        RecipeDetailController recipeDetailController = fxmlLoader.getController();

        Recipe recipe = new Recipe("Baklava", "Dessert", 120,
                "asdfasdf", 3, 4);
        recipeDetailController.setRecipeDetails(recipe);

        stage.setTitle("Chef Decode");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}