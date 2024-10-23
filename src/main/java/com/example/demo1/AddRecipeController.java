package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable
{
    Database db = new Database();

    @FXML
    private TextField instructionInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField timeInput;
    @FXML
    private TextField categoryInput;


    @FXML
    private ListView<String> addedIngredientList;
    @FXML
    private Button addButton;
    @FXML
    private Button addIngredient;
    @FXML
    private HBox includeHeader;
    @FXML
    private ComboBox<String> ingredientCombo;
    @FXML
    private ComboBox<String> quantityCombo;
    @FXML
    private TextField quantityText;
    @FXML
    private TextField priceText;
    @FXML
    private VBox ingredientBox;

    ObservableList<String> ingredientNames = FXCollections.observableArrayList();

    TextField newIngredientField;

    boolean isAddingNewIngredient;

    private List<Ingredient> ingredientList = new ArrayList<>();
    public void OnIngredientComboBoxPressed()
    {
        if (ingredientCombo.getValue().equals("Add New Ingredient..."))
        {
            newIngredientField = new TextField();
            newIngredientField.setPromptText("New Ingredient Name");

            ingredientBox.getChildren().add(1, newIngredientField);
            isAddingNewIngredient = true;
            return;
        }

        isAddingNewIngredient = false;
    }

    public void OnAddIngredientButtonPressed()
    {
        String ingredientName = isAddingNewIngredient ? newIngredientField.getText() : ingredientCombo.getValue();
        String quantityType = quantityCombo.getValue();
        String quantity = quantityText.getText();
        String unitPrice = priceText.getText();

        if (!ingredientName.isEmpty() && !quantityType.isEmpty() && !quantity.isEmpty() && !unitPrice.isEmpty())
        {
            Ingredient newIngredient = new Ingredient(MainController.ingredientIDCounter++, ingredientName, quantity, quantityType, Integer.parseInt(unitPrice));
            if (!ingredientList.contains(newIngredient))
            {
                ingredientNames.add(ingredientName);
                addedIngredientList.setItems(ingredientNames);
                ingredientList.add(newIngredient);
            }
        }
    }

    public void OnAddRecipeButtonPressed()
    {
        String name = nameInput.getText();
        if (name.isEmpty())
        {
            nameInput.setPromptText("REQUIRED AREA");
            nameInput.setStyle("-fx-prompt-text-fill: #FF0000");
        }
        String category = categoryInput.getText();
        if (category.isEmpty())
        {
            categoryInput.setPromptText("REQUIRED AREA");
            categoryInput.setStyle("-fx-prompt-text-fill: #FF0000");
        }
        String instruction = instructionInput.getText();
        if (instruction.isEmpty())
        {
            instructionInput.setPromptText("REQUIRED AREA");
            instructionInput.setStyle("-fx-prompt-text-fill: #FF0000");
        }
        String preparationTime = timeInput.getText();
        if (preparationTime.isEmpty())
        {
            timeInput.setPromptText("REQUIRED AREA");
            timeInput.setStyle("-fx-prompt-text-fill: #FF0000");
        }


        if (!name.isEmpty() && !category.isEmpty() && !instruction.isEmpty() && !preparationTime.isEmpty() && !ingredientList.isEmpty())
        {
            Recipe recipe = new Recipe(MainController.recipeIDCounter++, name, category, Integer.parseInt(preparationTime), instruction, 0, 0);
            db.insertRecipe(recipe);

            for (var ingredient : ingredientList)
            {
                db.insertIngredient(ingredient);
                db.insertRelation(recipe.getID(), ingredient.getIngredientID(), 1.5f);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<String> options = FXCollections.observableArrayList(
                "adet",
                "gram",
                "ml",
                "yemek kaşığı", // alternatif
                "çay kaşığı", // alternatif
                "su bardağı" // alternatif
        );
        quantityCombo.setItems(options);

        ObservableList<String> ingredientOptions = FXCollections.observableArrayList(
                "Add New Ingredient..."
        );
        ingredientCombo.setItems(ingredientOptions);
    }
}
