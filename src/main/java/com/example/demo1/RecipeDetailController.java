package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeDetailController implements Initializable
{
    @FXML
    private Button editButton;
    @FXML
    private VBox ingredients;
    @FXML
    private VBox instructions;
    @FXML
    private Label rate; //
    @FXML
    private Label ratedInfo; //
    @FXML
    private ImageView recipeImage;
    @FXML
    private Label recipeName;
    @FXML
    private Button saveButton;
    @FXML
    private Label preparationTimeLabel;
    @FXML
    private Label unitPriceLabel;

    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;

    private Database db = new Database();

    boolean voteStarsVisible = false;
    boolean isSaved;
    Recipe recipe;

    void setRecipeDetails(Recipe recipe)
    {
        this.recipe = recipe;

        System.out.println("Recipe ID : " + recipe.getID());

        List<String> ingredientNameList = db.getIngredientsByRecipeId(recipe.getID());

        if (ingredientNameList.isEmpty())
        {
            System.out.println("List is empty.");
        }
        for (var ingredient : ingredientNameList)
        {
            ingredients.getChildren().add(new Label(ingredient));
        }

        instructions.getChildren().add(new Label(recipe.getInstruction()));

        rate.setText(Float.toString(recipe.getRate()));
        ratedInfo.setText("( " + recipe.getNumberPoints() + " people rated )");
        recipeName.setText(recipe.getName());
        preparationTimeLabel.setText(Integer.toString(recipe.getPreparationTime()) + " dk");
        unitPriceLabel.setText(recipe.getTotalUnitPrice() + " ₺");
    }

    public void updateSaveButton(boolean b)
    {
        isSaved = b;
        String saveText = isSaved ? "Unsave" : "Save";
        saveButton.setText(saveText);
    }

    @FXML
    public void OnSavedButtonPressed()
    {
        if (recipe.isSaved())  { SavedRecipeController.getSavedRecipes().remove(recipe); }
        else                   { SavedRecipeController.addSavedRecipe(recipe); }

        recipe.setIsSaved(!recipe.isSaved());
        updateSaveButton(!isSaved);
    }

    @FXML
    public void OnEditButtonPressed()
    {
        try {
            Stage stage = (Stage) editButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editPage.fxml"));
            Parent root = loader.load();

            EditController editController = loader.getController();
            editController.setRecipe(recipe);
            editController.setPreviousScene(stage.getScene());
            editController.setBeforeStage(stage);
            editController.loadProperties();

            stage.setScene(new Scene(root));
            stage.setTitle("Edit Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void OnStarImagePressed()
    {
        if (recipe.isVoted()) { return; }

        if (voteStarsVisible)
        {
            voteStarsVisible = false;
            star1.setVisible(false);
            star2.setVisible(false);
            star3.setVisible(false);
            star4.setVisible(false);
            star5.setVisible(false);
        }
        else
        {
            voteStarsVisible = true;
            star1.setVisible(true);
            star2.setVisible(true);
            star3.setVisible(true);
            star4.setVisible(true);
            star5.setVisible(true);
        }
    }
    public void OnStar1Pressed()
    {
        if (!voteStarsVisible) { return; }

        db.updateRecipePoints(recipe.getID(), 1);
        setRecipeRate(1);

        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        star4.setVisible(false);
        star5.setVisible(false);
        voteStarsVisible = false;
        recipe.setVoted(true);
    }
    public void OnStar2Pressed()
    {
        if (!voteStarsVisible) { return; }

        db.updateRecipePoints(recipe.getID(), 2);
        setRecipeRate(2);

        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        star4.setVisible(false);
        star5.setVisible(false);
        voteStarsVisible = false;
        recipe.setVoted(true);
    }
    public void OnStar3Pressed()
    {
        if (!voteStarsVisible) { return; }

        db.updateRecipePoints(recipe.getID(), 3);
        setRecipeRate(3);

        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        star4.setVisible(false);
        star5.setVisible(false);
        voteStarsVisible = false;
        recipe.setVoted(true);
    }
    public void OnStar4Pressed()
    {
        if (!voteStarsVisible) { return; }

        db.updateRecipePoints(recipe.getID(), 4);
        setRecipeRate(4);

        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        star4.setVisible(false);
        star5.setVisible(false);
        voteStarsVisible = false;
        recipe.setVoted(true);
    }
    public void OnStar5Pressed()
    {
        if (!voteStarsVisible) { return; }

        db.updateRecipePoints(recipe.getID(), 5);
        setRecipeRate(5);

        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        star4.setVisible(false);
        star5.setVisible(false);
        voteStarsVisible = false;
        recipe.setVoted(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        star1.setVisible(false);
        star2.setVisible(false);
        star3.setVisible(false);
        star4.setVisible(false);
        star5.setVisible(false);
    }

    public void setRecipeRate(int givenRate)
    {
        recipe.setNumberPoints(recipe.getNumberPoints() + 1);
        recipe.setTotalPoints(recipe.getTotalPoints() + givenRate);

        float newRate = recipe.getTotalPoints() / recipe.getNumberPoints();

        rate.setText(Float.toString(newRate));
        ratedInfo.setText("( " + recipe.getNumberPoints() + " people rated )");
    }
}
