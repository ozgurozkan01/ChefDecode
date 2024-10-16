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

    SavedRecipeController savedRecipeController;

    public void OnLogoButtonPressed(ActionEvent event)
    {
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
            Parent savedRecipeRoot = FXMLLoader.load(getClass().getResource("savedRecipes.fxml"));

            Scene savedRecipeScene = new Scene(savedRecipeRoot);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(savedRecipeScene);
            window.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void OnSearchButtonPressed()
    {
        System.out.println("Search Button Pressed");
    }
    public void OnAddRecipeButtonPressed()
    {
        System.out.println("Add Recipe Button Pressed");
    }
}
