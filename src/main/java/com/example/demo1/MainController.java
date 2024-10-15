package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private ScrollPane menusScrollPane;
    @FXML
    private ScrollPane mostLikedScrollPane;
    @FXML
    private ScrollPane recipesScrollPane;
    @FXML
    private GridPane recipesGrid;

    private List<Recipe> recipes = new ArrayList<>();
    private List<MainMostLikedRecipe> mainMostLikedRecipes = new ArrayList<>();

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

    private List<MainMostLikedRecipe> getMainMostLikedRecipeData()
    {
        List<MainMostLikedRecipe> mostLikedRecipes = new ArrayList<>();
        MainMostLikedRecipe mostLikedRecipe;

        for (int i = 0; i < 10; i++)
        {
            mostLikedRecipe = new MainMostLikedRecipe();
            mostLikedRecipe.setName("Çiğ Köfte");
            mostLikedRecipe.setRate("5.0");
            mostLikedRecipe.setImageSrc("çk.jpg");
            mainMostLikedRecipes.add(mostLikedRecipe);
        }

        return mostLikedRecipes;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        recipes.addAll(getRecipesData());
        mainMostLikedRecipes.addAll(getMainMostLikedRecipeData());


        System.out.println(recipes.size());
        int column = 0;
        int row = 2;

        try {
            for (var recipe : recipes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/demo1/recipe.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                RecipeController recipeController = fxmlLoader.getController();
                recipeController.setData(recipe);

                if (column == 2)
                {
                    column = 0;
                    row++;
                }

                recipesGrid.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }

        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}