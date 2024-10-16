package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryPageController implements Initializable {
    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private List<Recipe> recipes = new ArrayList<>();

    private List<Recipe> getRecipesData() {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe;

        for (int i = 0; i < 10; i++) {
            recipe = new Recipe();
            recipe.setName("Çiğ Köfte");
            recipe.setRate("5.0");
            recipe.setImgSrc("çk.jpg");
            recipe.setStarImgSrc("star.png");
            recipeList.add(recipe);
        }

        return recipeList;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipes.addAll(getRecipesData());

        int column = 0;
        int row = 2;

        try {
            for (var recipe : recipes) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                RecipeController recipeController = fxmlLoader.getController();
                recipeController.setData(recipe);

                if (column == 3)
                {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
