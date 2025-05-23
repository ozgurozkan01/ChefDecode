package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TopBarController
{
    @FXML
    private Button addRecipeButton;
    @FXML
    private Button logoButton;
    @FXML
    private Button savedButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Button addStockButton;
    private SearchPageController searchPageController = new SearchPageController();

    SavedRecipeController savedRecipeController;

    public void OnLogoButtonPressed(ActionEvent event)
    {
        SavedRecipeController.isSavedRecipePageOpen = false;

        try
        {
            Parent mainRoot = FXMLLoader.load(getClass().getResource("main.fxml"));

            Scene mainScene = new Scene(mainRoot);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainScene);
            window.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void OnSavedButtonPressed(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/savedRecipes.fxml"));
            Parent root = loader.load();

            savedRecipeController = loader.getController();
            savedRecipeController.loadSavedRecipes();
            SavedRecipeController.setInstance(savedRecipeController);
            SavedRecipeController.setTopBarController(this);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            SavedRecipeController.isSavedRecipePageOpen = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void OnSearchButtonPressed(ActionEvent event) throws IOException {
        if (searchBar.getText() != null && !searchBar.getText().isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search-page.fxml"));
            Parent mainRoot = loader.load();

            // Controller'ı al
            SearchPageController searchPageController = loader.getController();

            Scene mainScene = new Scene(mainRoot);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(mainScene);
            window.show();

            searchPageController.searchStart(searchBar.getText());
        }
    }

    public void OnAddRecipeButtonPressed(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addRecipe.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            SavedRecipeController.isSavedRecipePageOpen = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void OnAddtockButtonPressed(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stockPage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            SavedRecipeController.isSavedRecipePageOpen = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
