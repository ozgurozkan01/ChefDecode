package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

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

    private Recipe recipe;
    private AnchorPane recipeAnchor;

    public RecipeDetailController()
    {

    }
    public RecipeDetailController(Recipe recipe, AnchorPane anchor)
    {
        this.recipe = recipe;
        this.recipeAnchor = anchor;
    };

    void setRecipeDetails(Recipe recipe)
    {
        instructions.getChildren().addAll(new Label("1-\u00A0 Bulgur (1 paket)"), new Label("2-\u00A0 Salça (2 yemek kaşığı) "), new Label("3-\u00A0İyot (2 çay kaşığı)"));
        Label l = new Label();
        l.setText("1-\u00A0Kýyma, tuz, karabiber, kimyon ve diðer baharatlarý bir kapta iyice yoðurun (5 dakika).");
        l.setWrapText(true);
        l.setStyle("-fx-font-size: 15px;");

        Label l2 = new Label();
        l2.setText("2-\u00A0Kýzartma tavasýnda 1-2 yemek kaþýðý yað ýsýtýn ve patlýcanlarý her iki tarafýný altýn rengi alana kadar kýzartýn (10 dakika).");
        l2.setWrapText(true);
        l2.setStyle("-fx-font-size: 15px;");

        Label l3 = new Label();
        l3.setText("3-\u00A0Karýþýmý yaðlanmýþ fýrýn kabýna dökün ve önceden ýsýtýlmýþ 180°C fýrýnda yaklaþýk 25-30 dakika piþirin (30 dakika).");
        l3.setWrapText(true);
        l3.setStyle("-fx-font-size: 15px;");

        ingredients.getChildren().addAll(l, l2, l3);
        rate.setText("3.0");
        ratedInfo.setText("( 1522342 people rated )");
        recipeName.setText("Baklava");

    }

    public void goToRecipeDetails()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo1/recipeDetail - Copy.fxml"));

            Parent recipeDetailsRoot = loader.load();

            RecipeDetailController controller = loader.getController();
            controller.setRecipeDetails(recipe);

            Scene recipeDetailsScene = new Scene(recipeDetailsRoot);
            Stage window = (Stage) recipeAnchor.getScene().getWindow();

            if (window != null)
            {
                window.setScene(recipeDetailsScene);
                window.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
