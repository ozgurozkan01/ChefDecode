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

public class RecipeController implements Initializable
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

    public void setData(Recipe recipe)
    {
        this.recipe = recipe;

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
    }

    public void loadDetails()
    {
        goToRecipeDetails();
    }

    public void goToRecipeDetails() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo1/recipeDetail - Copy.fxml"));

            Parent recipeDetailsRoot = loader.load();

            recipeDetailController = loader.getController();
            recipeDetailController.setRecipeDetails(recipe);
            recipeDetailController.updateSaveButton(recipe.isSaved());

            Scene recipeDetailsScene = new Scene(recipeDetailsRoot);
            Stage window = (Stage) anchor.getScene().getWindow();

            if (window != null) {
                window.setScene(recipeDetailsScene);
                window.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }
}
