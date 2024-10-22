package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    @FXML
    private AnchorPane anchor;

    private Recipe mainMostLikedRecipe;
    private RecipeDetailController recipeDetailController;

    //private List<MainMostLikedRecipe> mainMostLikedRecipes = new ArrayList<>();
    //private MainMostLikedRecipe mainMostLikedRecipe;

    public void setData(Recipe mostLikedRecipe)
    {
        this.mainMostLikedRecipe = mostLikedRecipe;

        rate.setText(String.valueOf(mostLikedRecipe.getRate()));
        name.setText(mainMostLikedRecipe.getName());
        //Image mostLikedImage = new Image(getClass().getResourceAsStream(mainMostLikedRecipe.getImageSrc()));
        //image.setImage(mostLikedImage);
    }

    public void loadDetails()
    {
        goToRecipeDetails();
    }

    private void goToRecipeDetails()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo1/recipeDetail - Copy.fxml"));

            Parent recipeDetailsRoot = loader.load();

            recipeDetailController = loader.getController();
            recipeDetailController.setRecipeDetails(mainMostLikedRecipe);
            recipeDetailController.updateSaveButton(mainMostLikedRecipe.isSaved());

            Scene recipeDetailsScene = new Scene(recipeDetailsRoot);
            Stage window = (Stage) anchor.getScene().getWindow();

            if (window != null)
            {
                window.setScene(recipeDetailsScene);
                window.show();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
