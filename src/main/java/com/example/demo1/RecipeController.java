package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeController
{
    @FXML
    private Label rate;
    @FXML
    private ImageView recipeImage;
    @FXML
    private Label recipeName;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    @FXML
    private ImageView star;
    @FXML
    private AnchorPane anchor;

    private RecipeDetailController recipeDetailController;
    private Recipe recipe;

    TopBarController topBarController = new TopBarController();
    public void setData(Recipe recipe)
    {
        this.recipe = recipe;
        recipeDetailController = new RecipeDetailController(recipe, anchor);

        String rateNumber = String.valueOf(recipe.getRate());
        rate.setText(rateNumber + " / 5");
        recipeName.setText(recipe.getName());

        String saveText = recipe.isSaved() ? "Unsave" : "Save";
        saveButton.setText(saveText);
        //Image image = new Image((getClass().getResourceAsStream(recipe.getImgSrc())));
        //recipeImage.setImage(image);
        //Image starImage = new Image(getClass().getResourceAsStream(recipe.getStarImgSrc()));
        //star.setImage(starImage);
    }

    public void OnSavedButtonPressed()
    {
        if (recipe.isSaved())  { SavedRecipeController.getSavedRecipes().remove(recipe); }
        else                   { SavedRecipeController.addSavedRecipe(recipe); }

        recipe.setIsSaved(!recipe.isSaved());
        setData(recipe);
        ActionEvent actionEvent = new ActionEvent();
        topBarController.OnSavedButtonPressed(actionEvent);
    }

    public void loadDetails()
    {
        if (recipeDetailController != null)
        {
            recipeDetailController.goToRecipeDetails();
        }
        else
        {
            System.err.println("recipeDetailController is not initialized.");
        }

    }
}
