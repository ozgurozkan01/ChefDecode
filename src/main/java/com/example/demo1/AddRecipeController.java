package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddRecipeController implements Initializable
{
    Database db = new Database();

    @FXML
    private TextField nameInput;
    @FXML
    private TextField timeInput;
    @FXML
    private Button deleteRowButton;
    @FXML
    private Button addRowButton;

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
    private ComboBox<String> categoryCombo;
    @FXML
    private TextField quantityText;
    @FXML
    private VBox ingredientBox;
    @FXML
    private VBox recipeBox;
    @FXML
    private TextField ingredientQuantity;

    ObservableList<String> ingredientNames = FXCollections.observableArrayList();

    private TextField newIngredientField;
    private TextField ingredientStorageQuantity;
    private TextField priceText;

    boolean isAddingNewIngredient;

    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<TextField> instructionTextFieldList = new ArrayList<>();

    int rowIndex = 5;

    public void OnIngredientComboBoxPressed()
    {
        if (ingredientCombo.getValue().equals("Add New Ingredient..."))
        {
            ingredientBox.getChildren().get(1).setVisible(true);
            ingredientBox.getChildren().get(3).setVisible(true);
            ingredientBox.getChildren().get(5).setVisible(true);
            isAddingNewIngredient = true;
            ingredientQuantity.setPromptText("Enter ingredient quantity");
            return;
        }

        isAddingNewIngredient = false;
        ingredientBox.getChildren().get(1).setVisible(false);
        ingredientBox.getChildren().get(3).setVisible(false);
        ingredientBox.getChildren().get(5).setVisible(false);
        ingredientQuantity.setPromptText("Enter ingredient quantity (" + db.getIngredientUnitTypeByName(ingredientCombo.getValue()) + ")" );
    }

    public void OnAddIngredientButtonPressed()
    {
        String ingredientName = isAddingNewIngredient ? newIngredientField.getText() : ingredientCombo.getValue();
        String quantityType = db.getIngredientUnitTypeByName(ingredientName);
        String quantity = isAddingNewIngredient ? ingredientStorageQuantity.getText() : "0";
        String unitPrice = isAddingNewIngredient ? priceText.getText() : db.getIngredientUnitPrice(ingredientName);

        if (!ingredientName.isEmpty() && !quantityType.isEmpty() && !quantity.isEmpty() && !unitPrice.isEmpty())
        {
            Ingredient newIngredient = new Ingredient(db.getMaxIngredientIdFromDatabase() + 1, ingredientName, quantity, quantityType, Integer.parseInt(unitPrice));

            if (!ingredientList.contains(newIngredient))
            {
                ingredientNames.add(ingredientName);
                addedIngredientList.setItems(ingredientNames);
                ingredientList.add(newIngredient);

                if (isAddingNewIngredient)
                {
                    Ingredient existingIngredient = db.getIngredientByName(ingredientName);
                    if (existingIngredient != null)
                    {
                        int updatedQuantity = Integer.parseInt(existingIngredient.getQuantity()) + Integer.parseInt(quantity);
                        existingIngredient.setQuantity(String.valueOf(updatedQuantity));
                        System.out.println("Updated storage for " + existingIngredient.getIngredientName() + ": " + existingIngredient.getQuantity());

                        db.updateIngredient(existingIngredient);
                    }
                    else
                    {
                        db.insertIngredient(newIngredient);
                    }
                }
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
        String category = categoryCombo.getValue();
        if (category.isEmpty())
        {
            categoryCombo.setPromptText("REQUIRED AREA");
            categoryCombo.setStyle("-fx-prompt-text-fill: #FF0000");
        }

        String instruction = "";

        for (var inst : instructionTextFieldList)
        {
            instruction += inst.getText() + "\n";
        }

        String preparationTime = timeInput.getText();
        if (preparationTime.isEmpty())
        {
            timeInput.setPromptText("REQUIRED AREA");
            timeInput.setStyle("-fx-prompt-text-fill: #FF0000");
        }


        if (!name.isEmpty() && !category.isEmpty() && !instruction.isEmpty() && !preparationTime.isEmpty() && !ingredientList.isEmpty())
        {
            Recipe recipe = new Recipe(db.getMaxRecipeIdFromDatabase() + 1, name, category, Integer.parseInt(preparationTime), instruction, 0, 0);
            db.insertRecipe(recipe);
            MainController.recipes.add(recipe);

            for (var ingredient : ingredientList)
            {
                db.insertIngredient(ingredient);
                db.insertRelation(recipe.getID(), ingredient.getIngredientID(), Float.parseFloat(ingredient.getQuantity()));
            }
        }
    }

    public void OnAddNewRowButtonPressed()
    {
        TextField newInstructionRow = new TextField();
        newInstructionRow.setPromptText("Add new instruction");
        instructionTextFieldList.add(newInstructionRow);

        recipeBox.getChildren().add(rowIndex++, newInstructionRow);
    }

    public void OnDeleteLastRowButtonPressed()
    {
        if (!instructionTextFieldList.isEmpty())
        {
            recipeBox.getChildren().remove(rowIndex--);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ingredientStorageQuantity = new TextField();
        ingredientStorageQuantity.setPromptText("Enter Storage Quantity");

        newIngredientField = new TextField();
        newIngredientField.setPromptText("New Ingredient Name");

        priceText = new TextField();
        priceText.setPromptText("Enter Unit Price");

        ingredientBox.getChildren().add(1, newIngredientField);
        ingredientBox.getChildren().add(3, ingredientStorageQuantity);
        ingredientBox.getChildren().add(5, priceText);

        ingredientBox.getChildren().get(1).setVisible(false);
        ingredientBox.getChildren().get(3).setVisible(false);
        ingredientBox.getChildren().get(5).setVisible(false);

        ObservableList<String> ingredientOptions = FXCollections.observableArrayList("Add New Ingredient...");

        List<Ingredient> ingredientsFromDb = db.getAllIngredients();
        for (Ingredient ingredient : ingredientsFromDb)
        {
            ingredientOptions.add(ingredient.getIngredientName());
        }

        ingredientCombo.setItems(ingredientOptions);

        ObservableList<String> categoryList = FXCollections.observableArrayList("Main Courses", "Soups", "Appetizers", "Snacks", "Desserts");
        categoryCombo.setItems(categoryList);
    }
}
