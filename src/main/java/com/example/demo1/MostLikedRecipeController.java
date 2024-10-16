package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MostLikedRecipeController
{
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label rate;

    private List<MainMostLikedRecipe> mainMostLikedRecipes = new ArrayList<>();
    private MainMostLikedRecipe mainMostLikedRecipe;

    public void setData(MainMostLikedRecipe mostLikedRecipe)
    {
        this.mainMostLikedRecipe = mostLikedRecipe;

        rate.setText(mainMostLikedRecipe.getRate());
        name.setText(mainMostLikedRecipe.getName());
        Image mostLikedImage = new Image(getClass().getResourceAsStream(mainMostLikedRecipe.getImageSrc()));
        image.setImage(mostLikedImage);
    }

    private List<MainMostLikedRecipe> getMainMostLikedRecipeData()
    {
        List<MainMostLikedRecipe> mostLikedRecipes = new ArrayList<>();
        MainMostLikedRecipe mostLikedRecipe;

        for (int i = 0; i < 6; i++)
        {
            mostLikedRecipe = new MainMostLikedRecipe();
            mostLikedRecipe.setName("Çiğ Köfte");
            mostLikedRecipe.setRate("5.0");
            mostLikedRecipe.setImageSrc("çk.jpg");
            mainMostLikedRecipes.add(mostLikedRecipe);
        }

        return mostLikedRecipes;
    }

    void loadMostLikedRecipes(GridPane mostLikeGrid)
    {
        if (mostLikeGrid == null) {
            System.out.println("recipesGrid is null");
            return;
        }

        mainMostLikedRecipes.addAll(getMainMostLikedRecipeData());

        int recipeColumn = 0;
        int recipeRow = 2;

        try
        {
            for (var mostLikedRecipe : mainMostLikedRecipes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/mainMostLikedRecipes.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();
                MostLikedRecipeController mostLikedRecipeController = fxmlLoader.getController();
                mostLikedRecipeController.setData(mostLikedRecipe);

                mostLikeGrid.add(anchorPane, recipeColumn, recipeRow++);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }

        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
