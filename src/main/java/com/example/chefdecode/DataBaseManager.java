package com.example.chefdecode;

import java.sql.*;

public class DataBaseManager
{
    private Connection connection;

    // "jdbc:sqlserver://Ozgur;databaseName=recipe_db;integratedSecurity=true;encrypt=true;trustServerCertificate=true"
    private static final String DB_URL =
            "jdbc:sqlserver://Ozgur;databaseName=recipe_test;integratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public DataBaseManager()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connection successful!");
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("SQL Server JDBC Driver not found.");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }

    public void closeConnection() throws SQLException
    {
        if (connection != null )
        {
            connection.close();
        }
    }

    public void insertRecipe(int recipeID, String recipeName, String category, int preparationTime, String instruction)
    {
        String insertRecipeCommand = "INSERT INTO recipes (RecipeID, RecipeName, Category, PreparationTime, Instruction) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement recipeStmt = connection.prepareStatement(insertRecipeCommand))
        {
            recipeStmt.setInt(1, recipeID);
            recipeStmt.setString(2, recipeName);
            recipeStmt.setString(3, category);
            recipeStmt.setInt(4, preparationTime);
            recipeStmt.setString(5, instruction);
            recipeStmt.executeUpdate();
            System.out.println("Recipe added successfully.");
        }
        catch (SQLException e)
        {
            System.err.println("Error adding recipe: " + e.getMessage());
        }
    }

    public void insertIngredients(int ingredientID, String ingredientName, String quantity, String unit, int unitPrice)
    {
        String insertIngredientCommand = "INSERT INTO ingredients (IngredientID, IngredientName, Quantity, Unit, UnitPrice) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ingredientStmt = connection.prepareStatement(insertIngredientCommand))
        {
            ingredientStmt.setInt(1, ingredientID);
            ingredientStmt.setString(2, ingredientName);
            ingredientStmt.setString(3, quantity);
            ingredientStmt.setString(4, unit);
            ingredientStmt.setInt(5, unitPrice);
            ingredientStmt.executeUpdate();
            System.out.println("Ingredient added successfully.");
        }
        catch (SQLException e)
        {
            System.err.println("Error adding ingredient: " + e.getMessage());
        }
    }

    public void insertRelation(int recipeID, int ingredientID, float ingredientQuantity)
    {
        String insertRelationCommand = "INSERT INTO relation (RecipeID, IngredientID, IngredientQuantity) VALUES (?, ?, ?)";
        try (PreparedStatement relationStmt = connection.prepareStatement(insertRelationCommand))
        {
            relationStmt.setInt(1, recipeID);
            relationStmt.setInt(2, ingredientID);
            relationStmt.setFloat(3, ingredientQuantity);
            relationStmt.executeUpdate();
            System.out.println("Relation added successfully.");
        }
        catch (SQLException e)
        {
            System.err.println("Error adding relation: " + e.getMessage());
        }
    }

    public void deleteRecipe(int recipeID) {
        String deleteRecipeCommand = "DELETE FROM recipes WHERE RecipeID = ?";

        try (PreparedStatement recipeStmt = connection.prepareStatement(deleteRecipeCommand))
        {
            recipeStmt.setInt(1, recipeID);
            int rowsAffected = recipeStmt.executeUpdate();

            if (rowsAffected > 0)   { System.out.println("Recipe deleted successfully."); }
            else                    { System.out.println("No recipe found with the given ID."); }
        }
        catch (SQLException e)
        {
            System.err.println("Error deleting recipe: " + e.getMessage());
        }
    }

    public void deleteIngredient(int ingredientID) {
        String deleteIngredientCommand = "DELETE FROM ingredients WHERE IngredientID = ?";

        try (PreparedStatement ingredientStmt = connection.prepareStatement(deleteIngredientCommand))
        {
            ingredientStmt.setInt(1, ingredientID);
            int rowsAffected = ingredientStmt.executeUpdate();

            if (rowsAffected > 0)   { System.out.println("Ingredient deleted successfully."); }
            else                    { System.out.println("No ingredient found with the given ID."); }
        }
        catch (SQLException e)
        {
            System.err.println("Error deleting ingredient: " + e.getMessage());
        }
    }

    public void deleteRelation(int recipeID, int ingredientID) {
        String deleteRelationCommand = "DELETE FROM relation WHERE RecipeID = ? AND IngredientID = ?";

        try (PreparedStatement relationStmt = connection.prepareStatement(deleteRelationCommand))
        {
            relationStmt.setInt(1, recipeID);
            relationStmt.setInt(2, ingredientID);
            int rowsAffected = relationStmt.executeUpdate();

            if (rowsAffected > 0)   { System.out.println("Relation deleted successfully."); }
            else                    { System.out.println("No relation found with the given RecipeID and IngredientID."); }
        }
        catch (SQLException e)
        {
            System.err.println("Error deleting relation: " + e.getMessage());
        }
    }

    public <T extends Comparable<T>> void updateRecipe(int recipeID, String columnName, T newValue)
    {
        String updateSQL = "UPDATE recipes SET " + columnName + " = ? WHERE RecipeID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL))
        {
            preparedStatement.setObject(1, newValue); // First Question Mark
            preparedStatement.setInt(2, recipeID); // Second Question Mark

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("Recipe record updated successfully!");
            }
            else
            {
                System.out.println("No recipe found with the given RecipeID.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Update failed!");
            e.printStackTrace();
        }
    }

    public <T extends Comparable<T>> void updateIngredient(int ingredientID, String columnName, T newValue)
    {
        String updateSQL = "UPDATE ingredients SET " + columnName + " = ? WHERE IngredientID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL))
        {
            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, ingredientID);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("Ingredient record updated successfully!");
            }
            else
            {
                System.out.println("No ingredient found with the given IngredientID.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Update failed!");
            e.printStackTrace();
        }
    }

/*    public void getRecipeByID(int recipeID) {
        String selectRecipeSQL = "SELECT * FROM recipes WHERE RecipeID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectRecipeSQL)) {
            preparedStatement.setInt(1, recipeID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String recipeName = resultSet.getString("RecipeName");
                String category = resultSet.getString("Category");
                int preparationTime = resultSet.getInt("PreparationTime");
                String instructions = resultSet.getString("Instruction");

                // Print the retrieved recipe data
                System.out.println("RecipeID: " + recipeID);
                System.out.println("Recipe Name: " + recipeName);
                System.out.println("Category: " + category);
                System.out.println("Preparation Time: " + preparationTime + " minutes");
                System.out.println("Instructions: " + instructions);
            } else {
                System.out.println("Recipe not found with RecipeID: " + recipeID);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching recipe: " + e.getMessage());
        }
    }*/
}
