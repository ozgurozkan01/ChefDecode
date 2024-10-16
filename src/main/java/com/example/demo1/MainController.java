package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private HBox includeHeader;
    @FXML
    private ScrollPane menusScrollPane;
    @FXML
    private ScrollPane mostLikedScrollPane;
    @FXML
    private ScrollPane recipesScrollPane;
    @FXML
    private GridPane recipesGrid;
    @FXML
    private GridPane mostLikeGrid;

    private List<Recipe> recipes = new ArrayList<>();

    MostLikedRecipeController mostLikedRecipeController;
    SavedRecipeController savedRecipeController;

    public MainController()
    {
        mostLikedRecipeController = new MostLikedRecipeController();
        savedRecipeController = new SavedRecipeController();
    }

    public void OnCategoryButtonPressed()
    {
        System.out.println("Category Button Pressed");
    }

    private List<Recipe> getRecipesData()
    {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe recipe;

        for (int i = 0; i < 10; i++)
        {
            recipe = new Recipe();
            recipe.setName("Çiğ Köfte");
            recipe.setRate("5.0");
            recipe.setImgSrc("çk.jpg");
            recipe.setStarImgSrc("star.png");
            recipeList.add(recipe);
        }

        return recipeList;
    }

    void loadRecipes()
    {
        if (recipesGrid == null || mostLikeGrid == null) {
            System.out.println("recipesGrid is null");
            return;
        }

        recipes.addAll(getRecipesData());

        int recipeColumn = 0;
        int recipeRow = 2;

        try
        {
            for (var recipe : recipes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                RecipeController recipeController = fxmlLoader.getController();
                recipeController.setData(recipe);

                if (recipeColumn == 2)
                {
                    recipeColumn = 0;
                    recipeRow++;
                }

                recipesGrid.add(anchorPane, recipeColumn++, recipeRow);

                GridPane.setMargin(anchorPane, new Insets(10));
            }


        }

        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        loadRecipes();
        mostLikedRecipeController.loadMostLikedRecipes(mostLikeGrid);
    }
}