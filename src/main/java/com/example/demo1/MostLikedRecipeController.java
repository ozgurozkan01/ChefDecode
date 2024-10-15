package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MostLikedRecipeController
{
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label rate;

    private MainMostLikedRecipe mainMostLikedRecipe;

    public void setData(MainMostLikedRecipe mostLikedRecipe)
    {
        this.mainMostLikedRecipe = mostLikedRecipe;

        rate.setText(mainMostLikedRecipe.getRate());
        name.setText(mainMostLikedRecipe.getName());
        Image mostLikedImage = new Image(getClass().getResourceAsStream(mainMostLikedRecipe.getImageSrc()));
        image.setImage(mostLikedImage);
    }
}
