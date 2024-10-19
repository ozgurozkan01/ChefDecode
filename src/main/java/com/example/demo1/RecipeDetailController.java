package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RecipeDetailController
{
    @FXML
    private VBox ingredients;
    @FXML
    private VBox instructions;
    @FXML
    private Label rate;
    @FXML
    private Label ratedInfo;
    @FXML
    private Label recipeName;
    @FXML
    private ImageView recipeImage;

    void setRecipeDetails(Recipe recipe)
    {
        ingredients.getChildren().addAll(new Label("* asdfasdfasdfasdf "), new Label("* 0QOFMAGKLFGADFG "), new Label("* 89456151135131313"));
        instructions.getChildren().addAll(new Label("* asdfasdfasdfasdf "), new Label("* 0QOFMAGKLFGADFG "), new Label("* 89456151135131313"));
        rate.setText("3.0");
        ratedInfo.setText("( 1522342 people rated )");
        recipeName.setText("Baklava");

    }
}
