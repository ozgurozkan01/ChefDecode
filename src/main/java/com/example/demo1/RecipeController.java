package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class RecipeController {

    @FXML
    private Button editButton;
    @FXML
    private Label rate;
    @FXML
    private ImageView recipeImage;
    @FXML
    private Label recipeName;
    @FXML
    private Button saveButton;
    @FXML
    private ImageView star;

    private Recipe recipe;


    public void setData(Recipe recipe)
    {
        this.recipe = recipe;

        String rateNumber = String.valueOf(recipe.getRate());
        rate.setText(rateNumber + " / 5");
        recipeName.setText(recipe.getName());
        //Image image = new Image((getClass().getResourceAsStream(recipe.getImgSrc())));
        //recipeImage.setImage(image);
        //Image starImage = new Image(getClass().getResourceAsStream(recipe.getStarImgSrc()));
        //star.setImage(starImage);
    }
}
