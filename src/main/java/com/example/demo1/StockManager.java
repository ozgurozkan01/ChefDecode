package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StockManager implements Initializable
{
    @FXML
    private Button subtractStockButton;
    @FXML
    private Button addStockButton;

    @FXML
    private Label informationLabel;
    @FXML
    private Label currentStockLabel;
    @FXML
    private HBox includeHeader;
    @FXML
    private ComboBox<String> ingredientCombo;
    @FXML
    private TextField stockInputText;

    Database database = new Database();

    Database db = new Database();

    public void OnAddButtonPressed()
    {
        if (ingredientCombo.getValue() != null && !ingredientCombo.getValue().isEmpty() && !stockInputText.getText().isEmpty())
        {
            String currentStock = db.getIngredientStock(ingredientCombo.getValue());
            String additionStock = stockInputText.getText();
            int stockAfterAddition = Integer.parseInt(additionStock) + Integer.parseInt(currentStock);

            db.updateIngredientStock(ingredientCombo.getValue(), Integer.toString(stockAfterAddition));
            currentStockLabel.setText(db.getIngredientStockWithUnit(ingredientCombo.getValue()));
            informationLabel.setText("- Stock addition into the ingredient is succesfully ! -");
        }
    }

    public void OnDeleteButtonPressed()
    {

        if (ingredientCombo.getValue() != null && !ingredientCombo.getValue().isEmpty() && !stockInputText.getText().isEmpty())
        {
            String currentStock = db.getIngredientStock(ingredientCombo.getValue());
            String subtractionStock = stockInputText.getText();

            if (Integer.parseInt(currentStock) >= Integer.parseInt(subtractionStock))
            {
                int stockAfterAddition = Integer.parseInt(currentStock) - Integer.parseInt(stockInputText.getText());

                db.updateIngredientStock(ingredientCombo.getValue(), Integer.toString(stockAfterAddition));
                currentStockLabel.setText(db.getIngredientStockWithUnit(ingredientCombo.getValue()));
                informationLabel.setText("- Stock subtraction into the ingredient is succesfully ! -");
            }
            else
            {
                informationLabel.setText("- There is not enough ingredient to subtract the stock. ! -");
            }
        }
    }

    public void OnIngredientComboPressed()
    {
        if (ingredientCombo.getValue() != null && !ingredientCombo.getValue().isEmpty())
        {
            currentStockLabel.setText(db.getIngredientStockWithUnit(ingredientCombo.getValue()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ingredientCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {});

        if (db != null)
        {
            List<String> ingredients = db.getAllIngredientNames();
            ObservableList<String> ingredientNameList = FXCollections.observableArrayList(ingredients);

            ingredientCombo.setItems(ingredientNameList);
        }
    }

    public boolean isRecipeAvailable(Recipe recipe)
    {
        for (Ingredient ingredient : database.getRecipeIngredients(recipe.getID()))
        {
            float stock = Float.parseFloat(ingredient.getQuantity());

            float ingredientQuantity = database.getIngredientQuantityForRecipe(recipe.getID(), ingredient.getIngredientID());

            if (stock < ingredientQuantity)
            {
                return false;
            }
        }
        return true;
    }

    public void setRecipeAvailabilityColor(Recipe recipe, AnchorPane anchorPane)
    {
        if (isRecipeAvailable(recipe))
        {
            anchorPane.setStyle("-fx-background-color: #00FF0044; -fx-border-width: 2;");
        }
        else
        {
            anchorPane.setStyle("-fx-background-color: #FF000055; -fx-border-width: 2;");
        }
    }
}
